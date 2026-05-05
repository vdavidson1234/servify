package com.servify.solicitudes.domain.model;

import com.servify.shared.domain.model.BaseEntity;
import com.servify.solicitudes.domain.enumtype.RolConfirmante;

import java.time.LocalDateTime;
import java.util.UUID;

//La pensé como una entidad por confirmación.
//Eso deja el flujo más claro:
//la asignación existe
//el solicitante confirma
//el prestador confirma
//cuando ambas confirmaciones están hechas, la solicitud/asignación puede pasar a finalizada

public class ConfirmacionFinalizacion extends BaseEntity {

    private UUID solicitudId;
    private UUID asignacionServicioId;
    private UUID confirmanteId;
    private RolConfirmante rolConfirmante;
    private Boolean confirmada;
    private LocalDateTime fechaConfirmacion;
    private String observacion;

    protected ConfirmacionFinalizacion() {
    }

    public ConfirmacionFinalizacion(UUID id,
                                    UUID solicitudId,
                                    UUID asignacionServicioId,
                                    UUID confirmanteId,
                                    RolConfirmante rolConfirmante,
                                    Boolean confirmada,
                                    LocalDateTime fechaConfirmacion,
                                    String observacion) {
        super(id);
        this.solicitudId = solicitudId;
        this.asignacionServicioId = asignacionServicioId;
        this.confirmanteId = confirmanteId;
        this.rolConfirmante = rolConfirmante;
        this.confirmada = confirmada;
        this.fechaConfirmacion = fechaConfirmacion;
        this.observacion = observacion;
    }

    public UUID getSolicitudId() {
        return solicitudId;
    }

    public UUID getAsignacionServicioId() {
        return asignacionServicioId;
    }

    public UUID getConfirmanteId() {
        return confirmanteId;
    }

    public RolConfirmante getRolConfirmante() {
        return rolConfirmante;
    }

    public Boolean getConfirmada() {
        return confirmada;
    }

    public LocalDateTime getFechaConfirmacion() {
        return fechaConfirmacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public boolean estaConfirmada() {
        // TODO implementar verificación de confirmación efectiva.
        // Debe devolver true cuando la confirmación haya sido realizada válidamente
        // por la parte correspondiente.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean esDelSolicitante() {
        // TODO implementar verificación de rol SOLICITANTE.
        // Debe devolver true cuando la confirmación haya sido emitida
        // por el solicitante de la solicitud.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean esDelPrestador() {
        // TODO implementar verificación de rol PRESTADOR.
        // Debe devolver true cuando la confirmación haya sido emitida
        // por el prestador asignado al servicio.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean perteneceAUsuario(UUID usuarioId) {
        // TODO implementar validación de pertenencia al usuario.
        // Debe verificar si esta confirmación fue emitida por el usuario indicado.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean correspondeASolicitud(UUID solicitudId) {
        // TODO implementar validación de pertenencia a la solicitud.
        // Debe verificar si esta confirmación corresponde a la solicitud indicada.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean correspondeAAsignacion(UUID asignacionServicioId) {
        // TODO implementar validación de pertenencia a la asignación.
        // Debe verificar si esta confirmación corresponde a la asignación indicada.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void confirmar(LocalDateTime fechaConfirmacion) {
        // TODO implementar confirmación de finalización.
        // Debe marcar la confirmación como efectiva, registrar la fecha
        // y validar que el usuario y la asignación estén habilitados para confirmar.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarObservacion(String observacion) {
        // TODO implementar actualización de observación.
        // Debe validar longitud, contenido y si el cambio está permitido
        // según el estado actual de la confirmación.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}