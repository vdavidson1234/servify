package com.servify.autenticacion.application.service;

import com.servify.autenticacion.application.dto.RenovarTokenCommand;
import com.servify.autenticacion.application.dto.SesionResult;
import com.servify.autenticacion.application.dto.TokenResult;
import com.servify.autenticacion.application.port.in.RenovarTokenUseCase;
import com.servify.autenticacion.application.port.out.CredencialAccesoRepositoryPort;
import com.servify.autenticacion.application.port.out.RefreshTokenRepositoryPort;
import com.servify.autenticacion.application.port.out.TokenProviderPort;
import com.servify.autenticacion.application.port.out.UsuarioAutenticablePort;
import com.servify.autenticacion.domain.model.CredencialAcceso;
import com.servify.autenticacion.domain.model.RefreshToken;

import java.time.LocalDateTime;

public class RenovarTokenService implements RenovarTokenUseCase {

    private final RefreshTokenRepositoryPort refreshTokenRepositoryPort;
    private final CredencialAccesoRepositoryPort credencialAccesoRepositoryPort;
    private final TokenProviderPort tokenProviderPort;
    private final UsuarioAutenticablePort usuarioAutenticablePort;

    public RenovarTokenService(RefreshTokenRepositoryPort refreshTokenRepositoryPort,
                               CredencialAccesoRepositoryPort credencialAccesoRepositoryPort,
                               TokenProviderPort tokenProviderPort,
                               UsuarioAutenticablePort usuarioAutenticablePort) {
        this.refreshTokenRepositoryPort = refreshTokenRepositoryPort;
        this.credencialAccesoRepositoryPort = credencialAccesoRepositoryPort;
        this.tokenProviderPort = tokenProviderPort;
        this.usuarioAutenticablePort = usuarioAutenticablePort;
    }

    @Override
    public SesionResult renovar(RenovarTokenCommand command) {
        // TODO implementar renovacion de token.
        // Debe:
        // - validar command
        // - buscar RefreshToken por hash
        // - validar que este activo, no expirado y no revocado
        // - validar que el usuario pueda autenticarse
        // - recuperar CredencialAcceso asociada
        // - generar nuevo access token y refresh token
        // - revocar el refresh token anterior si la politica de rotacion lo exige
        // - persistir el nuevo RefreshToken
        // - devolver SesionResult usando builder
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected RefreshToken obtenerRefreshToken(String refreshTokenPlano) {
        // TODO implementar busqueda obligatoria de RefreshToken por hash.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected CredencialAcceso obtenerCredencial(RefreshToken refreshToken) {
        // TODO implementar busqueda obligatoria de CredencialAcceso asociada.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected void validarRefreshToken(RefreshToken refreshToken,
                                       LocalDateTime fechaActual) {
        // TODO implementar validacion de refresh token vigente.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected RefreshToken construirRefreshToken(CredencialAcceso credencialAcceso,
                                                 TokenResult refreshToken) {
        // TODO implementar construccion del nuevo RefreshToken de dominio.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected SesionResult construirResultado(CredencialAcceso credencialAcceso,
                                              TokenResult accessToken,
                                              TokenResult refreshToken,
                                              LocalDateTime fechaInicioSesion) {
        // TODO implementar mapeo de sesion usando SesionResult.builder().
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected LocalDateTime obtenerFechaActual() {
        // TODO implementar obtencion centralizada de fecha actual.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }
}
