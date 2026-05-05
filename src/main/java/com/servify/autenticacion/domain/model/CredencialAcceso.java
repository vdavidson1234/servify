package com.servify.autenticacion.domain.model;

import com.servify.shared.domain.model.BaseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class CredencialAcceso extends BaseEntity {

    private UUID usuarioId;
    private String emailAcceso;
    private String passwordHash;
    private Boolean habilitada;
    private LocalDateTime ultimoAcceso;
    private Integer intentosFallidos;

    protected CredencialAcceso() {
    }

    public CredencialAcceso(UUID id,
                            UUID usuarioId,
                            String emailAcceso,
                            String passwordHash,
                            Boolean habilitada,
                            LocalDateTime ultimoAcceso,
                            Integer intentosFallidos) {
        super(id);
        this.usuarioId = usuarioId;
        this.emailAcceso = emailAcceso;
        this.passwordHash = passwordHash;
        this.habilitada = habilitada;
        this.ultimoAcceso = ultimoAcceso;
        this.intentosFallidos = intentosFallidos;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public String getEmailAcceso() {
        return emailAcceso;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Boolean getHabilitada() {
        return habilitada;
    }

    public LocalDateTime getUltimoAcceso() {
        return ultimoAcceso;
    }

    public Integer getIntentosFallidos() {
        return intentosFallidos;
    }

    public boolean estaHabilitada() {
        // TODO implementar verificación de credencial habilitada.
        // Debe devolver true cuando la credencial pueda utilizarse
        // para autenticarse en la plataforma.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean perteneceAUsuario(UUID usuarioId) {
        // TODO implementar validación de pertenencia al usuario.
        // Debe verificar si esta credencial corresponde al usuario indicado.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean usaEmail(String emailAcceso) {
        // TODO implementar validación de email de acceso.
        // Debe verificar si el identificador de acceso coincide
        // con el email recibido.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarEmailAcceso(String emailAcceso) {
        // TODO implementar actualización de email de acceso.
        // Debe validar formato, unicidad en la capa correspondiente
        // y consistencia con las políticas de autenticación del sistema.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarPasswordHash(String passwordHash) {
        // TODO implementar actualización del hash de contraseña.
        // Debe reemplazar el hash almacenado por uno nuevo ya procesado
        // por la estrategia de hashing definida en infraestructura.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void registrarAccesoExitoso(LocalDateTime fechaAcceso) {
        // TODO implementar registro de acceso exitoso.
        // Debe actualizar la fecha de último acceso
        // y reiniciar el contador de intentos fallidos si corresponde.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void registrarIntentoFallido() {
        // TODO implementar registro de intento fallido.
        // Debe incrementar el contador de intentos fallidos
        // y eventualmente disparar reglas de bloqueo o deshabilitación
        // si la plataforma las define.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void reiniciarIntentosFallidos() {
        // TODO implementar reinicio de intentos fallidos.
        // Debe dejar el contador en el valor base
        // cuando corresponda según el flujo de autenticación.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void habilitar() {
        // TODO implementar habilitación de la credencial.
        // Debe permitir nuevamente su uso para autenticación
        // respetando las reglas de seguridad del sistema.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void deshabilitar() {
        // TODO implementar deshabilitación de la credencial.
        // Debe impedir su uso para autenticación
        // preservando la trazabilidad de la operación.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}