package com.servify.usuarios.application.service;

import com.servify.shared.domain.valueobject.Ubicacion;
import com.servify.usuarios.application.dto.PerfilUsuarioResult;
import com.servify.usuarios.application.dto.UbicacionResult;
import com.servify.usuarios.application.port.in.ObtenerPerfilUsuarioUseCase;
import com.servify.usuarios.application.port.out.PerfilUsuarioRepositoryPort;
import com.servify.usuarios.domain.model.PerfilUsuario;

import java.util.Optional;
import java.util.UUID;

public class ObtenerPerfilUsuarioService implements ObtenerPerfilUsuarioUseCase {

    private final PerfilUsuarioRepositoryPort perfilUsuarioRepositoryPort;

    public ObtenerPerfilUsuarioService(PerfilUsuarioRepositoryPort perfilUsuarioRepositoryPort) {
        this.perfilUsuarioRepositoryPort = perfilUsuarioRepositoryPort;
    }

    @Override
    public Optional<PerfilUsuarioResult> obtenerPorUsuarioId(UUID usuarioId) {
        // TODO implementar consulta de perfil por usuarioId.
        // Debe:
        // - validar que el usuarioId no sea nulo
        // - consultar el perfil mediante PerfilUsuarioRepositoryPort
        // - mapear el resultado a PerfilUsuarioResult si existe
        // - devolver Optional.empty() si no existe perfil asociado
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    @Override
    public Optional<PerfilUsuarioResult> obtenerPorPerfilId(UUID perfilId) {
        // TODO implementar consulta de perfil por perfilId.
        // Debe:
        // - validar que el perfilId no sea nulo
        // - consultar el perfil mediante PerfilUsuarioRepositoryPort
        // - mapear el resultado a PerfilUsuarioResult si existe
        // - devolver Optional.empty() si no existe un perfil con ese identificador
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected PerfilUsuarioResult construirResultado(PerfilUsuario perfilUsuario) {
        // TODO implementar mapeo de PerfilUsuario a PerfilUsuarioResult.
        // Debe construir el resultado con:
        // - id del perfil
        // - usuarioId asociado
        // - nombre y apellido desde NombreCompleto
        // - edad
        // - foto de perfil
        // - ubicación mapeada a UbicacionResult
        // - descripción personal
        // - estado de perfil completo
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected UbicacionResult construirUbicacionResult(Ubicacion ubicacion) {
        // TODO implementar mapeo de Ubicacion a UbicacionResult.
        // Debe contemplar el caso de ubicación nula si el flujo lo permite.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}
