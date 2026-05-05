package com.servify.solicitudes.domain.model;

import com.servify.shared.domain.model.BaseEntity;
import com.servify.solicitudes.domain.enumtype.EstadoDistribucion;

import java.time.LocalDateTime;
import java.util.UUID;

//Metí:
//solicitudId
//publicacionServicioId
//prestadorId
//porque cada distribución está asociada a:
//una solicitud
//una publicación compatible
//el prestador dueño de esa publicación
//También agregué:
//rondaDistribucion para modelar la distribución progresiva
//fechaEnvio
//fechaRespuesta
//fechaExpiracion
//
//Eso les va a servir bastante después para trazabilidad, matching y reglas de expiración.

public class DistribucionSolicitud extends BaseEntity {

    private UUID solicitudId;
    private UUID publicacionServicioId;
    private UUID prestadorId;
    private EstadoDistribucion estado;
    private Integer rondaDistribucion;
    private LocalDateTime fechaEnvio;
    private LocalDateTime fechaRespuesta;
    private LocalDateTime fechaExpiracion;

    protected DistribucionSolicitud() {
    }

    public DistribucionSolicitud(UUID id,
                                 UUID solicitudId,
                                 UUID publicacionServicioId,
                                 UUID prestadorId,
                                 EstadoDistribucion estado,
                                 Integer rondaDistribucion,
                                 LocalDateTime fechaEnvio,
                                 LocalDateTime fechaRespuesta,
                                 LocalDateTime fechaExpiracion) {
        super(id);
        this.solicitudId = solicitudId;
        this.publicacionServicioId = publicacionServicioId;
        this.prestadorId = prestadorId;
        this.estado = estado;
        this.rondaDistribucion = rondaDistribucion;
        this.fechaEnvio = fechaEnvio;
        this.fechaRespuesta = fechaRespuesta;
        this.fechaExpiracion = fechaExpiracion;
    }

    public UUID getSolicitudId() {
        return solicitudId;
    }

    public UUID getPublicacionServicioId() {
        return publicacionServicioId;
    }

    public UUID getPrestadorId() {
        return prestadorId;
    }

    public EstadoDistribucion getEstado() {
        return estado;
    }

    public Integer getRondaDistribucion() {
        return rondaDistribucion;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public LocalDateTime getFechaRespuesta() {
        return fechaRespuesta;
    }

    public LocalDateTime getFechaExpiracion() {
        return fechaExpiracion;
    }

    public boolean estaEnviada() {
        // TODO implementar verificación de estado ENVIADA.
        // Debe devolver true cuando la distribución fue enviada
        // y todavía no recibió una respuesta válida del prestador.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean estaAceptada() {
        // TODO implementar verificación de estado ACEPTADA.
        // Debe devolver true cuando el prestador aceptó esta distribución.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean estaRechazada() {
        // TODO implementar verificación de estado RECHAZADA.
        // Debe devolver true cuando el prestador rechazó esta distribución.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean estaContraofertada() {
        // TODO implementar verificación de estado CONTRAOFERTADA.
        // Debe devolver true cuando el prestador respondió mediante una contraoferta.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean estaExpirada() {
        // TODO implementar verificación de estado EXPIRADA.
        // Debe devolver true cuando la distribución superó su tiempo de respuesta
        // sin recibir contestación válida.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean estaCerrada() {
        // TODO implementar verificación de estado CERRADA.
        // Debe devolver true cuando la distribución fue cerrada por el sistema,
        // por ejemplo al asignarse la solicitud a otro prestador.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean fueRespondida() {
        // TODO implementar validación de respuesta emitida.
        // Debe devolver true cuando la distribución ya tenga una respuesta final
        // o una respuesta que impida seguir operando sobre ella.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean puedeSerRespondida() {
        // TODO implementar validación para responder la distribución.
        // Debe verificar que la distribución siga activa para el prestador
        // y que no se encuentre expirada, cerrada o respondida definitivamente.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean perteneceAPrestador(UUID prestadorId) {
        // TODO implementar validación de pertenencia.
        // Debe verificar si esta distribución corresponde al prestador indicado.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void marcarComoEnviada() {
        // TODO implementar transición a estado ENVIADA.
        // Debe utilizarse al crear o despachar la distribución al prestador,
        // registrando correctamente la fecha de envío si corresponde.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void aceptar(LocalDateTime fechaRespuesta) {
        // TODO implementar aceptación de la distribución.
        // Debe cambiar el estado a ACEPTADA, registrar la fecha de respuesta
        // y validar que la distribución todavía pueda ser respondida.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void rechazar(LocalDateTime fechaRespuesta) {
        // TODO implementar rechazo de la distribución.
        // Debe cambiar el estado a RECHAZADA, registrar la fecha de respuesta
        // y validar que la distribución todavía pueda ser respondida.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void marcarComoContraofertada(LocalDateTime fechaRespuesta) {
        // TODO implementar transición a estado CONTRAOFERTADA.
        // Debe cambiar el estado, registrar la fecha de respuesta
        // y validar que la distribución admita contraoferta.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void expirar(LocalDateTime fechaExpiracion) {
        // TODO implementar expiración de la distribución.
        // Debe cambiar el estado a EXPIRADA cuando se supere el tiempo permitido
        // sin respuesta válida del prestador.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void cerrar() {
        // TODO implementar cierre de la distribución.
        // Debe cambiar el estado a CERRADA cuando la solicitud ya no deba
        // seguir disponible para este prestador.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}