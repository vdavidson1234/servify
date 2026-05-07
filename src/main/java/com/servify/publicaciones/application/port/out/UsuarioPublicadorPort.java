package com.servify.publicaciones.application.port.out;

import java.util.UUID;

/**
 * Puerto anticorrupcion para consultar si un usuario puede operar como publicador.
 */
public interface UsuarioPublicadorPort {

    boolean existeUsuario(UUID usuarioId);

    boolean puedePublicarServicios(UUID usuarioId);
}
