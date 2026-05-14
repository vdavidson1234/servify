package com.servify.solicitudes.application.service;

import com.servify.solicitudes.application.dto.AsignacionServicioResult;
import com.servify.solicitudes.application.dto.ContraofertaResult;
import com.servify.solicitudes.application.dto.EstadoAsignacionSolicitudResult;
import com.servify.solicitudes.application.port.in.ObtenerEstadoAsignacionSolicitudUseCase;
import com.servify.solicitudes.application.port.out.AsignacionServicioRepositoryPort;
import com.servify.solicitudes.application.port.out.ContraofertaRepositoryPort;
import com.servify.solicitudes.application.port.out.DistribucionSolicitudRepositoryPort;
import com.servify.solicitudes.application.port.out.SolicitudServicioRepositoryPort;
import com.servify.solicitudes.domain.model.AsignacionServicio;
import com.servify.solicitudes.domain.model.Contraoferta;
import com.servify.solicitudes.domain.model.DistribucionSolicitud;
import com.servify.solicitudes.domain.model.SolicitudServicio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ObtenerEstadoAsignacionSolicitudService implements ObtenerEstadoAsignacionSolicitudUseCase {

    private final SolicitudServicioRepositoryPort solicitudServicioRepositoryPort;
    private final DistribucionSolicitudRepositoryPort distribucionSolicitudRepositoryPort;
    private final AsignacionServicioRepositoryPort asignacionServicioRepositoryPort;
    private final ContraofertaRepositoryPort contraofertaRepositoryPort;

    public ObtenerEstadoAsignacionSolicitudService(SolicitudServicioRepositoryPort solicitudServicioRepositoryPort,
                                                   DistribucionSolicitudRepositoryPort distribucionSolicitudRepositoryPort,
                                                   AsignacionServicioRepositoryPort asignacionServicioRepositoryPort,
                                                   ContraofertaRepositoryPort contraofertaRepositoryPort) {
        this.solicitudServicioRepositoryPort = solicitudServicioRepositoryPort;
        this.distribucionSolicitudRepositoryPort = distribucionSolicitudRepositoryPort;
        this.asignacionServicioRepositoryPort = asignacionServicioRepositoryPort;
        this.contraofertaRepositoryPort = contraofertaRepositoryPort;
    }

    @Override
    public EstadoAsignacionSolicitudResult obtenerEstado(UUID solicitudId) {
        // TODO implementar consulta de estado de asignacion.
        // Debe:
        // - validar solicitudId
        // - recuperar solicitud existente
        // - consultar asignacion por solicitud si existe
        // - consultar distribuciones activas de la solicitud
        // - consultar contraofertas pendientes asociadas a sus distribuciones
        // - devolver EstadoAsignacionSolicitudResult usando builder
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected SolicitudServicio obtenerSolicitudExistente(UUID solicitudId) {
        // TODO implementar busqueda obligatoria de solicitud.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected Optional<AsignacionServicio> obtenerAsignacion(UUID solicitudId) {
        // TODO implementar busqueda opcional de asignacion por solicitud.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected List<DistribucionSolicitud> obtenerDistribucionesActivas(UUID solicitudId) {
        // TODO implementar consulta de distribuciones activas por solicitud.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected List<Contraoferta> obtenerContraofertasPendientes(List<DistribucionSolicitud> distribuciones) {
        // TODO implementar consulta de contraofertas pendientes asociadas a distribuciones.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected AsignacionServicioResult construirAsignacionResult(AsignacionServicio asignacionServicio) {
        // TODO implementar mapeo de AsignacionServicio a AsignacionServicioResult.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected ContraofertaResult construirContraofertaResult(Contraoferta contraoferta) {
        // TODO implementar mapeo de Contraoferta a ContraofertaResult.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected EstadoAsignacionSolicitudResult construirResultado(SolicitudServicio solicitudServicio,
                                                                 Optional<AsignacionServicio> asignacionServicio,
                                                                 List<Contraoferta> contraofertasPendientes,
                                                                 Integer distribucionesActivas) {
        // TODO implementar mapeo final de estado de asignacion.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }
}
