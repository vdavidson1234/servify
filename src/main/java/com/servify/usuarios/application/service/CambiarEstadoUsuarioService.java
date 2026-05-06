package com.servify.usuarios.application.service;

import com.servify.usuarios.application.dto.CambiarEstadoUsuarioCommand;
import com.servify.usuarios.application.port.in.CambiarEstadoUsuarioUseCase;
import com.servify.usuarios.application.port.out.UsuarioRepositoryPort;
import com.servify.usuarios.domain.enumtype.EstadoUsuario;
import com.servify.usuarios.domain.model.Usuario;

import java.util.UUID;

//Es la implementación del caso de uso que cambia el estado de un usuario.
//Coordina:
//búsqueda del usuario
//validación básica de entrada
//aplicación del cambio de estado en la entidad
//persistencia del usuario actualizado

public class CambiarEstadoUsuarioService implements CambiarEstadoUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public CambiarEstadoUsuarioService(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public void cambiarEstado(CambiarEstadoUsuarioCommand command) {
        // TODO implementar cambio de estado del usuario.
        // Debe:
        // - validar que el command no sea nulo
        // - verificar que el usuario exista
        // - validar que el nuevo estado no sea nulo
        // - aplicar la transición correspondiente sobre la entidad Usuario
        // - persistir el usuario actualizado mediante UsuarioRepositoryPort
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected Usuario obtenerUsuarioExistente(UUID usuarioId) {
        // TODO implementar búsqueda obligatoria de usuario por id.
        // Debe recuperar el usuario desde UsuarioRepositoryPort
        // y lanzar la excepción correspondiente si no existe.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected void aplicarNuevoEstado(Usuario usuario, EstadoUsuario nuevoEstado) {
        // TODO implementar aplicación del nuevo estado.
        // Debe resolver la transición según el valor recibido:
        // - ACTIVO -> usuario.activar()
        // - SUSPENDIDO -> usuario.suspender()
        // - BLOQUEADO -> usuario.bloquear()
        // - INACTIVO -> usuario.desactivar()
        // También debe validar que la transición sea coherente con las reglas del dominio.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected void persistirUsuario(Usuario usuario) {
        // TODO implementar persistencia del usuario actualizado.
        // Debe delegar el guardado en UsuarioRepositoryPort.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}