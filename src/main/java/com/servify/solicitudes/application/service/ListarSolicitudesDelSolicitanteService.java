package com.servify.solicitudes.application.service;

import com.servify.shared.domain.valueobject.DisponibilidadHoraria;
import com.servify.shared.domain.valueobject.Ubicacion;
import com.servify.solicitudes.application.dto.DisponibilidadHorariaResult;
import com.servify.solicitudes.application.dto.SolicitudServicioResult;
import com.servify.solicitudes.application.dto.UbicacionSolicitudResult;
import com.servify.solicitudes.application.port.in.ListarSolicitudesDelSolicitanteUseCase;
import com.servify.solicitudes.application.port.out.SolicitudServicioRepositoryPort;
import com.servify.solicitudes.domain.model.SolicitudServicio;

import java.util.List;
import java.util.UUID;

public class ListarSolicitudesDelSolicitanteService implements ListarSolicitudesDelSolicitanteUseCase {

    private final SolicitudServicioRepositoryPort solicitudServicioRepositoryPort;

    public ListarSolicitudesDelSolicitanteService(SolicitudServicioRepositoryPort solicitudServicioRepositoryPort) {
        this.solicitudServicioRepositoryPort = solicitudServicioRepositoryPort;
    }

    @Override
    public List<SolicitudServicioResult> listarPorSolicitanteId(UUID solicitanteId) {
        // TODO implementar listado de solicitudes del solicitante.
        // Debe:
        // - validar que el solicitanteId no sea nulo
        // - consultar las solicitudes mediante SolicitudServicioRepositoryPort
        // - mapear cada solicitud a SolicitudServicioResult
        // - devolver lista vacía si no existen solicitudes asociadas
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