package com.servify.publicaciones.application.dto;

public class CrearCategoriaServicioCommand {

    private String nombre;
    private String descripcion;

    public CrearCategoriaServicioCommand() {
    }

    public CrearCategoriaServicioCommand(String nombre,
                                         String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
