package com.servify.publicaciones.domain.service;

import com.servify.shared.domain.enumtype.ModalidadServicio;
import com.servify.publicaciones.domain.model.CategoriaServicio;
import com.servify.publicaciones.domain.model.PublicacionServicio;
import com.servify.shared.domain.valueobject.DisponibilidadHoraria;
import com.servify.shared.domain.valueobject.Ubicacion;

public class PoliticaCompatibilidadPublicacion {

    public boolean esCompatible(PublicacionServicio publicacion,
                                CategoriaServicio categoriaRequerida,
                                ModalidadServicio modalidadRequerida,
                                Ubicacion ubicacionRequerida,
                                DisponibilidadHoraria disponibilidadRequerida) {
        // TODO implementar validación integral de compatibilidad.
        // Debe verificar, como mínimo:
        // - que la publicación exista
        // - que pueda participar en distribución
        // - que la categoría de la publicación sea compatible con la requerida
        // - que la modalidad del servicio sea compatible con la requerida
        // - que la ubicación sea compatible según las reglas de cercanía
        // - que exista al menos una disponibilidad horaria compatible
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean esCompatiblePorCategoria(PublicacionServicio publicacion,
                                            CategoriaServicio categoriaRequerida) {
        // TODO implementar compatibilidad por categoría.
        // Debe verificar si la categoría de la publicación coincide con la categoría requerida
        // o si existe algún criterio de equivalencia permitido por el negocio.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean esCompatiblePorModalidad(PublicacionServicio publicacion,
                                            ModalidadServicio modalidadRequerida) {
        // TODO implementar compatibilidad por modalidad.
        // Debe verificar si la modalidad de la publicación satisface la modalidad solicitada.
        // Por ejemplo, contemplar si una modalidad MIXTA puede cubrir casos presenciales o virtuales.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean esCompatiblePorUbicacion(PublicacionServicio publicacion,
                                            Ubicacion ubicacionRequerida) {
        // TODO implementar compatibilidad geográfica.
        // Debe verificar si la ubicación de la publicación resulta apta para atender
        // la ubicación requerida según la lógica de cercanía definida por la plataforma.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean esCompatiblePorDisponibilidad(PublicacionServicio publicacion,
                                                 DisponibilidadHoraria disponibilidadRequerida) {
        // TODO implementar compatibilidad por disponibilidad.
        // Debe verificar si alguna de las disponibilidades horarias de la publicación
        // coincide o resulta compatible con la franja requerida.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}