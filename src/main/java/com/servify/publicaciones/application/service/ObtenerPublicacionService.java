package com.servify.publicaciones.application.service;

import com.servify.publicaciones.application.dto.PublicacionServicioResult;
import com.servify.publicaciones.application.port.in.ObtenerPublicacionUseCase;
import com.servify.publicaciones.application.port.out.PublicacionServicioRepositoryPort;
import com.servify.publicaciones.domain.model.PublicacionServicio;

import java.util.Optional;
import java.util.UUID;

/**
 * Servicio de aplicacion que obtiene el detalle de una publicacion.
 */
public class ObtenerPublicacionService implements ObtenerPublicacionUseCase {

    private final PublicacionServicioRepositoryPort publicacionServicioRepositoryPort;

    public ObtenerPublicacionService(PublicacionServicioRepositoryPort publicacionServicioRepositoryPort) {
        this.publicacionServicioRepositoryPort = publicacionServicioRepositoryPort;
    }

    @Override
    public Optional<PublicacionServicioResult> obtenerPorId(UUID publicacionServicioId) {
        // TODO implementar consulta de publicacion por id.
        // Debe delegar en PublicacionServicioRepositoryPort y mapear con builder.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected PublicacionServicioResult construirResultado(PublicacionServicio publicacionServicio) {
        // TODO implementar mapeo con PublicacionServicioResult.builder().
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }
}
