package com.servify.autenticacion.application.service;

import com.servify.autenticacion.application.dto.RenovarTokenCommand;
import com.servify.autenticacion.application.dto.SesionResult;
import com.servify.autenticacion.application.dto.TokenResult;
import com.servify.autenticacion.application.port.in.RenovarTokenUseCase;
import com.servify.autenticacion.application.port.out.CredencialAccesoRepositoryPort;
import com.servify.autenticacion.application.port.out.IdentidadExternaRepositoryPort;
import com.servify.autenticacion.application.port.out.RefreshTokenRepositoryPort;
import com.servify.autenticacion.application.port.out.TokenProviderPort;
import com.servify.autenticacion.application.port.out.UsuarioAutenticablePort;
import com.servify.autenticacion.domain.model.CredencialAcceso;
import com.servify.autenticacion.domain.model.IdentidadExterna;
import com.servify.autenticacion.domain.model.RefreshToken;

import java.time.LocalDateTime;
import java.util.UUID;

public class RenovarTokenService implements RenovarTokenUseCase {

    private final RefreshTokenRepositoryPort refreshTokenRepositoryPort;
    private final CredencialAccesoRepositoryPort credencialAccesoRepositoryPort;
    private final IdentidadExternaRepositoryPort identidadExternaRepositoryPort;
    private final TokenProviderPort tokenProviderPort;
    private final UsuarioAutenticablePort usuarioAutenticablePort;

    public RenovarTokenService(RefreshTokenRepositoryPort refreshTokenRepositoryPort,
                               CredencialAccesoRepositoryPort credencialAccesoRepositoryPort,
                               TokenProviderPort tokenProviderPort,
                               UsuarioAutenticablePort usuarioAutenticablePort) {
        this(refreshTokenRepositoryPort, credencialAccesoRepositoryPort, null, tokenProviderPort, usuarioAutenticablePort);
    }

    public RenovarTokenService(RefreshTokenRepositoryPort refreshTokenRepositoryPort,
                               CredencialAccesoRepositoryPort credencialAccesoRepositoryPort,
                               IdentidadExternaRepositoryPort identidadExternaRepositoryPort,
                               TokenProviderPort tokenProviderPort,
                               UsuarioAutenticablePort usuarioAutenticablePort) {
        this.refreshTokenRepositoryPort = refreshTokenRepositoryPort;
        this.credencialAccesoRepositoryPort = credencialAccesoRepositoryPort;
        this.identidadExternaRepositoryPort = identidadExternaRepositoryPort;
        this.tokenProviderPort = tokenProviderPort;
        this.usuarioAutenticablePort = usuarioAutenticablePort;
    }

    @Override
    public SesionResult renovar(RenovarTokenCommand command) {
        // Renueva tokens a partir de un refresh token válido.
        // - valida el refresh token, revoca el anterior y genera nuevos tokens
        if (command == null || command.getRefreshToken() == null || command.getRefreshToken().trim().isEmpty()) {
            throw new IllegalArgumentException("refreshToken no puede ser nulo o vacío");
        }

        LocalDateTime ahora = obtenerFechaActual();
        RefreshToken existente = obtenerRefreshToken(command.getRefreshToken());
        validarRefreshToken(existente, ahora);

        DatosAutenticacion datosAutenticacion = obtenerDatosAutenticacion(existente);
        if (!usuarioAutenticablePort.puedeAutenticarse(datosAutenticacion.usuarioId())) {
            throw new IllegalStateException("El usuario no puede autenticarse");
        }

        TokenResult nuevoAccess = tokenProviderPort.generarAccessToken(datosAutenticacion.usuarioId(), datosAutenticacion.emailAcceso());
        TokenResult nuevoRefresh = tokenProviderPort.generarRefreshToken(datosAutenticacion.usuarioId(), datosAutenticacion.emailAcceso());

        // Revocar refresh token anterior y persistir cambio
        existente.revocar(ahora);
        refreshTokenRepositoryPort.guardar(existente);

        RefreshToken refreshDominio = construirRefreshToken(datosAutenticacion, nuevoRefresh);
        refreshTokenRepositoryPort.guardar(refreshDominio);

        return construirResultado(datosAutenticacion, nuevoAccess, nuevoRefresh, ahora);
    }

