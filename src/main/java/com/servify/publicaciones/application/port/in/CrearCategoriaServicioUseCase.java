package com.servify.publicaciones.application.port.in;

import com.servify.publicaciones.application.dto.CategoriaServicioResult;
import com.servify.publicaciones.application.dto.CrearCategoriaServicioCommand;

public interface CrearCategoriaServicioUseCase {

    CategoriaServicioResult crear(CrearCategoriaServicioCommand command);
}
