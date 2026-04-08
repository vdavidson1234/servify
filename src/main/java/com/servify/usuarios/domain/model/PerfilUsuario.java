package com.servify.usuarios.domain.model;

import com.servify.shared.domain.model.BaseEntity;
import com.servify.shared.domain.valueobject.Ubicacion;
import com.servify.usuarios.domain.valueobject.NombreCompleto;

import java.util.UUID;

/**
 * Entidad que representa el perfil de usuario en el sistema Servify.
 * Contiene información personal como nombre, edad, foto, ubicación y descripción.
 * Extiende BaseEntity para heredar funcionalidad común (id, auditoría, etc).
 */
public class PerfilUsuario extends BaseEntity {

    // UUID del usuario asociado
    private UUID usuarioId;
    // Nombre y apellido del usuario
    private NombreCompleto nombreCompleto;
    // Edad del usuario
    private Integer edad;
    // URL de la foto de perfil
    private String fotoPerfilUrl;
    // Ubicación geográfica del usuario
    private Ubicacion ubicacion;
    // Descripción personal/biografía del usuario
    private String descripcionPersonal;
    // Bandera que indica si el perfil está completo
    private Boolean perfilCompleto;

    /**
     * Constructor protegido sin argumentos.
     */
    protected PerfilUsuario() {
    }

    /**
     * Crea un perfil de usuario con todos sus datos.
     *
     * @param id identificador único del perfil
     * @param usuarioId identificador del usuario asociado
     * @param nombreCompleto nombre y apellido del usuario
     * @param edad edad del usuario
     * @param fotoPerfilUrl URL de la foto de perfil
     * @param ubicacion ubicación geográfica del usuario
     * @param descripcionPersonal descripción personal/biografía
     * @param perfilCompleto indica si el perfil está completo
     */
    public PerfilUsuario(UUID id,
                         UUID usuarioId,
                         NombreCompleto nombreCompleto,
                         Integer edad,
                         String fotoPerfilUrl,
                         Ubicacion ubicacion,
                         String descripcionPersonal,
                         Boolean perfilCompleto) {
        super(id);
        this.usuarioId = usuarioId;
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.fotoPerfilUrl = fotoPerfilUrl;
        this.ubicacion = ubicacion;
        this.descripcionPersonal = descripcionPersonal;
        this.perfilCompleto = perfilCompleto;
    }

    /**
     * Retorna el ID del usuario asociado.
     */
    public UUID getUsuarioId() {
        return usuarioId;
    }

    /**
     * Retorna el nombre completo del usuario.
     */
    public NombreCompleto getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Retorna la edad del usuario.
     */
    public Integer getEdad() {
        return edad;
    }

    /**
     * Retorna la URL de la foto de perfil.
     */
    public String getFotoPerfilUrl() {
        return fotoPerfilUrl;
    }

    /**
     * Retorna la ubicación geográfica del usuario.
     */
    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    /**
     * Retorna la descripción personal del usuario.
     */
    public String getDescripcionPersonal() {
        return descripcionPersonal;
    }

    /**
     * Retorna si el perfil está marcado como completo.
     */
    public Boolean getPerfilCompleto() {
        return perfilCompleto;
    }

    /**
     * Actualiza el nombre y apellido del perfil.
     * Valida los datos, construye el value object y persiste el cambio.
     *
     * @param nombre nuevo nombre
     * @param apellido nuevo apellido
     */
    public void actualizarNombre(String nombre, String apellido) {
        // TODO implementar actualización del nombre completo del perfil.
        // Debe validar los datos recibidos, construir el value object NombreCompleto
        // y actualizar la referencia en la entidad.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    /**
     * Actualiza la edad del perfil.
     * Valida que sea coherente con las reglas del negocio.
     *
     * @param edad nueva edad
     */
    public void actualizarEdad(Integer edad) {
        // TODO implementar actualización de edad.
        // Debe validar que la edad sea coherente con las reglas del negocio
        // antes de persistir el cambio.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    /**
     * Actualiza la foto de perfil del usuario.
     * Valida formato y presencia según reglas definidas.
     *
     * @param fotoPerfilUrl nueva URL de foto
     */
    public void actualizarFotoPerfil(String fotoPerfilUrl) {
        // TODO implementar actualización de foto de perfil.
        // Debe validar formato/presencia según las reglas definidas por el equipo.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    /**
     * Actualiza la ubicación geográfica del usuario.
     * Valida que cumpla criterios mínimos requeridos.
     *
     * @param ubicacion nueva ubicación
     */
    public void actualizarUbicacion(Ubicacion ubicacion) {
        // TODO implementar actualización de ubicación.
        // Debe validar que la ubicación no sea nula y que cumpla los criterios
        // mínimos requeridos para el perfil.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    /**
     * Actualiza la descripción personal del usuario.
     * Aplica validaciones de longitud y contenido si el negocio las define.
     *
     * @param descripcionPersonal nueva descripción
     */
    public void actualizarDescripcionPersonal(String descripcionPersonal) {
        // TODO implementar actualización de descripción personal.
        // Debe aplicar validaciones de longitud y contenido si el negocio las define.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    /**
     * Evalúa si el perfil cumple con los criterios para considerarse completo.
     * Revisa nombre, edad, foto y ubicación según reglas funcionales.
     *
     * @return true si el perfil está completo, false en caso contrario
     */
    public boolean estaCompleto() {
        // TODO implementar evaluación de perfil completo.
        // Debe revisar nombre completo, edad, foto de perfil y ubicación,
        // según la regla funcional definida para Servify.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    /**
     * Recalcula si el perfil está completo y actualiza el atributo perfilCompleto.
     * Ejecuta la lógica de evaluación e persiste el resultado.
     */
    public void recalcularEstadoPerfilCompleto() {
        // TODO implementar recálculo del atributo perfilCompleto.
        // Debe invocar la lógica que determine si el perfil está completo
        // y actualizar el valor almacenado en la entidad.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    /**
     * Verifica si el usuario tiene nombre completo registrado y válido.
     *
     * @return true si tiene nombre completo, false en caso contrario
     */
    public boolean tieneNombreCompleto() {
        // TODO implementar validación de nombre completo.
        // Debe verificar que el value object exista y sea válido.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    /**
     * Verifica si la edad registrada es válida según reglas del negocio.
     *
     * @return true si la edad es válida, false en caso contrario
     */
    public boolean tieneEdadValida() {
        // TODO implementar validación de edad.
        // Debe verificar que la edad exista y cumpla las reglas del negocio.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    /**
     * Verifica si el usuario tiene foto de perfil registrada.
     *
     * @return true si tiene foto, false en caso contrario
     */
    public boolean tieneFotoPerfil() {
        // TODO implementar validación de foto de perfil.
        // Debe verificar que la URL o referencia de imagen esté informada correctamente.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    /**
     * Verifica si la ubicación registrada es válida y suficiente para operar.
     *
     * @return true si la ubicación es válida, false en caso contrario
     */
    public boolean tieneUbicacionValida() {
        // TODO implementar validación de ubicación.
        // Debe verificar que la ubicación exista y sea suficiente para operar en la plataforma.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}