    protected RefreshToken obtenerRefreshToken(String refreshTokenPlano) {
        if (refreshTokenPlano == null || refreshTokenPlano.trim().isEmpty()) {
            throw new IllegalArgumentException("refreshToken no puede ser nulo o vacío");
        }
        String hash = this.tokenProviderPort.obtenerHashToken(refreshTokenPlano);
        return this.refreshTokenRepositoryPort.buscarPorTokenHash(hash)
                .orElseThrow(() -> new IllegalArgumentException("Refresh token no encontrado"));
    }

    protected DatosAutenticacion obtenerDatosAutenticacion(RefreshToken refreshToken) {
        if (refreshToken == null) {
            throw new IllegalArgumentException("refreshToken no puede ser nulo");
        }
        if (refreshToken.getCredencialAccesoId() != null) {
            CredencialAcceso credencial = obtenerCredencial(refreshToken);
            return new DatosAutenticacion(
                    credencial.getUsuarioId(),
                    credencial.getEmailAcceso(),
                    credencial.getId(),
                    null
            );
        }
        if (refreshToken.getIdentidadExternaId() != null) {
            IdentidadExterna identidadExterna = obtenerIdentidadExterna(refreshToken);
            return new DatosAutenticacion(
                    identidadExterna.getUsuarioId(),
                    identidadExterna.getEmail(),
                    null,
                    identidadExterna.getId()
            );
        }
        throw new IllegalStateException("Refresh token sin credencial o identidad externa asociada");
    }

    protected CredencialAcceso obtenerCredencial(RefreshToken refreshToken) {
        return this.credencialAccesoRepositoryPort.buscarPorId(refreshToken.getCredencialAccesoId())
                .orElseThrow(() -> new IllegalStateException("Credencial de acceso asociada no encontrada"));
    }

    protected IdentidadExterna obtenerIdentidadExterna(RefreshToken refreshToken) {
        if (identidadExternaRepositoryPort == null) {
            throw new IllegalStateException("No hay repositorio de identidad externa configurado");
        }
        return this.identidadExternaRepositoryPort.buscarPorId(refreshToken.getIdentidadExternaId())
                .filter(IdentidadExterna::estaHabilitada)
                .orElseThrow(() -> new IllegalStateException("Identidad externa asociada no encontrada o deshabilitada"));
    }

    protected void validarRefreshToken(RefreshToken refreshToken,
                                       LocalDateTime fechaActual) {
        if (refreshToken == null) {
            throw new IllegalArgumentException("refreshToken no puede ser nulo");
        }
        if (!refreshToken.estaActivo()) {
            throw new IllegalStateException("Refresh token no está activo");
        }
        if (refreshToken.estaExpirado(fechaActual)) {
            throw new IllegalStateException("Refresh token expirado");
        }
        if (refreshToken.fueRevocado()) {
            throw new IllegalStateException("Refresh token revocado");
        }
    }

    protected RefreshToken construirRefreshToken(DatosAutenticacion datosAutenticacion,
                                                 TokenResult refreshToken) {
        if (datosAutenticacion == null || refreshToken == null) {
            throw new IllegalArgumentException("datosAutenticacion y refreshToken no pueden ser nulos");
        }
        String hash = this.tokenProviderPort.obtenerHashToken(refreshToken.getToken());
        return new RefreshToken(
                java.util.UUID.randomUUID(),
                datosAutenticacion.usuarioId(),
                datosAutenticacion.credencialAccesoId(),
                datosAutenticacion.identidadExternaId(),
                hash,
                refreshToken.getFechaEmision(),
                refreshToken.getFechaExpiracion(),
                null,
                true
        );
    }

    protected SesionResult construirResultado(DatosAutenticacion datosAutenticacion,
                                              TokenResult accessToken,
                                              TokenResult refreshToken,
                                              LocalDateTime fechaInicioSesion) {
        return SesionResult.builder()
            .usuarioId(datosAutenticacion.usuarioId())
            .emailAcceso(datosAutenticacion.emailAcceso())
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .fechaInicioSesion(fechaInicioSesion)
            .build();
    }

    protected LocalDateTime obtenerFechaActual() {
        return LocalDateTime.now();
    }

    protected record DatosAutenticacion(
            UUID usuarioId,
            String emailAcceso,
            UUID credencialAccesoId,
            UUID identidadExternaId
    ) {
    }
}
