package com.servify.publicaciones.application.service;

import com.servify.publicaciones.application.dto.CambiarEstadoCategoriaServicioCommand;
import com.servify.publicaciones.application.dto.CategoriaServicioResult;
import com.servify.publicaciones.application.port.in.CambiarEstadoCategoriaServicioUseCase;
import com.servify.publicaciones.application.port.out.CategoriaServicioRepositoryPort;
import com.servify.publicaciones.domain.enumtype.EstadoCategoria;
import com.servify.publicaciones.domain.model.CategoriaServicio;

import java.util.UUID;

public class CambiarEstadoCategoriaServicioService implements CambiarEstadoCategoriaServicioUseCase {

    private final CategoriaServicioRepositoryPort categoriaServicioRepositoryPort;

    public CambiarEstadoCategoriaServicioService(CategoriaServicioRepositoryPort categoriaServicioRepositoryPort) {
        this.categoriaServicioRepositoryPort = categoriaServicioRepositoryPort;
    }

    @Override
    public CategoriaServicioResult cambiarEstado(CambiarEstadoCategoriaServicioCommand command) {
        // TODO implementar cambio unificado de estado de categoria.
        // Debe:
        // - validar command
        // - recuperar categoria existente
        // - validar transicion permitida
        // - aplicar ACTIVA o INACTIVA desde un unico use case
        // - persistir la categoria
        // - devolver CategoriaServicioResult usando builder
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected CategoriaServicio obtenerCategoriaExistente(UUID categoriaServicioId) {
        // TODO implementar busqueda obligatoria de categoria.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected void aplicarEstadoDestino(CategoriaServicio categoriaServicio,
                                        EstadoCategoria estadoDestino) {
        // TODO implementar despacho de transicion de categoria.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected CategoriaServicioResult construirResultado(CategoriaServicio categoriaServicio) {
        // TODO implementar mapeo con CategoriaServicioResult.builder().
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }
}
