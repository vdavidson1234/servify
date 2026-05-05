package com.servify.solicitudes.domain.model;

import com.servify.shared.domain.model.BaseEntity;
import com.servify.solicitudes.domain.enumtype.EstadoContraoferta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

//Metí estos atributos porque suelen ser los justos y necesarios:
//distribucionSolicitudId: ancla la contraoferta a una distribución concreta
//prestadorId: deja claro quién la emitió
//precioOriginal y precioPropuesto: permiten trazabilidad
//mensaje: para aclaración breve del prestador
//estado, fechaEmision, fechaResolucion: para ciclo de vida

public class Contraoferta extends BaseEntity {

    private UUID distribucionSolicitudId;
    private UUID prestadorId;
    private BigDecimal precioOriginal;
    private BigDecimal precioPropuesto;
    private String mensaje;
    private EstadoContraoferta estado;
    private LocalDateTime fechaEmision;
    private LocalDateTime fechaResolucion;

    protected Contraoferta() {
    }

    public Contraoferta(UUID id,
                        UUID distribucionSolicitudId,
                        UUID prestadorId,
                        BigDecimal precioOriginal,
                        BigDecimal precioPropuesto,
                        String mensaje,
                        EstadoContraoferta estado,
                        LocalDateTime fechaEmision,
                        LocalDateTime fechaResolucion) {
        super(id);
        this.distribucionSolicitudId = distribucionSolicitudId;
        this.prestadorId = prestadorId;
        this.precioOriginal = precioOriginal;
        this.precioPropuesto = precioPropuesto;
        this.mensaje = mensaje;
        this.estado = estado;
        this.fechaEmision = fechaEmision;
        this.fechaResolucion = fechaResolucion;
    }

    public UUID getDistribucionSolicitudId() {
        return distribucionSolicitudId;
    }

    public UUID getPrestadorId() {
        return prestadorId;
    }

    public BigDecimal getPrecioOriginal() {
        return precioOriginal;
    }

    public BigDecimal getPrecioPropuesto() {
        return precioPropuesto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public EstadoContraoferta getEstado() {
        return estado;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public LocalDateTime getFechaResolucion() {
        return fechaResolucion;
    }

    public boolean estaPendiente() {
        // TODO implementar verificación de estado PENDIENTE.
        // Debe devolver true cuando la contraoferta haya sido emitida
        // y todavía no haya sido aceptada, rechazada o cancelada.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean estaAceptada() {
        // TODO implementar verificación de estado ACEPTADA.
        // Debe devolver true cuando la contraoferta haya sido aceptada
        // por la parte correspondiente del flujo.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean estaRechazada() {
        // TODO implementar verificación de estado RECHAZADA.
        // Debe devolver true cuando la contraoferta haya sido rechazada.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean estaCancelada() {
        // TODO implementar verificación de estado CANCELADA.
        // Debe devolver true cuando la contraoferta haya perdido vigencia
        // o haya sido anulada por el sistema o por el flujo del negocio.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean puedeSerResuelta() {
        // TODO implementar validación para resolución de la contraoferta.
        // Debe verificar que la contraoferta continúe pendiente
        // y que la distribución/solicitud asociada siga activa.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean perteneceAPrestador(UUID prestadorId) {
        // TODO implementar validación de pertenencia.
        // Debe verificar si la contraoferta fue emitida por el prestador indicado.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarMensaje(String mensaje) {
        // TODO implementar actualización del mensaje de la contraoferta.
        // Debe validar longitud, contenido y si el cambio está permitido
        // según el estado actual de la contraoferta.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarPrecioPropuesto(BigDecimal precioPropuesto) {
        // TODO implementar actualización del precio propuesto.
        // Debe validar que el precio no sea nulo, no sea negativo
        // y cumpla las restricciones definidas por el negocio.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void aceptar(LocalDateTime fechaResolucion) {
        // TODO implementar aceptación de la contraoferta.
        // Debe cambiar el estado a ACEPTADA, registrar la fecha de resolución
        // y validar que la contraoferta pueda ser resuelta.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void rechazar(LocalDateTime fechaResolucion) {
        // TODO implementar rechazo de la contraoferta.
        // Debe cambiar el estado a RECHAZADA, registrar la fecha de resolución
        // y validar que la contraoferta pueda ser resuelta.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void cancelar(LocalDateTime fechaResolucion) {
        // TODO implementar cancelación de la contraoferta.
        // Debe cambiar el estado a CANCELADA, registrar la fecha de resolución
        // y aplicar las reglas de negocio correspondientes.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}