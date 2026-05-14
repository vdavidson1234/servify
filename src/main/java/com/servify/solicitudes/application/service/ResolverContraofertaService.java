package com.servify.solicitudes.application.service;

import com.servify.solicitudes.application.dto.ContraofertaResult;
import com.servify.solicitudes.application.dto.ResolverContraofertaCommand;
import com.servify.solicitudes.application.dto.TipoDecisionSolicitud;
import com.servify.solicitudes.application.port.in.ResolverContraofertaUseCase;
import com.servify.solicitudes.application.port.out.ContraofertaRepositoryPort;
import com.servify.solicitudes.application.port.out.DistribucionSolicitudRepositoryPort;
import com.servify.solicitudes.application.port.out.SolicitudServicioRepositoryPort;
import com.servify.solicitudes.domain.model.Contraoferta;
import com.servify.solicitudes.domain.model.DistribucionSolicitud;
import com.servify.solicitudes.domain.model.SolicitudServicio;

import java.time.LocalDateTime;
import java.util.UUID;

public class ResolverContraofertaService implements ResolverContraofertaUseCase {

    private final ContraofertaRepositoryPort contraofertaRepositoryPort;
    private final DistribucionSolicitudRepositoryPort distribucionSolicitudRepositoryPort;
    private final SolicitudServicioRepositoryPort solicitudServicioRepositoryPort;

    public ResolverContraofertaService(ContraofertaRepositoryPort contraofertaRepositoryPort,
                                       DistribucionSolicitudRepositoryPort distribucionSolicitudRepositoryPort,
                                       SolicitudServicioRepositoryPort solicitudServicioRepositoryPort) {
        this.contraofertaRepositoryPort = contraofertaRepositoryPort;
        this.distribucionSolicitudRepositoryPort = distribucionSolicitudRepositoryPort;
        this.solicitudServicioRepositoryPort = solicitudServicioRepositoryPort;
    }

    @Override
    public ContraofertaResult resolver(ResolverContraofertaCommand command) {
        // TODO implementar resolucion de contraoferta.
        // Debe:
        // - validar command y decision
        // - recuperar contraoferta, distribucion y solicitud asociadas
        // - validar que la solicitud pertenezca al solicitante indicado
        // - validar que la contraoferta continue pendiente y resoluble
        // - aceptar o rechazar la contraoferta segun TipoDecisionSolicitud
        // - si se acepta, dejar la distribucion en estado compatible con asignacion
        // - persistir contraoferta y distribucion si corresponde
        // - devolver ContraofertaResult usando builder
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected Contraoferta obtenerContraofertaExistente(UUID contraofertaId) {
        // TODO implementar busqueda obligatoria de contraoferta.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected DistribucionSolicitud obtenerDistribucionExistente(UUID distribucionSolicitudId) {
        // TODO implementar busqueda obligatoria de distribucion.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected SolicitudServicio obtenerSolicitudExistente(UUID solicitudId) {
        // TODO implementar busqueda obligatoria de solicitud.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected void validarSolicitantePuedeResolver(SolicitudServicio solicitudServicio,
                                                   UUID solicitanteId) {
        // TODO implementar validacion de solicitante autorizado para resolver la contraoferta.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected void aplicarDecision(Contraoferta contraoferta,
                                   DistribucionSolicitud distribucionSolicitud,
                                   TipoDecisionSolicitud decision,
                                   LocalDateTime fechaResolucion) {
        // TODO implementar aplicacion de decision ACEPTAR o RECHAZAR.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected ContraofertaResult construirResultado(Contraoferta contraoferta) {
        // TODO implementar mapeo de Contraoferta a ContraofertaResult.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected LocalDateTime obtenerFechaActual() {
        // TODO implementar obtencion centralizada de fecha actual.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }
}
