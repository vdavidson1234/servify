package com.servify.solicitudes.application.service;

import com.servify.solicitudes.application.dto.CalificarServicioCommand;
import com.servify.solicitudes.application.port.in.CalificarServicioUseCase;
import com.servify.solicitudes.application.port.out.AsignacionServicioRepositoryPort;
import com.servify.solicitudes.application.port.out.CalificacionRepositoryPort;
import com.servify.solicitudes.application.port.out.SolicitudServicioRepositoryPort;
import com.servify.solicitudes.domain.model.AsignacionServicio;
import com.servify.solicitudes.domain.model.Calificacion;
import com.servify.solicitudes.domain.model.SolicitudServicio;
import com.servify.solicitudes.domain.service.PoliticaCalificacion;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * Es la implementación del caso de uso mediante el cual el solicitante califica al prestador una vez finalizado el servicio.
 * El MVP exige que:
 * solo se califique una solicitud finalizada
 * la calificación sea de 1 a 5 estrellas
 * y que no exista más de una calificación por solicitud
 */

public class CalificarServicioService implements CalificarServicioUseCase {

    private final CalificacionRepositoryPort calificacionRepositoryPort;
    private final SolicitudServicioRepositoryPort solicitudServicioRepositoryPort;
    private final AsignacionServicioRepositoryPort asignacionServicioRepositoryPort;
    private final PoliticaCalificacion politicaCalificacion;

    public CalificarServicioService(CalificacionRepositoryPort calificacionRepositoryPort,
                                    SolicitudServicioRepositoryPort solicitudServicioRepositoryPort,
                                    AsignacionServicioRepositoryPort asignacionServicioRepositoryPort,
                                    PoliticaCalificacion politicaCalificacion) {
        this.calificacionRepositoryPort = calificacionRepositoryPort;
        this.solicitudServicioRepositoryPort = solicitudServicioRepositoryPort;
        this.asignacionServicioRepositoryPort = asignacionServicioRepositoryPort;
        this.politicaCalificacion = politicaCalificacion;
    }

    @Override
    public void calificar(CalificarServicioCommand command) {
        // TODO implementar calificación del servicio.
        // Debe:
        // - validar que el command no sea nulo
        // - validar que solicitudId, asignacionServicioId, solicitanteId, prestadorId y puntaje no sean nulos
        // - verificar que la solicitud exista
        // - verificar que la asignación exista
        // - verificar que la asignación corresponda a la solicitud indicada
        // - verificar que el solicitante sea quien realmente realizó la solicitud
        // - verificar que el prestador sea el asignado a la prestación
        // - verificar mediante PoliticaCalificacion que la solicitud pueda calificarse
        // - construir la entidad Calificacion
        // - persistir la calificación
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected SolicitudServicio obtenerSolicitudExistente(UUID solicitudId) {
        // TODO implementar búsqueda obligatoria de solicitud por id.
        // Debe recuperar la solicitud desde SolicitudServicioRepositoryPort
        // y lanzar la excepción correspondiente si no existe.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected AsignacionServicio obtenerAsignacionExistente(UUID asignacionServicioId) {
        // TODO implementar búsqueda obligatoria de asignación por id.
        // Debe recuperar la asignación desde AsignacionServicioRepositoryPort
        // y lanzar la excepción correspondiente si no existe.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected void validarCorrespondenciaSolicitudAsignacion(SolicitudServicio solicitudServicio,
                                                             AsignacionServicio asignacionServicio) {
        // TODO implementar validación de correspondencia entre solicitud y asignación.
        // Debe verificar que la asignación pertenezca a la solicitud indicada.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected void validarSolicitante(SolicitudServicio solicitudServicio, UUID solicitanteId) {
        // TODO implementar validación del solicitante.
        // Debe verificar que el usuario que intenta calificar
        // sea el solicitante real de la solicitud.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected void validarPrestadorAsignado(AsignacionServicio asignacionServicio, UUID prestadorId) {
        // TODO implementar validación del prestador asignado.
        // Debe verificar que el prestador calificado
        // coincida con el prestador vinculado a la asignación.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected void validarCalificacionPermitida(SolicitudServicio solicitudServicio,
                                                UUID prestadorId,
                                                Integer puntaje) {
        // TODO implementar validación integral de la calificación.
        // Debe:
        // - verificar que la solicitud esté finalizada
        // - verificar que no exista ya una calificación para la solicitud
        // - verificar que el prestador corresponda al asignado
        // - verificar que el puntaje sea válido
        // apoyándose en PoliticaCalificacion.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected Calificacion construirCalificacion(CalificarServicioCommand command,
                                                 LocalDateTime fechaCalificacion) {
        // TODO implementar construcción de la calificación.
        // Debe crear la entidad Calificacion con:
        // - id nuevo
        // - solicitudId
        // - asignacionServicioId
        // - solicitanteId
        // - prestadorId
        // - puntaje
        // - fecha actual de calificación
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected void persistirCalificacion(Calificacion calificacion) {
        // TODO implementar persistencia de la calificación.
        // Debe delegar el guardado en CalificacionRepositoryPort.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected UUID generarIdCalificacion() {
        // TODO implementar generación de identificador de calificación.
        // Por el momento puede resolverse con UUID aleatorio si esa es la estrategia elegida.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected LocalDateTime obtenerFechaActual() {
        // TODO implementar obtención de fecha actual.
        // Debe centralizar la fecha/hora usada al registrar la calificación.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}