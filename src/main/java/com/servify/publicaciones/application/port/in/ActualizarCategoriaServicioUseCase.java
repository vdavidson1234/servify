package com.servify.publicaciones.application.port.in;

import com.servify.publicaciones.application.dto.ActualizarCategoriaServicioCommand;
import com.servify.publicaciones.application.dto.CategoriaServicioResult;

public interface ActualizarCategoriaServicioUseCase {

    CategoriaServicioResult actualizar(ActualizarCategoriaServicioCommand command);
}
