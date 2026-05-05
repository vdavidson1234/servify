package com.servify.publicaciones.domain.model;

import com.servify.publicaciones.domain.enumtype.EstadoPublicacion;
import com.servify.shared.domain.enumtype.ModalidadServicio;
import com.servify.shared.domain.valueobject.DisponibilidadHoraria;
import com.servify.shared.domain.model.BaseEntity;
import com.servify.shared.domain.valueobject.Ubicacion;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class PublicacionServicio extends BaseEntity {

    private UUID usuarioId;
    private CategoriaServicio categoriaServicio;
    private String titulo;
    private String descripcion;
    private ModalidadServicio modalidadServicio;
    private Ubicacion ubicacion;
    private List<DisponibilidadHoraria> disponibilidadesHorarias;
    private BigDecimal precioBase;
    private EstadoPublicacion estado;

    protected PublicacionServicio() {
    }

    public PublicacionServicio(UUID id,
                               UUID usuarioId,
                               CategoriaServicio categoriaServicio,
                               String titulo,
                               String descripcion,
                               ModalidadServicio modalidadServicio,
                               Ubicacion ubicacion,
                               List<DisponibilidadHoraria> disponibilidadesHorarias,
                               BigDecimal precioBase,
                               EstadoPublicacion estado) {
        super(id);
        this.usuarioId = usuarioId;
        this.categoriaServicio = categoriaServicio;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.modalidadServicio = modalidadServicio;
        this.ubicacion = ubicacion;
        this.disponibilidadesHorarias = disponibilidadesHorarias;
        this.precioBase = precioBase;
        this.estado = estado;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public CategoriaServicio getCategoriaServicio() {
        return categoriaServicio;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ModalidadServicio getModalidadServicio() {
        return modalidadServicio;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public List<DisponibilidadHoraria> getDisponibilidadesHorarias() {
        return disponibilidadesHorarias;
    }

    public BigDecimal getPrecioBase() {
        return precioBase;
    }

    public EstadoPublicacion getEstado() {
        return estado;
    }

    public boolean estaActiva() {
        // TODO implementar verificación de publicación activa.
        // Debe devolver true cuando la publicación se encuentre habilitada
        // para participar en el proceso de distribución de solicitudes.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean perteneceA(UUID usuarioId) {
        // TODO implementar validación de pertenencia.
        // Debe verificar si la publicación pertenece al usuario indicado.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean puedeParticiparEnDistribucion() {
        // TODO implementar validación de participación en distribución.
        // Debe verificar, como mínimo:
        // - que la publicación esté activa
        // - que su categoría esté activa
        // - que tenga disponibilidad válida
        // - que posea modalidad y ubicación coherentes para operar
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarTitulo(String titulo) {
        // TODO implementar actualización de título.
        // Debe validar que el título no sea nulo, vacío ni inválido
        // según las reglas definidas por la plataforma.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarDescripcion(String descripcion) {
        // TODO implementar actualización de descripción.
        // Debe validar longitud y contenido según las reglas del negocio.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarCategoria(CategoriaServicio categoriaServicio) {
        // TODO implementar actualización de categoría.
        // Debe validar que la categoría exista y se encuentre disponible para uso.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarModalidad(ModalidadServicio modalidadServicio) {
        // TODO implementar actualización de modalidad.
        // Debe validar que la modalidad informada sea compatible con la publicación.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarUbicacion(Ubicacion ubicacion) {
        // TODO implementar actualización de ubicación.
        // Debe validar que la ubicación sea suficiente para la operación
        // según la modalidad del servicio.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarDisponibilidades(List<DisponibilidadHoraria> disponibilidadesHorarias) {
        // TODO implementar actualización de disponibilidades horarias.
        // Debe validar que la lista no sea nula ni vacía, que las franjas sean válidas
        // y que no existan superposiciones incompatibles.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarPrecioBase(BigDecimal precioBase) {
        // TODO implementar actualización de precio base.
        // Debe validar que el precio sea válido, no negativo
        // y cumpla las restricciones definidas por la plataforma.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void activar() {
        // TODO implementar activación de publicación.
        // Debe cambiar el estado a ACTIVA respetando las transiciones válidas
        // y verificando que la publicación cumpla condiciones mínimas.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void desactivar() {
        // TODO implementar desactivación de publicación.
        // Debe cambiar el estado para impedir que participe en nuevas distribuciones.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void pausar() {
        // TODO implementar pausa de publicación.
        // Debe cambiar el estado a PAUSADA cuando corresponda
        // según la lógica operativa definida por la plataforma.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void eliminar() {
        // TODO implementar baja lógica de publicación.
        // Debe marcar la publicación como ELIMINADA sin perder trazabilidad histórica.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}