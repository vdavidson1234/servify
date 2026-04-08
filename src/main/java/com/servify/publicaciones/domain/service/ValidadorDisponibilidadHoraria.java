package com.servify.publicaciones.domain.service;

import com.servify.publicaciones.domain.valueobject.DisponibilidadHoraria;

import java.util.List;

public class ValidadorDisponibilidadHoraria {

    public boolean sonValidas(List<DisponibilidadHoraria> disponibilidades) {
        // TODO implementar validación integral de disponibilidades horarias.
        // Debe verificar, como mínimo:
        // - que la lista no sea nula
        // - que la lista no esté vacía
        // - que cada disponibilidad individual sea válida
        // - que no existan solapamientos incompatibles entre franjas del mismo día
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean haySuperposiciones(List<DisponibilidadHoraria> disponibilidades) {
        // TODO implementar detección de superposición entre disponibilidades.
        // Debe recorrer las franjas horarias y detectar si alguna se superpone
        // con otra en forma total o parcial.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}