package com.servify.solicitudes.domain.model;

import com.servify.shared.domain.model.BaseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

//La dejé minimalista porque en el MVP no hay reseña textual, solo estrellas.
//Más adelante, si agregan comentarios o feedback escrito, se puede extender con algo como:
//String comentario
//Boolean visible
//LocalDateTime fechaEdicion

public class Calificacion extends BaseEntity {

    private UUID solicitudId;
    private UUID asignacionServicioId;
    private UUID solicitanteId;
    private UUID prestadorId;
    private Integer puntaje;
    private LocalDateTime fechaCalificacion;

    protected Calificacion() {
    }

    public Calificacion(UUID id,
                        UUID solicitudId,
                        UUID asignacionServicioId,
                        UUID solicitanteId,
                        UUID prestadorId,
                        Integer puntaje,
                        LocalDateTime fechaCalificacion) {
        super(id);
        this.solicitudId = solicitudId;
        this.asignacionServicioId = asignacionServicioId;
        this.solicitanteId = solicitanteId;
        this.prestadorId = prestadorId;
        this.puntaje = puntaje;
        this.fechaCalificacion = fechaCalificacion;
    }

    public UUID getSolicitudId() {
        return solicitudId;
    }

    public UUID getAsignacionServicioId() {
        return asignacionServicioId;
    }

    public UUID getSolicitanteId() {
        return solicitanteId;
    }

    public UUID getPrestadorId() {
        return prestadorId;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public LocalDateTime getFechaCalificacion() {
        return fechaCalificacion;
    }

    public boolean esPuntajeValido() {
        // TODO implementar validación de puntaje.
        // Debe verificar que el puntaje no sea nulo
        // y que esté dentro del rango permitido por la plataforma (1 a 5).
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean perteneceASolicitud(UUID solicitudId) {
        // TODO implementar validación de pertenencia a la solicitud.
        // Debe verificar si esta calificación corresponde a la solicitud indicada.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean perteneceAPrestador(UUID prestadorId) {
        // TODO implementar validación de pertenencia al prestador.
        // Debe verificar si esta calificación corresponde al prestador indicado.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean fueEmitidaPor(UUID solicitanteId) {
        // TODO implementar validación del emisor.
        // Debe verificar si esta calificación fue emitida por el solicitante indicado.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarPuntaje(Integer puntaje) {
        // TODO implementar actualización de puntaje.
        // Debe validar que el nuevo puntaje esté dentro del rango permitido
        // y que el cambio esté habilitado por las reglas del negocio.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}