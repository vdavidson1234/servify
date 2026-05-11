package com.servify.solicitudes.application.service;

import com.servify.shared.domain.valueobject.DisponibilidadHoraria;
import com.servify.shared.domain.valueobject.Ubicacion;
import com.servify.solicitudes.application.dto.CrearSolicitudServicioCommand;
import com.servify.solicitudes.application.dto.DisponibilidadHorariaResult;
import com.servify.solicitudes.application.dto.SolicitudServicioResult;
import com.servify.solicitudes.application.dto.UbicacionSolicitudResult;
import com.servify.solicitudes.application.port.in.CrearSolicitudServicioUseCase;
import com.servify.solicitudes.application.port.out.SolicitudServicioRepositoryPort;
import com.servify.solicitudes.domain.enumtype.EstadoSolicitud;
import com.servify.solicitudes.domain.model.SolicitudServicio;

import java.time.LocalDateTime;
import java.util.UUID;

public class CrearSolicitudServicioService implements CrearSolicitudServicioUseCase {

    private final SolicitudServicioRepositoryPort solicitudServicioRepositoryPort;

    public CrearSolicitudServicioService(SolicitudServicioRepositoryPort solicitudServicioRepositoryPort) {
        this.solicitudServicioRepositoryPort = solicitudServicioRepositoryPort;
    }

    @Override
    public SolicitudServicioResult crear(CrearSolicitudServicioCommand command) {
        // TODO implementar creación de solicitud de servicio.
        // Debe:
        // - validar que el command no sea nulo
        // - validar que solicitanteId y categoriaServicioId no sean nulos
        // - validar modalidad, ubicación y disponibilidad requerida
        // - construir la entidad SolicitudServicio con estado inicial válido
        // - persistir la solicitud mediante SolicitudServicioRepositoryPort
        // - mapear la entidad persistida a SolicitudServicioResult
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected SolicitudServicio construirSolicitud(CrearSolicitudServicioCommand command) {
        // TODO implementar construcción inicial de la solicitud.
        // Debe crear la entidad SolicitudServicio con:
        // - id nuevo
        // - solicitanteId recibido
        // - categoriaServicioId recibido
        // - modalidad, ubicación y disponibilidad requerida
        // - descripción de necesidad
        // - precio de referencia
        // - estado inicial adecuado
        // - fecha actual de creación
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected SolicitudServicioResult construirResultado(SolicitudServicio solicitudServicio) {
        // TODO implementar mapeo de SolicitudServicio a SolicitudServicioResult.
        // Debe incluir:
        // - id de la solicitud
        // - solicitanteId
        // - categoriaServicioId
        // - modalidad
        // - ubicación mapeada a UbicacionSolicitudResult
        // - disponibilidad requerida mapeada a DisponibilidadHorariaResult
        // - descripción de necesidad
        // - precio de referencia
        // - estado
        // - fecha de solicitud
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected UbicacionSolicitudResult construirUbicacionResult(Ubicacion ubicacion) {
        // TODO implementar mapeo de Ubicacion a UbicacionSolicitudResult.
        // Debe contemplar el caso de ubicación nula si el flujo llegara a permitirlo.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected DisponibilidadHorariaResult construirDisponibilidadResult(DisponibilidadHoraria disponibilidadHoraria) {
        // TODO implementar mapeo de DisponibilidadHoraria a DisponibilidadHorariaResult.
        // Debe contemplar el caso de disponibilidad nula si el flujo llegara a permitirlo.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected EstadoSolicitud obtenerEstadoInicial() {
        // TODO implementar definición del estado inicial de la solicitud.
        // Debe devolver el estado con el que una solicitud recién creada
        // debe iniciar su ciclo de vida.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected UUID generarIdSolicitud() {
        // TODO implementar generación de identificador de solicitud.
        // Por el momento puede resolverse con UUID aleatorio si esa es la estrategia elegida.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected LocalDateTime obtenerFechaActual() {
        // TODO implementar obtención de fecha actual.
        // Debe centralizar la fecha/hora usada al crear la solicitud.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}