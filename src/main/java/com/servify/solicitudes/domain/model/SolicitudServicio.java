package com.servify.solicitudes.domain.model;

import com.servify.shared.domain.enumtype.ModalidadServicio;
import com.servify.shared.domain.valueobject.DisponibilidadHoraria;
import com.servify.shared.domain.model.BaseEntity;
import com.servify.shared.domain.valueobject.Ubicacion;
import com.servify.solicitudes.domain.enumtype.EstadoSolicitud;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

//Tomé estas decisiones para mantener coherencia:
//solicitanteId en vez de referencia a Usuario
//categoriaServicioId en vez de referencia a CategoriaServicio
//ModalidadServicio reutilizada como enum común del negocio
//DisponibilidadHoraria reutilizada como franja requerida
//precioReferencia como BigDecimal

public class SolicitudServicio extends BaseEntity {

    private UUID solicitanteId;
    private UUID categoriaServicioId;
    private ModalidadServicio modalidadServicio;
    private Ubicacion ubicacion;
    private DisponibilidadHoraria disponibilidadRequerida;
    private String descripcionNecesidad;
    private BigDecimal precioReferencia;
    private EstadoSolicitud estado;
    private LocalDateTime fechaSolicitud;

    protected SolicitudServicio() {
    }

    public SolicitudServicio(UUID id,
                             UUID solicitanteId,
                             UUID categoriaServicioId,
                             ModalidadServicio modalidadServicio,
                             Ubicacion ubicacion,
                             DisponibilidadHoraria disponibilidadRequerida,
                             String descripcionNecesidad,
                             BigDecimal precioReferencia,
                             EstadoSolicitud estado,
                             LocalDateTime fechaSolicitud) {
        super(id);
        this.solicitanteId = solicitanteId;
        this.categoriaServicioId = categoriaServicioId;
        this.modalidadServicio = modalidadServicio;
        this.ubicacion = ubicacion;
        this.disponibilidadRequerida = disponibilidadRequerida;
        this.descripcionNecesidad = descripcionNecesidad;
        this.precioReferencia = precioReferencia;
        this.estado = estado;
        this.fechaSolicitud = fechaSolicitud;
    }

    public UUID getSolicitanteId() {
        return solicitanteId;
    }

    public UUID getCategoriaServicioId() {
        return categoriaServicioId;
    }

    public ModalidadServicio getModalidadServicio() {
        return modalidadServicio;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public DisponibilidadHoraria getDisponibilidadRequerida() {
        return disponibilidadRequerida;
    }

    public String getDescripcionNecesidad() {
        return descripcionNecesidad;
    }

    public BigDecimal getPrecioReferencia() {
        return precioReferencia;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public boolean estaBuscandoPrestador() {
        // TODO implementar verificación de estado BUSCANDO_PRESTADOR.
        // Debe devolver true cuando la solicitud se encuentre en proceso
        // de búsqueda y distribución activa de prestadores compatibles.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean estaAsignada() {
        // TODO implementar verificación de estado ASIGNADA.
        // Debe devolver true cuando la solicitud ya tenga un prestador asignado.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean estaFinalizada() {
        // TODO implementar verificación de estado FINALIZADA.
        // Debe devolver true cuando ambas partes hayan confirmado
        // la realización del servicio y el cierre sea efectivo.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean estaCancelada() {
        // TODO implementar verificación de estado CANCELADA.
        // Debe devolver true cuando la solicitud haya sido anulada
        // por el solicitante o por la lógica operativa correspondiente.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean puedeSerCancelada() {
        // TODO implementar validación de cancelación.
        // Debe verificar si la solicitud cumple las reglas de negocio
        // para ser cancelada, por ejemplo que no esté finalizada.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean puedeRecibirRespuestas() {
        // TODO implementar validación para recepción de respuestas.
        // Debe verificar si la solicitud sigue activa y disponible
        // para que prestadores respondan distribuciones asociadas.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarModalidad(ModalidadServicio modalidadServicio) {
        // TODO implementar actualización de modalidad.
        // Debe validar que la modalidad no sea nula y que el cambio
        // sea válido según el estado actual de la solicitud.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarUbicacion(Ubicacion ubicacion) {
        // TODO implementar actualización de ubicación.
        // Debe validar que la ubicación sea suficiente y coherente
        // con la modalidad del servicio solicitada.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarDisponibilidadRequerida(DisponibilidadHoraria disponibilidadRequerida) {
        // TODO implementar actualización de disponibilidad requerida.
        // Debe validar que la franja horaria sea válida
        // y compatible con el flujo de negocio de la solicitud.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarDescripcionNecesidad(String descripcionNecesidad) {
        // TODO implementar actualización de descripción de necesidad.
        // Debe validar contenido, longitud y obligatoriedad
        // según las reglas definidas por la plataforma.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarPrecioReferencia(BigDecimal precioReferencia) {
        // TODO implementar actualización de precio de referencia.
        // Debe validar que el precio no sea nulo, no sea negativo
        // y cumpla las restricciones definidas por la plataforma.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void marcarComoBuscandoPrestador() {
        // TODO implementar transición a estado BUSCANDO_PRESTADOR.
        // Debe aplicarse al crear la solicitud o al reanudar la búsqueda
        // si el flujo de negocio lo permite.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void marcarComoAsignada() {
        // TODO implementar transición a estado ASIGNADA.
        // Debe ejecutarse únicamente cuando exista una asignación válida
        // y única para esta solicitud.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void marcarComoFinalizada() {
        // TODO implementar transición a estado FINALIZADA.
        // Debe ejecutarse únicamente cuando ambas partes hayan confirmado
        // la finalización del servicio.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void cancelar() {
        // TODO implementar cancelación de la solicitud.
        // Debe cambiar el estado a CANCELADA respetando las reglas del negocio
        // y evitando cancelar solicitudes en estados no permitidos.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}