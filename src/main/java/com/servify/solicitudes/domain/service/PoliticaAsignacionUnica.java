package com.servify.solicitudes.domain.service;

import com.servify.solicitudes.domain.model.AsignacionServicio;
import com.servify.solicitudes.domain.model.SolicitudServicio;

import java.util.List;

//expresa una regla central del negocio
//no pertenece a una sola entidad
//puede ser usada por casos de uso y por el motor de distribución/asignación

public class PoliticaAsignacionUnica {

    public boolean puedeAsignarse(SolicitudServicio solicitud,
                                  List<AsignacionServicio> asignacionesExistentes) {
        // TODO implementar validación de asignación única.
        // Debe verificar, como mínimo:
        // - que la solicitud exista
        // - que la solicitud no esté cancelada ni finalizada
        // - que no exista ya una asignación activa o válida para esa misma solicitud
        // - que el flujo actual permita generar una nueva asignación
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean existeAsignacionVigente(List<AsignacionServicio> asignacionesExistentes) {
        // TODO implementar verificación de asignación vigente.
        // Debe recorrer las asignaciones existentes y detectar si alguna
        // se encuentra en un estado que impida crear una nueva asignación
        // para la misma solicitud.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean requiereCerrarDistribucionesRestantes() {
        // TODO implementar regla para cierre de distribuciones restantes.
        // Debe devolver true cuando, una vez consolidada una asignación válida,
        // el sistema deba cerrar automáticamente las demás distribuciones activas
        // asociadas a la misma solicitud.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}