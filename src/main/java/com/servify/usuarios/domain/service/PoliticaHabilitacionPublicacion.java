package com.servify.usuarios.domain.service;

import com.servify.usuarios.domain.model.Usuario;

public class PoliticaHabilitacionPublicacion {

    public boolean puedePublicar(Usuario usuario) {
        // TODO implementar la regla de negocio para determinar si un usuario
        // puede publicar servicios en la plataforma.
        //
        // Debe verificar, como mínimo:
        // - que el usuario exista
        // - que su estado le permita operar
        // - que tenga perfil completo
        // - que la identidad esté validada si la configuración o versión del sistema lo exige
        //
        // Esta clase debe centralizar la política para evitar duplicación de lógica
        // en entidades, casos de uso o controllers.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}