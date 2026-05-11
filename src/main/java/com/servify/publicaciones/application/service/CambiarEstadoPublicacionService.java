package com.servify.publicaciones.application.service;

import com.servify.publicaciones.application.dto.CambiarEstadoPublicacionCommand;
import com.servify.publicaciones.application.dto.PublicacionServicioResult;
import com.servify.publicaciones.application.port.in.CambiarEstadoPublicacionUseCase;
import com.servify.publicaciones.application.port.out.PublicacionServicioRepositoryPort;
import com.servify.publicaciones.domain.enumtype.EstadoPublicacion;
import com.servify.publicaciones.domain.model.PublicacionServicio;

import java.util.UUID;

/**
 * Servicio de aplicacion que ejecuta el cambio de estado de publicacion.
 */
public class CambiarEstadoPublicacionService implements CambiarEstadoPublicacionUseCase {

    private final PublicacionServicioRepositoryPort publicacionServicioRepositoryPort;

    public CambiarEstadoPublicacionService(PublicacionServicioRepositoryPort publicacionServicioRepositoryPort) {
        this.publicacionServicioRepositoryPort = publicacionServicioRepositoryPort;
    }

    @Override
    public PublicacionServicioResult cambiarEstado(CambiarEstadoPublicacionCommand command) {
        // TODO implementar cambio unificado de estado de publicacion.
        // Debe:
        // - validar que el command no sea nulo
        // - recuperar la publicacion existente
        // - validar pertenencia o permisos administrativos segun estado destino
        // - aplicar transicion ACTIVA, INACTIVA, PAUSADA, BLOQUEADA o ELIMINADA
        // - persistir mediante PublicacionServicioRepositoryPort
        // - devolver PublicacionServicioResult usando builder
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected PublicacionServicio obtenerPublicacionExistente(UUID publicacionServicioId) {
        // TODO implementar busqueda obligatoria de publicacion.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected void validarCambioPermitido(PublicacionServicio publicacionServicio,
                                          CambiarEstadoPublicacionCommand command) {
        // TODO implementar validacion de permisos y transicion.
        // Debe diferenciar acciones del publicador y acciones administrativas,
        // especialmente para el estado BLOQUEADA.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected void aplicarEstadoDestino(PublicacionServicio publicacionServicio,
                                        EstadoPublicacion estadoDestino) {
        // TODO implementar despacho de transicion de estado.
        // Debe centralizar activacion, desactivacion, pausa, bloqueo y baja logica.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected PublicacionServicioResult construirResultado(PublicacionServicio publicacionServicio) {
        // TODO implementar mapeo con PublicacionServicioResult.builder().
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }
}
