package com.servify.publicaciones.application.port.in;

import com.servify.publicaciones.application.dto.PublicacionServicioResult;

import java.util.List;
import java.util.UUID;

public interface ListarPublicacionesPorCategoriaUseCase {

    List<PublicacionServicioResult> listarActivasPorCategoria(UUID categoriaServicioId);
}
