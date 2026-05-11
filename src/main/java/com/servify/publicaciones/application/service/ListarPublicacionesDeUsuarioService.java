package com.servify.publicaciones.application.service;

import com.servify.publicaciones.application.dto.PublicacionServicioResult;
import com.servify.publicaciones.application.port.in.ListarPublicacionesDeUsuarioUseCase;
import com.servify.publicaciones.application.port.out.PublicacionServicioRepositoryPort;
import com.servify.publicaciones.domain.model.PublicacionServicio;

import java.util.List;
import java.util.UUID;

/**
 * Servicio de aplicacion que lista publicaciones de un usuario.
 */
public class ListarPublicacionesDeUsuarioService implements ListarPublicacionesDeUsuarioUseCase {

    private final PublicacionServicioRepositoryPort publicacionServicioRepositoryPort;

    public ListarPublicacionesDeUsuarioService(PublicacionServicioRepositoryPort publicacionServicioRepositoryPort) {
        this.publicacionServicioRepositoryPort = publicacionServicioRepositoryPort;
    }

    @Override
    public List<PublicacionServicioResult> listarPorUsuarioId(UUID usuarioId) {
        // TODO implementar listado de publicaciones por usuario.
        // Debe validar usuarioId, consultar el repositorio y mapear cada resultado.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected PublicacionServicioResult construirResultado(PublicacionServicio publicacionServicio) {
        // TODO implementar mapeo con PublicacionServicioResult.builder().
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }
}
