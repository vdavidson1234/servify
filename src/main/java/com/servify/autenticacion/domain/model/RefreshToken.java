package com.servify.autenticacion.domain.model;

import com.servify.shared.domain.model.BaseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class RefreshToken extends BaseEntity {

    private UUID usuarioId;
    private UUID credencialAccesoId;
    private String tokenHash;
    private LocalDateTime fechaEmision;
    private LocalDateTime fechaExpiracion;
    private LocalDateTime fechaRevocacion;
    private Boolean activo;

    protected RefreshToken() {
    }

    public RefreshToken(UUID id,
                        UUID usuarioId,
                        UUID credencialAccesoId,
                        String tokenHash,
                        LocalDateTime fechaEmision,
                        LocalDateTime fechaExpiracion,
                        LocalDateTime fechaRevocacion,
                        Boolean activo) {
        super(id);
        this.usuarioId = usuarioId;
        this.credencialAccesoId = credencialAccesoId;
        this.tokenHash = tokenHash;
        this.fechaEmision = fechaEmision;
        this.fechaExpiracion = fechaExpiracion;
        this.fechaRevocacion = fechaRevocacion;
        this.activo = activo;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public UUID getCredencialAccesoId() {
        return credencialAccesoId;
    }

    public String getTokenHash() {
        return tokenHash;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public LocalDateTime getFechaExpiracion() {
        return fechaExpiracion;
    }

    public LocalDateTime getFechaRevocacion() {
        return fechaRevocacion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public boolean estaActivo() {
        // TODO implementar verificacion de token activo.
        // Debe devolver true si el refresh token puede utilizarse para renovar sesion.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    public boolean estaExpirado(LocalDateTime fechaActual) {
        // TODO implementar verificacion de expiracion.
        // Debe comparar la fecha actual contra la fecha de expiracion configurada.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    public boolean fueRevocado() {
        // TODO implementar verificacion de revocacion.
        // Debe devolver true cuando exista fecha de revocacion o estado inactivo.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    public boolean perteneceAUsuario(UUID usuarioId) {
        // TODO implementar validacion de pertenencia del token al usuario.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    public void revocar(LocalDateTime fechaRevocacion) {
        // TODO implementar revocacion del refresh token.
        // Debe marcarlo como inactivo y registrar la fecha de revocacion.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }
}
