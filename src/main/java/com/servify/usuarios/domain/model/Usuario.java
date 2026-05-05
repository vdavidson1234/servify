package com.servify.usuarios.domain.model;

import com.servify.shared.domain.model.BaseEntity;
import com.servify.usuarios.domain.enumtype.EstadoUsuario;
import com.servify.usuarios.domain.enumtype.EstadoValidacionIdentidad;
import com.servify.usuarios.domain.enumtype.Rol;
import com.servify.usuarios.domain.valueobject.Contacto;

import java.time.LocalDateTime;
import java.util.UUID;

public class Usuario extends BaseEntity {

    private Contacto contacto;
    private Rol rol;
    private EstadoUsuario estado;
    private EstadoValidacionIdentidad estadoValidacionIdentidad;
    private PerfilUsuario perfil;
    private LocalDateTime fechaRegistro;

    protected Usuario() {
    }

    public Usuario(UUID id,
                   Contacto contacto,
                   Rol rol,
                   EstadoUsuario estado,
                   EstadoValidacionIdentidad estadoValidacionIdentidad,
                   PerfilUsuario perfil,
                   LocalDateTime fechaRegistro) {
        super(id);
        this.contacto = contacto;
        this.rol = rol;
        this.estado = estado;
        this.estadoValidacionIdentidad = estadoValidacionIdentidad;
        this.perfil = perfil;
        this.fechaRegistro = fechaRegistro;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public EstadoValidacionIdentidad getEstadoValidacionIdentidad() {
        return estadoValidacionIdentidad;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public boolean esAdmin() {
        // TODO implementar verificación de rol administrador.
        // Debe devolver true cuando el usuario posea rol ADMIN.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean esUsuarioComun() {
        // TODO implementar verificación de rol usuario común.
        // Debe devolver true cuando el usuario posea rol USUARIO.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarRol(Rol rol) {
        // TODO implementar actualización de rol.
        // Debe validar que el nuevo rol no sea nulo y que la transición
        // sea válida según las políticas de acceso y administración del sistema.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean estaActivo() {
        // TODO implementar verificación de estado activo.
        // Debe devolver true únicamente cuando el estado del usuario
        // permita operar normalmente dentro de la plataforma.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean estaSuspendido() {
        // TODO implementar verificación de estado suspendido.
        // Debe devolver true cuando el usuario tenga una restricción temporal
        // aplicada por administración o por reglas del sistema.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean estaBloqueado() {
        // TODO implementar verificación de estado bloqueado.
        // Debe devolver true cuando el usuario esté inhabilitado para operar
        // por una medida más severa o definitiva.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean tienePerfilCompleto() {
        // TODO implementar validación de perfil completo.
        // Debe verificar si el perfil asociado existe y cumple las condiciones
        // mínimas exigidas por la plataforma para operar como prestador.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean puedePublicarServicios() {
        // TODO implementar validación integral para publicación de servicios.
        // Debe verificar, como mínimo:
        // - que el usuario esté habilitado/activo,
        // - que el perfil esté completo,
        // - y que la identidad esté validada si la versión/configuración lo exige.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean identidadValidada() {
        // TODO implementar verificación de identidad validada.
        // Debe devolver true cuando el estado de validación de identidad
        // indique que la validación fue aprobada.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarEmail(String email) {
        // TODO implementar actualización de email.
        // Debe validar el nuevo email, construir o actualizar el value object Contacto
        // y aplicar las reglas necesarias para evitar estados inválidos.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarTelefono(String telefono) {
        // TODO implementar actualización de teléfono.
        // Debe actualizar el value object Contacto preservando el email actual
        // y validando el formato si el negocio lo requiere.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void asociarPerfil(PerfilUsuario perfil) {
        // TODO implementar asociación de perfil al usuario.
        // Debe validar que el perfil no sea nulo y que corresponda al mismo usuario
        // antes de establecer la relación.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void marcarIdentidadComoValidada() {
        // TODO implementar cambio de estado de validación de identidad a VALIDADA.
        // Debe aplicar las verificaciones previas necesarias según el flujo definido.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void marcarIdentidadComoPendiente() {
        // TODO implementar cambio de estado de validación de identidad a PENDIENTE.
        // Debe utilizarse cuando el proceso de validación esté iniciado pero no resuelto.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void marcarIdentidadComoRechazada() {
        // TODO implementar cambio de estado de validación de identidad a RECHAZADA.
        // Debe utilizarse cuando la validación falle o sea denegada.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void suspender() {
        // TODO implementar suspensión del usuario.
        // Debe cambiar el estado a SUSPENDIDO respetando las reglas de transición válidas.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void bloquear() {
        // TODO implementar bloqueo del usuario.
        // Debe cambiar el estado a BLOQUEADO respetando las reglas de transición válidas.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void activar() {
        // TODO implementar activación del usuario.
        // Debe cambiar el estado a ACTIVO solo si la transición es válida
        // según la política de administración y negocio.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void desactivar() {
        // TODO implementar desactivación del usuario.
        // Debe cambiar el estado a INACTIVO cuando corresponda
        // según las reglas definidas por la plataforma.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}