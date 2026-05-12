package com.servify.solicitudes.application.service;

import com.servify.solicitudes.application.dto.ResponderDistribucionSolicitudCommand;
import com.servify.solicitudes.application.dto.TipoRespuestaDistribucion;
import com.servify.solicitudes.application.port.in.ResponderDistribucionSolicitudUseCase;
import com.servify.solicitudes.application.port.out.DistribucionSolicitudRepositoryPort;
import com.servify.solicitudes.application.port.out.SolicitudServicioRepositoryPort;
import com.servify.solicitudes.domain.model.DistribucionSolicitud;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Acá metí también SolicitudServicioRepositoryPort porque la distribución no debería responderse a ciegas;
   conviene validar que la solicitud asociada siga activa y disponible
 */

public class ResponderDistribucionSolicitudService implements ResponderDistribucionSolicitudUseCase {

    private final DistribucionSolicitudRepositoryPort distribucionSolicitudRepositoryPort;
    private final SolicitudServicioRepositoryPort solicitudServicioRepositoryPort;

    public ResponderDistribucionSolicitudService(
            DistribucionSolicitudRepositoryPort distribucionSolicitudRepositoryPort,
            SolicitudServicioRepositoryPort solicitudServicioRepositoryPort
    ) {
        this.distribucionSolicitudRepositoryPort = distribucionSolicitudRepositoryPort;
        this.solicitudServicioRepositoryPort = solicitudServicioRepositoryPort;
    }

    @Override
    public void responder(ResponderDistribucionSolicitudCommand command) {
        // TODO implementar respuesta de distribución por parte del prestador.
        // Debe:
        // - validar que el command no sea nulo
        // - validar que distribucionSolicitudId, prestadorId y tipoRespuesta no sean nulos
        // - buscar la distribución mediante DistribucionSolicitudRepositoryPort
        // - verificar que la distribución exista
        // - verificar que pertenezca al prestador indicado
        // - verificar que la distribución pueda ser respondida
        // - verificar que la solicitud asociada siga activa y disponible para respuesta
        // - aplicar la respuesta correspondiente (ACEPTAR o RECHAZAR)
        // - registrar la fecha de respuesta
        // - persistir la distribución actualizada
        //
        // Si la respuesta es ACEPTAR, más adelante este caso de uso podrá coordinar
        // el inicio del proceso de asignación efectiva.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected DistribucionSolicitud obtenerDistribucionExistente(UUID distribucionSolicitudId) {
        // TODO implementar búsqueda obligatoria de distribución por id.
        // Debe recuperar la distribución desde DistribucionSolicitudRepositoryPort
        // y lanzar la excepción correspondiente si no existe.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected void validarPertenenciaPrestador(DistribucionSolicitud distribucionSolicitud, UUID prestadorId) {
        // TODO implementar validación de pertenencia al prestador.
        // Debe verificar que la distribución corresponda al prestador que intenta responderla.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected void validarRespuestaPermitida(DistribucionSolicitud distribucionSolicitud) {
        // TODO implementar validación previa a la respuesta.
        // Debe verificar que la distribución:
        // - no esté cerrada
        // - no esté expirada
        // - no haya sido respondida definitivamente
        // - y siga en un estado apto para responderse
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected void validarSolicitudAsociadaActiva(DistribucionSolicitud distribucionSolicitud) {
        // TODO implementar validación de solicitud asociada.
        // Debe verificar, utilizando SolicitudServicioRepositoryPort, que la solicitud
        // vinculada a la distribución siga activa y disponible para ser respondida.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected void aplicarRespuesta(DistribucionSolicitud distribucionSolicitud,
                                    TipoRespuestaDistribucion tipoRespuesta,
                                    LocalDateTime fechaRespuesta) {
        // TODO implementar aplicación de la respuesta.
        // Debe resolver la transición según el valor recibido:
        // - ACEPTAR -> distribucionSolicitud.aceptar(fechaRespuesta)
        // - RECHAZAR -> distribucionSolicitud.rechazar(fechaRespuesta)
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected void persistirDistribucion(DistribucionSolicitud distribucionSolicitud) {
        // TODO implementar persistencia de la distribución actualizada.
        // Debe delegar el guardado en DistribucionSolicitudRepositoryPort.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected LocalDateTime obtenerFechaActual() {
        // TODO implementar obtención de fecha actual.
        // Debe centralizar la fecha/hora usada al registrar la respuesta del prestador.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}