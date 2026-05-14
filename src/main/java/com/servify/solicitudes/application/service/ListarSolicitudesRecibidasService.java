package com.servify.solicitudes.application.service;

import com.servify.shared.domain.valueobject.DisponibilidadHoraria;
import com.servify.shared.domain.valueobject.Ubicacion;
import com.servify.solicitudes.application.dto.DisponibilidadHorariaResult;
import com.servify.solicitudes.application.dto.SolicitudServicioResult;
import com.servify.solicitudes.application.dto.UbicacionSolicitudResult;
import com.servify.solicitudes.application.port.in.ListarSolicitudesRecibidasUseCase;
import com.servify.solicitudes.application.port.out.DistribucionSolicitudRepositoryPort;
import com.servify.solicitudes.application.port.out.SolicitudServicioRepositoryPort;
import com.servify.solicitudes.domain.model.DistribucionSolicitud;
import com.servify.solicitudes.domain.model.SolicitudServicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 Acá hay un detalle importante: este caso de uso devuelve SolicitudServicioResult, o sea la solicitud general.
 Más adelante, si querés mostrar también el estado individual de la distribución para el prestador,
 ahí convendría crear un DTO más rico, por ejemplo algo tipo: SolicitudRecibidaResult
 que combine:
 - datos de la solicitud
 - estado de la distribución
 - fecha de envío
 - fecha de expiración
 */

public class ListarSolicitudesRecibidasService implements ListarSolicitudesRecibidasUseCase {

    private final DistribucionSolicitudRepositoryPort distribucionSolicitudRepositoryPort;
    private final SolicitudServicioRepositoryPort solicitudServicioRepositoryPort;

    public ListarSolicitudesRecibidasService(DistribucionSolicitudRepositoryPort distribucionSolicitudRepositoryPort,
                                             SolicitudServicioRepositoryPort solicitudServicioRepositoryPort) {
        this.distribucionSolicitudRepositoryPort = distribucionSolicitudRepositoryPort;
        this.solicitudServicioRepositoryPort = solicitudServicioRepositoryPort;
    }

    @Override
    public List<SolicitudServicioResult> listarPorPrestadorId(UUID prestadorId) {
        // TODO implementar listado de solicitudes recibidas por el prestador.
        // Debe:
        // - validar que el prestadorId no sea nulo
        // - consultar las distribuciones del prestador mediante DistribucionSolicitudRepositoryPort
        // - obtener las solicitudes asociadas a cada distribución
        // - mapear cada solicitud a SolicitudServicioResult
        // - evitar duplicados si por alguna razón existieran múltiples distribuciones
        // - devolver lista vacía si no existen solicitudes recibidas
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected List<DistribucionSolicitud> obtenerDistribucionesDelPrestador(UUID prestadorId) {
        // TODO implementar obtención de distribuciones del prestador.
        // Debe delegar la consulta en DistribucionSolicitudRepositoryPort.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected List<SolicitudServicio> obtenerSolicitudesAsociadas(List<DistribucionSolicitud> distribuciones) {
        // TODO implementar obtención de solicitudes asociadas a distribuciones.
        // Debe recorrer las distribuciones y recuperar cada solicitud desde
        // SolicitudServicioRepositoryPort, evitando nulos y duplicados.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected Optional<SolicitudServicio> obtenerSolicitudPorId(UUID solicitudId) {
        // TODO implementar obtención opcional de solicitud por id.
        // Debe delegar la búsqueda en SolicitudServicioRepositoryPort.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected List<SolicitudServicioResult> construirResultados(List<SolicitudServicio> solicitudes) {
        // TODO implementar mapeo de lista de solicitudes a resultados.
        // Debe transformar cada SolicitudServicio en SolicitudServicioResult.
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

    protected boolean yaFueAgregada(UUID solicitudId, List<SolicitudServicio> solicitudes) {
        // TODO implementar validación de duplicados.
        // Debe verificar si la solicitud ya fue agregada a la colección resultado.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}