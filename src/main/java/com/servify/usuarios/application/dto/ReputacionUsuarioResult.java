package com.servify.usuarios.application.dto;

import java.util.UUID;

public class ReputacionUsuarioResult {

    private final UUID usuarioId;
    private final long cantidadValoraciones;
    private final double promedioEstrellas;

    public ReputacionUsuarioResult(UUID usuarioId, long cantidadValoraciones, double promedioEstrellas) {
        this.usuarioId = usuarioId;
        this.cantidadValoraciones = cantidadValoraciones;
        this.promedioEstrellas = promedioEstrellas;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public long getCantidadValoraciones() {
        return cantidadValoraciones;
    }

    public double getPromedioEstrellas() {
        return promedioEstrellas;
    }
}
