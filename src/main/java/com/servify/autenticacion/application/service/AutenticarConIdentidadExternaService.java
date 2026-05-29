package com.servify.autenticacion.application.service;

import com.servify.autenticacion.application.dto.AutenticarConIdentidadExternaCommand;
import com.servify.autenticacion.application.dto.IdentidadExternaVerificadaResult;
import com.servify.autenticacion.application.dto.SesionResult;
import com.servify.autenticacion.application.dto.TokenResult;
import com.servify.autenticacion.application.port.in.AutenticarConIdentidadExternaUseCase;
import com.servify.autenticacion.application.port.out.IdentidadExternaRepositoryPort;
import com.servify.autenticacion.application.port.out.ProveedorIdentidadVerifierPort;
import com.servify.autenticacion.application.port.out.RefreshTokenRepositoryPort;
import com.servify.autenticacion.application.port.out.TokenProviderPort;
import com.servify.autenticacion.domain.enumtype.ProveedorIdentidadExterna;
import com.servify.autenticacion.domain.model.IdentidadExterna;
import com.servify.autenticacion.domain.model.RefreshToken;
import com.servify.shared.domain.exception.BusinessRuleException;
import com.servify.shared.domain.exception.ValidationException;
import com.servify.usuarios.application.port.out.UsuarioRepositoryPort;
import com.servify.usuarios.domain.enumtype.EstadoUsuario;
import com.servify.usuarios.domain.enumtype.EstadoValidacionIdentidad;
import com.servify.usuarios.domain.enumtype.Rol;
import com.servify.usuarios.domain.model.Usuario;
import com.servify.usuarios.domain.valueobject.Contacto;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

public class AutenticarConIdentidadExternaService implements AutenticarConIdentidadExternaUseCase {

    private final ProveedorIdentidadVerifierPort proveedorIdentidadVerifierPort;
    private final IdentidadExternaRepositoryPort identidadExternaRepositoryPort;
    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final TokenProviderPort tokenProviderPort;
    private final RefreshTokenRepositoryPort refreshTokenRepositoryPort;

    public AutenticarConIdentidadExternaService(
            ProveedorIdentidadVerifierPort proveedorIdentidadVerifierPort,
            IdentidadExternaRepositoryPort identidadExternaRepositoryPort,
            UsuarioRepositoryPort usuarioRepositoryPort,
            TokenProviderPort tokenProviderPort,
            RefreshTokenRepositoryPort refreshTokenRepositoryPort
    ) {
        this.proveedorIdentidadVerifierPort = proveedorIdentidadVerifierPort;
        this.identidadExternaRepositoryPort = identidadExternaRepositoryPort;
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.tokenProviderPort = tokenProviderPort;
        this.refreshTokenRepositoryPort = refreshTokenRepositoryPort;
    }

    @Override
    public SesionResult autenticar(AutenticarConIdentidadExternaCommand command) {
        validarCommand(command);

        IdentidadExternaVerificadaResult identidadVerificada = proveedorIdentidadVerifierPort.verificar(command);
        validarIdentidadVerificada(command, identidadVerificada);

        LocalDateTime ahora = obtenerFechaActual();
        IdentidadExterna identidadExterna = obtenerOCrearIdentidad(command, identidadVerificada, ahora);
        Usuario usuario = obtenerUsuarioAutenticable(identidadExterna.getUsuarioId());

        identidadExterna.registrarAcceso(
                ahora,
                identidadVerificada.getEmail(),
                identidadVerificada.getEmailVerificado(),
                identidadVerificada.getNombreMostrado()
        );
        identidadExternaRepositoryPort.guardar(identidadExterna);

        TokenResult access = tokenProviderPort.generarAccessToken(usuario.getId(), identidadExterna.getEmail());
        TokenResult refresh = tokenProviderPort.generarRefreshToken(usuario.getId(), identidadExterna.getEmail());
        RefreshToken refreshDominio = construirRefreshToken(usuario, identidadExterna, refresh);
        refreshTokenRepositoryPort.guardar(refreshDominio);

        return construirResultado(usuario, identidadExterna.getEmail(), access, refresh, ahora);
    }

    private void validarCommand(AutenticarConIdentidadExternaCommand command) {
        if (command == null) {
            throw new ValidationException("El comando de autenticacion externa es obligatorio");
        }
        if (command.getProveedor() == null) {
            throw new ValidationException("proveedor es obligatorio");
        }
        if (command.getIdToken() == null || command.getIdToken().isBlank()) {
            throw new ValidationException("idToken es obligatorio");
        }
        if (Rol.ADMIN == command.getRolSolicitado()) {
            throw new BusinessRuleException("No se puede registrar un administrador desde autenticacion social");
        }
    }

    private void validarIdentidadVerificada(AutenticarConIdentidadExternaCommand command,
                                            IdentidadExternaVerificadaResult identidadVerificada) {
        if (identidadVerificada == null) {
            throw new IllegalStateException("El proveedor no devolvio una identidad verificada");
        }
        if (identidadVerificada.getProveedor() != command.getProveedor()) {
            throw new IllegalStateException("El proveedor verificado no coincide con el solicitado");
        }
        if (identidadVerificada.getSubject() == null || identidadVerificada.getSubject().isBlank()) {
            throw new ValidationException("El token del proveedor no contiene subject");
        }
        if (identidadVerificada.getEmail() == null || identidadVerificada.getEmail().isBlank()) {
            throw new ValidationException("El token del proveedor no contiene email");
        }
        if (!Boolean.TRUE.equals(identidadVerificada.getEmailVerificado())) {
            throw new BusinessRuleException("El proveedor no confirmo que el email este verificado");
        }
    }

