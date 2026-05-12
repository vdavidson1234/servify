package com.servify.solicitudes.application.service;

import com.servify.solicitudes.application.dto.CancelarSolicitudServicioCommand;
import com.servify.solicitudes.application.port.in.CancelarSolicitudServicioUseCase;
import com.servify.solicitudes.application.port.out.SolicitudServicioRepositoryPort;
import com.servify.solicitudes.domain.model.SolicitudServicio;

import java.util.UUID;

public class CancelarSolicitudServicioService implements CancelarSolicitudServicioUseCase {

    private final SolicitudServicioRepositoryPort solicitudServicioRepositoryPort;

    public CancelarSolicitudServicioService(SolicitudServicioRepositoryPort solicitudServicioRepositoryPort) {
        this.solicitudServicioRepositoryPort = solicitudServicioRepositoryPort;
    }

    @Override
    public void cancelar(CancelarSolicitudServicioCommand command) {
        // TODO implementar cancelación de solicitud.
        // Debe:
        // - validar que el command no sea nulo
        // - validar que solicitudId y solicitanteId no sean nulos
        // - buscar la solicitud mediante SolicitudServicioRepositoryPort
        // - verificar que la solicitud exista
        // - verificar que la solicitud pertenezca al solicitante indicado
        // - verificar que la solicitud pueda cancelarse según las reglas del negocio
        // - ejecutar la cancelación sobre la entidad
        // - persistir la solicitud actualizada
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected SolicitudServicio obtenerSolicitudExistente(UUID solicitudId) {
        // TODO implementar búsqueda obligatoria de solicitud por id.
        // Debe recuperar la solicitud desde SolicitudServicioRepositoryPort
        // y lanzar la excepción correspondiente si no existe.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected void validarPertenenciaSolicitante(SolicitudServicio solicitudServicio, UUID solicitanteId) {
        // TODO implementar validación de pertenencia al solicitante.
        // Debe verificar que la solicitud pertenezca al usuario que intenta cancelarla.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected void validarCancelacionPermitida(SolicitudServicio solicitudServicio) {
        // TODO implementar validación previa a la cancelación.
        // Debe verificar que la solicitud no esté finalizada
        // y que cumpla las demás reglas del negocio para poder cancelarse.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected void persistirSolicitud(SolicitudServicio solicitudServicio) {
        // TODO implementar persistencia de la solicitud actualizada.
        // Debe delegar el guardado en SolicitudServicioRepositoryPort.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}