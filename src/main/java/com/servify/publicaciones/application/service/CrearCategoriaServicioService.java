package com.servify.publicaciones.application.service;

import com.servify.publicaciones.application.dto.CategoriaServicioResult;
import com.servify.publicaciones.application.dto.CrearCategoriaServicioCommand;
import com.servify.publicaciones.application.port.in.CrearCategoriaServicioUseCase;
import com.servify.publicaciones.application.port.out.CategoriaServicioRepositoryPort;
import com.servify.publicaciones.domain.model.CategoriaServicio;

import java.util.UUID;

/**
 * Servicio de aplicacion que ejecuta el caso de uso de crear categoria.
 */
public class CrearCategoriaServicioService implements CrearCategoriaServicioUseCase {

    private final CategoriaServicioRepositoryPort categoriaServicioRepositoryPort;

    public CrearCategoriaServicioService(CategoriaServicioRepositoryPort categoriaServicioRepositoryPort) {
        this.categoriaServicioRepositoryPort = categoriaServicioRepositoryPort;
    }

    @Override
    public CategoriaServicioResult crear(CrearCategoriaServicioCommand command) {
        // TODO implementar creacion de categoria.
        // Debe:
        // - validar que el command no sea nulo
        // - validar unicidad de nombre mediante CategoriaServicioRepositoryPort
        // - construir CategoriaServicio con estado inicial activo o inactivo segun politica
        // - persistir la categoria
        // - devolver CategoriaServicioResult usando builder
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected CategoriaServicio construirCategoria(CrearCategoriaServicioCommand command) {
        // TODO implementar construccion inicial de CategoriaServicio.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected CategoriaServicioResult construirResultado(CategoriaServicio categoriaServicio) {
        // TODO implementar mapeo con CategoriaServicioResult.builder().
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected UUID generarIdCategoria() {
        // TODO implementar generacion de identificador para categoria.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }
}
