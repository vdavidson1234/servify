package com.servify.publicaciones.application.service;

import com.servify.publicaciones.application.dto.ActualizarCategoriaServicioCommand;
import com.servify.publicaciones.application.dto.CategoriaServicioResult;
import com.servify.publicaciones.application.port.in.ActualizarCategoriaServicioUseCase;
import com.servify.publicaciones.application.port.out.CategoriaServicioRepositoryPort;
import com.servify.publicaciones.domain.model.CategoriaServicio;

import java.util.UUID;

public class ActualizarCategoriaServicioService implements ActualizarCategoriaServicioUseCase {

    private final CategoriaServicioRepositoryPort categoriaServicioRepositoryPort;

    public ActualizarCategoriaServicioService(CategoriaServicioRepositoryPort categoriaServicioRepositoryPort) {
        this.categoriaServicioRepositoryPort = categoriaServicioRepositoryPort;
    }

    @Override
    public CategoriaServicioResult actualizar(ActualizarCategoriaServicioCommand command) {
        // TODO implementar actualizacion de categoria.
        // Debe:
        // - validar command
        // - recuperar categoria existente
        // - validar unicidad de nombre si cambia
        // - aplicar actualizaciones mediante metodos del dominio
        // - persistir la categoria
        // - devolver CategoriaServicioResult usando builder
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected CategoriaServicio obtenerCategoriaExistente(UUID categoriaServicioId) {
        // TODO implementar busqueda obligatoria de categoria.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected void aplicarActualizaciones(CategoriaServicio categoriaServicio,
                                          ActualizarCategoriaServicioCommand command) {
        // TODO implementar aplicacion de cambios de nombre y descripcion.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected CategoriaServicioResult construirResultado(CategoriaServicio categoriaServicio) {
        // TODO implementar mapeo con CategoriaServicioResult.builder().
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }
}
