package com.servify.solicitudes.domain.service;

import com.servify.solicitudes.domain.model.Calificacion;
import com.servify.solicitudes.domain.model.SolicitudServicio;

import java.util.List;
import java.util.UUID;

//sta política evita repartir validaciones entre:
//Calificacion
//SolicitudServicio
//casos de uso
//controllers


public class PoliticaCalificacion {

    public boolean puedeCalificarse(SolicitudServicio solicitud,
                                    List<Calificacion> calificacionesExistentes,
                                    UUID prestadorId) {
        // TODO implementar validación integral de calificación.
        // Debe verificar, como mínimo:
        // - que la solicitud exista
        // - que la solicitud esté finalizada
        // - que no exista una calificación previa para esa misma solicitud
        // - que el prestador a calificar corresponda efectivamente a la asignación de la solicitud
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean yaFueCalificada(SolicitudServicio solicitud,
                                   List<Calificacion> calificacionesExistentes) {
        // TODO implementar verificación de calificación previa.
        // Debe recorrer las calificaciones existentes y detectar si alguna
        // corresponde a la solicitud indicada.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean correspondeAlPrestadorAsignado(UUID prestadorId,
                                                  UUID prestadorAsignadoId) {
        // TODO implementar validación de prestador calificado.
        // Debe verificar que el prestador que se intenta calificar
        // sea el mismo que quedó asignado a la solicitud finalizada.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean puntajePermitido(Integer puntaje) {
        // TODO implementar validación de puntaje.
        // Debe verificar que el puntaje se encuentre dentro del rango permitido por la plataforma.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}