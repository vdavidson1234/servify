package com.servify.solicitudes.domain.model;

import com.servify.shared.domain.model.BaseEntity;
import com.servify.solicitudes.domain.enumtype.EstadoAsignacion;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

//Decisiones tomadas:
//solicitudId, distribucionSolicitudId, prestadorId y publicacionServicioId van por ID para no acoplar módulos.
//precioAcordado permite reflejar tanto el precio original como uno surgido de una contraoferta aceptada.
//fechaAsignacion y fechaFinalizacion ayudan con trazabilidad y auditoría básica.

public class AsignacionServicio extends BaseEntity {

    private UUID solicitudId;
    private UUID distribucionSolicitudId;
    private UUID prestadorId;
    private UUID publicacionServicioId;
    private BigDecimal precioAcordado;
    private EstadoAsignacion estado;
    private LocalDateTime fechaAsignacion;
    private LocalDateTime fechaFinalizacion;

    protected AsignacionServicio() {
    }

    public AsignacionServicio(UUID id,
                              UUID solicitudId,
                              UUID distribucionSolicitudId,
                              UUID prestadorId,
                              UUID publicacionServicioId,
                              BigDecimal precioAcordado,
                              EstadoAsignacion estado,
                              LocalDateTime fechaAsignacion,
                              LocalDateTime fechaFinalizacion) {
        super(id);
        this.solicitudId = solicitudId;
        this.distribucionSolicitudId = distribucionSolicitudId;
        this.prestadorId = prestadorId;
        this.publicacionServicioId = publicacionServicioId;
        this.precioAcordado = precioAcordado;
        this.estado = estado;
        this.fechaAsignacion = fechaAsignacion;
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public UUID getSolicitudId() {
        return solicitudId;
    }

    public UUID getDistribucionSolicitudId() {
        return distribucionSolicitudId;
    }

    public UUID getPrestadorId() {
        return prestadorId;
    }

    public UUID getPublicacionServicioId() {
        return publicacionServicioId;
    }

    public BigDecimal getPrecioAcordado() {
        return precioAcordado;
    }

    public EstadoAsignacion getEstado() {
        return estado;
    }

    public LocalDateTime getFechaAsignacion() {
        return fechaAsignacion;
    }

    public LocalDateTime getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public boolean estaPendienteConfirmacion() {
        // TODO implementar verificación de estado PENDIENTE_CONFIRMACION.
        // Debe devolver true cuando la asignación exista pero todavía no haya
        // quedado consolidada como activa según el flujo definido por el negocio.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean estaActiva() {
        // TODO implementar verificación de estado ACTIVA.
        // Debe devolver true cuando la asignación se encuentre vigente
        // y el servicio esté en curso o listo para ejecutarse.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean estaFinalizada() {
        // TODO implementar verificación de estado FINALIZADA.
        // Debe devolver true cuando la asignación haya concluido correctamente
        // y la solicitud asociada haya completado su cierre.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean estaCancelada() {
        // TODO implementar verificación de estado CANCELADA.
        // Debe devolver true cuando la asignación haya sido anulada
        // por una regla válida del flujo de negocio.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean perteneceAPrestador(UUID prestadorId) {
        // TODO implementar validación de pertenencia al prestador.
        // Debe verificar si esta asignación corresponde al prestador indicado.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean correspondeASolicitud(UUID solicitudId) {
        // TODO implementar validación de pertenencia a la solicitud.
        // Debe verificar si esta asignación corresponde a la solicitud indicada.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean puedeFinalizarse() {
        // TODO implementar validación previa a finalización.
        // Debe verificar que la asignación se encuentre en un estado
        // que permita su cierre efectivo según las reglas del negocio.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarPrecioAcordado(BigDecimal precioAcordado) {
        // TODO implementar actualización del precio acordado.
        // Debe validar que el precio no sea nulo, no sea negativo
        // y que el cambio esté permitido por el estado actual de la asignación.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void marcarComoPendienteConfirmacion() {
        // TODO implementar transición a estado PENDIENTE_CONFIRMACION.
        // Debe utilizarse al generarse la asignación inicial,
        // según el flujo operativo definido por la plataforma.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void activar() {
        // TODO implementar transición a estado ACTIVA.
        // Debe consolidar la asignación como vigente
        // una vez cumplidas las condiciones necesarias del flujo.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void finalizar(LocalDateTime fechaFinalizacion) {
        // TODO implementar finalización de la asignación.
        // Debe cambiar el estado a FINALIZADA, registrar la fecha de finalización
        // y validar que la asignación pueda cerrarse.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void cancelar() {
        // TODO implementar cancelación de la asignación.
        // Debe cambiar el estado a CANCELADA respetando las transiciones válidas
        // y preservando la trazabilidad del proceso.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}