    private IdentidadExterna obtenerOCrearIdentidad(AutenticarConIdentidadExternaCommand command,
                                                    IdentidadExternaVerificadaResult identidadVerificada,
                                                    LocalDateTime ahora) {
        ProveedorIdentidadExterna proveedor = identidadVerificada.getProveedor();
        String subject = identidadVerificada.getSubject();
        return identidadExternaRepositoryPort.buscarPorProveedorYSubject(proveedor, subject)
                .map(this::validarIdentidadHabilitada)
                .orElseGet(() -> vincularNuevaIdentidad(command, identidadVerificada, ahora));
    }

    private IdentidadExterna validarIdentidadHabilitada(IdentidadExterna identidadExterna) {
        if (!identidadExterna.estaHabilitada()) {
            throw new IllegalStateException("La identidad externa no esta habilitada");
        }
        return identidadExterna;
    }

    private IdentidadExterna vincularNuevaIdentidad(AutenticarConIdentidadExternaCommand command,
                                                    IdentidadExternaVerificadaResult identidadVerificada,
                                                    LocalDateTime ahora) {
        String emailNormalizado = normalizarEmail(identidadVerificada.getEmail());
        Usuario usuario = usuarioRepositoryPort.buscarPorEmail(emailNormalizado)
                .orElseGet(() -> crearUsuarioDesdeIdentidad(command, emailNormalizado, ahora));

        obtenerUsuarioAutenticable(usuario.getId());
        identidadExternaRepositoryPort.buscarPorUsuarioIdYProveedor(usuario.getId(), identidadVerificada.getProveedor())
                .filter(existing -> !Objects.equals(existing.getSubject(), identidadVerificada.getSubject()))
                .ifPresent(existing -> {
                    throw new BusinessRuleException("El usuario ya tiene una identidad vinculada para "
                            + identidadVerificada.getProveedor().getApiValue());
                });

        IdentidadExterna identidadExterna = new IdentidadExterna(
                generarIdIdentidadExterna(),
                usuario.getId(),
                identidadVerificada.getProveedor(),
                identidadVerificada.getSubject(),
                emailNormalizado,
                identidadVerificada.getEmailVerificado(),
                identidadVerificada.getNombreMostrado(),
                ahora,
                null,
                true
        );
        return identidadExternaRepositoryPort.guardar(identidadExterna);
    }

    private Usuario crearUsuarioDesdeIdentidad(AutenticarConIdentidadExternaCommand command,
                                               String email,
                                               LocalDateTime ahora) {
        Usuario usuario = new Usuario(
                generarIdUsuario(),
                new Contacto(email, normalizarTextoOpcional(command.getTelefono())),
                command.getRolSolicitado() != null ? command.getRolSolicitado() : Rol.USUARIO,
                EstadoUsuario.ACTIVO,
                EstadoValidacionIdentidad.NO_REQUERIDA,
                null,
                ahora
        );
        return usuarioRepositoryPort.guardar(usuario);
    }

    private Usuario obtenerUsuarioAutenticable(UUID usuarioId) {
        Usuario usuario = usuarioRepositoryPort.buscarPorId(usuarioId)
                .orElseThrow(() -> new IllegalStateException("Usuario asociado a identidad externa no encontrado"));
        if (!usuario.estaActivo()) {
            throw new IllegalStateException("El usuario no puede autenticarse");
        }
        return usuario;
    }

    private RefreshToken construirRefreshToken(Usuario usuario,
                                               IdentidadExterna identidadExterna,
                                               TokenResult refreshToken) {
        String hash = tokenProviderPort.obtenerHashToken(refreshToken.getToken());
        return new RefreshToken(
                generarIdRefreshToken(),
                usuario.getId(),
                null,
                identidadExterna.getId(),
                hash,
                refreshToken.getFechaEmision(),
                refreshToken.getFechaExpiracion(),
                null,
                true
        );
    }

    private SesionResult construirResultado(Usuario usuario,
                                            String emailAcceso,
                                            TokenResult accessToken,
                                            TokenResult refreshToken,
                                            LocalDateTime fechaInicioSesion) {
        return SesionResult.builder()
                .usuarioId(usuario.getId())
                .emailAcceso(emailAcceso)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .fechaInicioSesion(fechaInicioSesion)
                .build();
    }

    protected UUID generarIdIdentidadExterna() {
        return UUID.randomUUID();
    }

    protected UUID generarIdUsuario() {
        return UUID.randomUUID();
    }

    protected UUID generarIdRefreshToken() {
        return UUID.randomUUID();
    }

    protected LocalDateTime obtenerFechaActual() {
        return LocalDateTime.now();
    }

    private String normalizarEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new ValidationException("El email es obligatorio");
        }
        return email.trim().toLowerCase(Locale.ROOT);
    }

    private String normalizarTextoOpcional(String valor) {
        if (valor == null || valor.isBlank()) {
            return null;
        }
        return valor.trim();
    }
}
