package com.servify.autenticacion.domain.model;

import com.servify.autenticacion.domain.enumtype.ProveedorIdentidadExterna;
import com.servify.shared.domain.model.BaseEntity;
import java.time.LocalDateTime;
import java.util.UUID;

public class IdentidadExterna extends BaseEntity {

    private UUID usuarioId;
    private ProveedorIdentidadExterna proveedor;
    private String subject;
    private String email;
    private Boolean emailVerificado;
    private String nombreMostrado;
    private LocalDateTime fechaVinculacion;
    private LocalDateTime ultimoAcceso;
    private Boolean habilitada;

    protected IdentidadExterna() {
    }

    public IdentidadExterna(UUID id,
                            UUID usuarioId,
                            ProveedorIdentidadExterna proveedor,
                            String subject,
                            String email,
                            Boolean emailVerificado,
                            String nombreMostrado,
                            LocalDateTime fechaVinculacion,
                            LocalDateTime ultimoAcceso,
                            Boolean habilitada) {
        super(id);
        this.usuarioId = validarUsuarioId(usuarioId);
        this.proveedor = validarProveedor(proveedor);
        this.subject = normalizarObligatorio(subject, "subject no puede ser nulo o vacio");
        this.email = normalizarObligatorio(email, "email no puede ser nulo o vacio");
        this.emailVerificado = emailVerificado;
        this.nombreMostrado = normalizarOpcional(nombreMostrado);
        this.fechaVinculacion = fechaVinculacion != null ? fechaVinculacion : LocalDateTime.now();
        this.ultimoAcceso = ultimoAcceso;
        this.habilitada = habilitada != null ? habilitada : true;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public ProveedorIdentidadExterna getProveedor() {
        return proveedor;
    }

    public String getSubject() {
        return subject;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getEmailVerificado() {
        return emailVerificado;
    }

    public String getNombreMostrado() {
        return nombreMostrado;
    }

    public LocalDateTime getFechaVinculacion() {
        return fechaVinculacion;
    }

    public LocalDateTime getUltimoAcceso() {
        return ultimoAcceso;
    }

    public Boolean getHabilitada() {
        return habilitada;
    }

    public boolean estaHabilitada() {
        return Boolean.TRUE.equals(habilitada);
    }

    public boolean correspondeA(ProveedorIdentidadExterna proveedor, String subject) {
        return this.proveedor == proveedor && this.subject.equals(subject);
    }

    public boolean perteneceAUsuario(UUID usuarioId) {
        return usuarioId != null && usuarioId.equals(this.usuarioId);
    }

    public void registrarAcceso(LocalDateTime fechaAcceso,
                                String email,
                                Boolean emailVerificado,
                                String nombreMostrado) {
        if (fechaAcceso == null) {
            throw new IllegalArgumentException("La fecha de acceso no puede ser nula");
        }
        this.email = normalizarObligatorio(email, "email no puede ser nulo o vacio");
        this.emailVerificado = emailVerificado;
        this.nombreMostrado = normalizarOpcional(nombreMostrado);
        this.ultimoAcceso = fechaAcceso;
    }

    public void deshabilitar() {
        this.habilitada = false;
    }

    public void habilitar() {
        this.habilitada = true;
    }

    private UUID validarUsuarioId(UUID usuarioId) {
        if (usuarioId == null) {
            throw new IllegalArgumentException("usuarioId no puede ser nulo");
        }
        return usuarioId;
    }

    private ProveedorIdentidadExterna validarProveedor(ProveedorIdentidadExterna proveedor) {
        if (proveedor == null) {
            throw new IllegalArgumentException("proveedor no puede ser nulo");
        }
        return proveedor;
    }

    private String normalizarObligatorio(String valor, String mensaje) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(mensaje);
        }
        return valor.trim();
    }

    private String normalizarOpcional(String valor) {
        if (valor == null || valor.isBlank()) {
            return null;
        }
        return valor.trim();
    }
}
