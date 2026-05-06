package com.servify.solicitudes.domain.service;

import com.servify.solicitudes.domain.model.ConfirmacionFinalizacion;
import com.servify.solicitudes.domain.model.SolicitudServicio;

import java.util.List;

//no pertenece solo a SolicitudServicio
//tampoco pertenece solo a ConfirmacionFinalizacion
//y la van a usar después los casos de uso de cierre/finalización

public class PoliticaFinalizacionMutua {

    public boolean puedeFinalizarse(SolicitudServicio solicitud,
                                    List<ConfirmacionFinalizacion> confirmaciones) {
        // TODO implementar validación de finalización mutua.
        // Debe verificar, como mínimo:
        // - que la solicitud exista
        // - que la solicitud esté en un estado que permita finalizarse
        // - que existan confirmaciones válidas de ambas partes
        // - que no se haya finalizado previamente
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean existeConfirmacionDelSolicitante(List<ConfirmacionFinalizacion> confirmaciones) {
        // TODO implementar verificación de confirmación del solicitante.
        // Debe recorrer las confirmaciones y detectar si existe una confirmación
        // válida emitida por el solicitante.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean existeConfirmacionDelPrestador(List<ConfirmacionFinalizacion> confirmaciones) {
        // TODO implementar verificación de confirmación del prestador.
        // Debe recorrer las confirmaciones y detectar si existe una confirmación
        // válida emitida por el prestador.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean estanAmbasConfirmacionesPresentes(List<ConfirmacionFinalizacion> confirmaciones) {
        // TODO implementar validación de confirmaciones completas.
        // Debe devolver true únicamente cuando existan confirmaciones válidas
        // tanto del solicitante como del prestador.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}