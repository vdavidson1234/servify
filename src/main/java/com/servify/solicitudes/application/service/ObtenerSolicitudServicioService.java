package com.servify.solicitudes.application.service;

import com.servify.shared.domain.valueobject.DisponibilidadHoraria;
import com.servify.shared.domain.valueobject.Ubicacion;
import com.servify.solicitudes.application.dto.DisponibilidadHorariaResult;
import com.servify.solicitudes.application.dto.SolicitudServicioResult;
import com.servify.solicitudes.application.dto.UbicacionSolicitudResult;
import com.servify.solicitudes.application.port.in.ObtenerSolicitudServicioUseCase;
import com.servify.solicitudes.application.port.out.SolicitudServicioRepositoryPort;
import com.servify.solicitudes.domain.model.SolicitudServicio;

import java.util.Optional;
import java.util.UUID;

public class ObtenerSolicitudServicioService implements ObtenerSolicitudServicioUseCase {

    private final SolicitudServicioRepositoryPort solicitudServicioRepositoryPort;

    public ObtenerSolicitudServicioService(SolicitudServicioRepositoryPort solicitudServicioRepositoryPort) {
        this.solicitudServicioRepositoryPort = solicitudServicioRepositoryPort;
    }

    @Override
    public Optional<SolicitudServicioResult> obtenerPorId(UUID solicitudId) {
        // TODO implementar consulta de solicitud por id.
        // Debe:
        // - validar que el solicitudId no sea nulo
        // - consultar la solicitud mediante SolicitudServicioRepositoryPort
        // - mapear el resultado a SolicitudServicioResult si existe
        // - devolver Optional.empty() si no existe una solicitud con ese identificador
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
        // Debe contemplar el caso de ubicación nula si el flujo lo permitiera.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected DisponibilidadHorariaResult construirDisponibilidadResult(DisponibilidadHoraria disponibilidadHoraria) {
        // TODO implementar mapeo de DisponibilidadHoraria a DisponibilidadHorariaResult.
        // Debe contemplar el caso de disponibilidad nula si el flujo lo permitiera.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}