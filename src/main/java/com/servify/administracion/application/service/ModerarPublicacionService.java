package com.servify.administracion.application.service;

import com.servify.administracion.application.dto.ModerarPublicacionCommand;
import com.servify.administracion.application.port.in.ModerarPublicacionUseCase;
import com.servify.administracion.application.port.out.PublicacionModerablePort;
import com.servify.administracion.application.port.out.UsuarioAdministrablePort;

import java.util.UUID;

public class ModerarPublicacionService implements ModerarPublicacionUseCase {

    private final PublicacionModerablePort publicacionModerablePort;
    private final UsuarioAdministrablePort usuarioAdministrablePort;

    public ModerarPublicacionService(PublicacionModerablePort publicacionModerablePort,
                                     UsuarioAdministrablePort usuarioAdministrablePort) {
        this.publicacionModerablePort = publicacionModerablePort;
        this.usuarioAdministrablePort = usuarioAdministrablePort;
    }

    @Override
    public void moderar(ModerarPublicacionCommand command) {
        // TODO implementar moderacion de publicacion.
        // Debe:
        // - validar command
        // - validar administrador mediante UsuarioAdministrablePort
        // - verificar existencia de la publicacion mediante PublicacionModerablePort
        // - validar estado destino permitido para moderacion
        // - delegar cambio de estado a PublicacionModerablePort
        // - no duplicar gestion de categorias ni reglas internas del modulo publicaciones
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected void validarAdministrador(UUID administradorId) {
        // TODO implementar validacion de administrador.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected void validarPublicacion(UUID publicacionServicioId) {
        // TODO implementar validacion de existencia de publicacion.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected void validarEstadoDestino(String estadoDestino) {
        // TODO implementar validacion de estado destino permitido por moderacion.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }
}
