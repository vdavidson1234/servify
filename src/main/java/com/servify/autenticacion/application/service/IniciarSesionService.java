package com.servify.autenticacion.application.service;

import com.servify.autenticacion.application.dto.IniciarSesionCommand;
import com.servify.autenticacion.application.dto.SesionResult;
import com.servify.autenticacion.application.dto.TokenResult;
import com.servify.autenticacion.application.port.in.IniciarSesionUseCase;
import com.servify.autenticacion.application.port.out.CredencialAccesoRepositoryPort;
import com.servify.autenticacion.application.port.out.PasswordHasherPort;
import com.servify.autenticacion.application.port.out.RefreshTokenRepositoryPort;
import com.servify.autenticacion.application.port.out.TokenProviderPort;
import com.servify.autenticacion.application.port.out.UsuarioAutenticablePort;
import com.servify.autenticacion.domain.model.CredencialAcceso;
import com.servify.autenticacion.domain.model.RefreshToken;

import java.time.LocalDateTime;
import java.util.UUID;

public class IniciarSesionService implements IniciarSesionUseCase {

    private final CredencialAccesoRepositoryPort credencialAccesoRepositoryPort;
    private final PasswordHasherPort passwordHasherPort;
    private final TokenProviderPort tokenProviderPort;
    private final RefreshTokenRepositoryPort refreshTokenRepositoryPort;
    private final UsuarioAutenticablePort usuarioAutenticablePort;

    public IniciarSesionService(CredencialAccesoRepositoryPort credencialAccesoRepositoryPort,
                                PasswordHasherPort passwordHasherPort,
                                TokenProviderPort tokenProviderPort,
                                RefreshTokenRepositoryPort refreshTokenRepositoryPort,
                                UsuarioAutenticablePort usuarioAutenticablePort) {
        this.credencialAccesoRepositoryPort = credencialAccesoRepositoryPort;
        this.passwordHasherPort = passwordHasherPort;
        this.tokenProviderPort = tokenProviderPort;
        this.refreshTokenRepositoryPort = refreshTokenRepositoryPort;
        this.usuarioAutenticablePort = usuarioAutenticablePort;
    }

    @Override
    public SesionResult iniciar(IniciarSesionCommand command) {
        // TODO implementar inicio de sesion.
        // Debe:
        // - validar command
        // - buscar credencial por email
        // - validar que la credencial este habilitada
        // - validar que el usuario pueda autenticarse mediante UsuarioAutenticablePort
        // - verificar password con PasswordHasherPort
        // - registrar acceso exitoso o intento fallido segun corresponda
        // - generar access token y refresh token con TokenProviderPort
        // - persistir RefreshToken con hash, no el token plano
        // - devolver SesionResult usando builder
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected CredencialAcceso obtenerCredencial(String emailAcceso) {
        // TODO implementar busqueda obligatoria de credencial por email.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected void validarCredencialYPassword(CredencialAcceso credencialAcceso,
                                              String passwordPlano) {
        // TODO implementar validacion de credencial y password.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected RefreshToken construirRefreshToken(CredencialAcceso credencialAcceso,
                                                 TokenResult refreshToken) {
        // TODO implementar construccion del RefreshToken de dominio.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected SesionResult construirResultado(CredencialAcceso credencialAcceso,
                                              TokenResult accessToken,
                                              TokenResult refreshToken,
                                              LocalDateTime fechaInicioSesion) {
        // TODO implementar mapeo de sesion usando SesionResult.builder().
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected UUID generarIdRefreshToken() {
        // TODO implementar generacion de identificador de refresh token.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected LocalDateTime obtenerFechaActual() {
        // TODO implementar obtencion centralizada de fecha actual.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }
}
