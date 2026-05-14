package com.servify.autenticacion.application.service;

import com.servify.autenticacion.application.dto.CerrarSesionCommand;
import com.servify.autenticacion.application.port.in.CerrarSesionUseCase;
import com.servify.autenticacion.application.port.out.RefreshTokenRepositoryPort;
import com.servify.autenticacion.application.port.out.TokenProviderPort;
import com.servify.autenticacion.domain.model.RefreshToken;

import java.time.LocalDateTime;

public class CerrarSesionService implements CerrarSesionUseCase {

    private final RefreshTokenRepositoryPort refreshTokenRepositoryPort;
    private final TokenProviderPort tokenProviderPort;

    public CerrarSesionService(RefreshTokenRepositoryPort refreshTokenRepositoryPort,
                               TokenProviderPort tokenProviderPort) {
        this.refreshTokenRepositoryPort = refreshTokenRepositoryPort;
        this.tokenProviderPort = tokenProviderPort;
    }

    @Override
    public void cerrar(CerrarSesionCommand command) {
        // TODO implementar cierre de sesion.
        // Debe:
        // - validar command y refresh token
        // - hashear token recibido usando TokenProviderPort
        // - buscar RefreshToken por hash
        // - validar pertenencia al usuario indicado si corresponde
        // - revocar el RefreshToken
        // - persistir la revocacion
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected RefreshToken obtenerRefreshToken(String refreshTokenPlano) {
        // TODO implementar busqueda obligatoria de RefreshToken por hash.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected void validarCierrePermitido(RefreshToken refreshToken,
                                          CerrarSesionCommand command) {
        // TODO implementar validacion de cierre permitido para el usuario indicado.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected LocalDateTime obtenerFechaActual() {
        // TODO implementar obtencion centralizada de fecha actual.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }
}
