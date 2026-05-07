package com.servify.publicaciones.application.service;

import com.servify.publicaciones.application.dto.CategoriaServicioResult;
import com.servify.publicaciones.application.port.in.ListarCategoriasActivasUseCase;
import com.servify.publicaciones.application.port.out.CategoriaServicioRepositoryPort;
import com.servify.publicaciones.domain.model.CategoriaServicio;

import java.util.List;

public class ListarCategoriasActivasService implements ListarCategoriasActivasUseCase {

    private final CategoriaServicioRepositoryPort categoriaServicioRepositoryPort;

    public ListarCategoriasActivasService(CategoriaServicioRepositoryPort categoriaServicioRepositoryPort) {
        this.categoriaServicioRepositoryPort = categoriaServicioRepositoryPort;
    }

    @Override
    public List<CategoriaServicioResult> listarActivas() {
        // TODO implementar listado de categorias activas.
        // Debe delegar en CategoriaServicioRepositoryPort y mapear cada resultado.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected CategoriaServicioResult construirResultado(CategoriaServicio categoriaServicio) {
        // TODO implementar mapeo con CategoriaServicioResult.builder().
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }
}
