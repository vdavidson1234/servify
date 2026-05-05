package com.servify.administracion.domain.model;

import com.servify.administracion.domain.enumtype.TipoMedida;
import com.servify.shared.domain.model.BaseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class MedidaAdministrativaUsuario extends BaseEntity {

    private UUID usuarioId;
    private UUID administradorId;
    private TipoMedida tipoMedida;
    private String motivo;
    private LocalDateTime fechaAplicacion;
    private LocalDateTime fechaFinVigencia;
    private Boolean activa;

    protected MedidaAdministrativaUsuario() {
    }

    public MedidaAdministrativaUsuario(UUID id,
                                       UUID usuarioId,
                                       UUID administradorId,
                                       TipoMedida tipoMedida,
                                       String motivo,
                                       LocalDateTime fechaAplicacion,
                                       LocalDateTime fechaFinVigencia,
                                       Boolean activa) {
        super(id);
        this.usuarioId = usuarioId;
        this.administradorId = administradorId;
        this.tipoMedida = tipoMedida;
        this.motivo = motivo;
        this.fechaAplicacion = fechaAplicacion;
        this.fechaFinVigencia = fechaFinVigencia;
        this.activa = activa;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public UUID getAdministradorId() {
        return administradorId;
    }

    public TipoMedida getTipoMedida() {
        return tipoMedida;
    }

    public String getMotivo() {
        return motivo;
    }

    public LocalDateTime getFechaAplicacion() {
        return fechaAplicacion;
    }

    public LocalDateTime getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public Boolean getActiva() {
        return activa;
    }

    public boolean esBloqueo() {
        // TODO implementar verificación de tipo BLOQUEO.
        // Debe devolver true cuando la medida administrativa corresponda a un bloqueo.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean esSuspension() {
        // TODO implementar verificación de tipo SUSPENSION.
        // Debe devolver true cuando la medida administrativa corresponda a una suspensión.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean esAdvertencia() {
        // TODO implementar verificación de tipo ADVERTENCIA.
        // Debe devolver true cuando la medida administrativa corresponda a una advertencia.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean estaActiva() {
        // TODO implementar verificación de medida activa.
        // Debe devolver true cuando la medida siga vigente
        // y no haya sido levantada o finalizada.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean tieneVigenciaTemporal() {
        // TODO implementar validación de vigencia temporal.
        // Debe devolver true cuando la medida tenga una fecha de fin definida.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean estaVencida(LocalDateTime fechaActual) {
        // TODO implementar verificación de vencimiento.
        // Debe validar si la medida superó su fecha de fin de vigencia
        // respecto de la fecha actual recibida.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean aplicaAUsuario(UUID usuarioId) {
        // TODO implementar validación de pertenencia al usuario afectado.
        // Debe verificar si la medida corresponde al usuario indicado.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean fueAplicadaPor(UUID administradorId) {
        // TODO implementar validación del administrador emisor.
        // Debe verificar si la medida fue aplicada por el administrador indicado.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarMotivo(String motivo) {
        // TODO implementar actualización del motivo.
        // Debe validar contenido, longitud y si el cambio está permitido
        // según el estado actual de la medida.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarFechaFinVigencia(LocalDateTime fechaFinVigencia) {
        // TODO implementar actualización de fin de vigencia.
        // Debe validar coherencia temporal respecto de la fecha de aplicación
        // y las reglas definidas para el tipo de medida.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void activar() {
        // TODO implementar activación de la medida.
        // Debe marcar la medida como activa respetando las transiciones válidas.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void desactivar() {
        // TODO implementar desactivación de la medida.
        // Debe marcar la medida como no activa preservando la trazabilidad.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}