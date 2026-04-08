package com.servify.publicaciones.domain.model;

import com.servify.publicaciones.domain.enumtype.EstadoCategoria;
import com.servify.shared.domain.model.BaseEntity;

import java.util.UUID;

public class CategoriaServicio extends BaseEntity {

    private String nombre;
    private String descripcion;
    private EstadoCategoria estado;

    protected CategoriaServicio() {
    }

    public CategoriaServicio(UUID id,
                             String nombre,
                             String descripcion,
                             EstadoCategoria estado) {
        super(id);
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public EstadoCategoria getEstado() {
        return estado;
    }

    public boolean estaActiva() {
        // TODO implementar verificación de categoría activa.
        // Debe devolver true cuando la categoría esté habilitada
        // para ser utilizada en nuevas publicaciones.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarNombre(String nombre) {
        // TODO implementar actualización del nombre de la categoría.
        // Debe validar que el nombre no sea nulo, vacío ni inválido,
        // y aplicar la política de unicidad si corresponde en otra capa.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarDescripcion(String descripcion) {
        // TODO implementar actualización de la descripción de la categoría.
        // Debe validar longitud y contenido según las reglas del negocio.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void activar() {
        // TODO implementar activación de la categoría.
        // Debe cambiar el estado a ACTIVA respetando las transiciones válidas.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void desactivar() {
        // TODO implementar desactivación de la categoría.
        // Debe cambiar el estado a INACTIVA para impedir su uso en nuevas publicaciones.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}