package com.servify.publicaciones.application.port.in;

import com.servify.publicaciones.application.dto.CambiarEstadoCategoriaServicioCommand;
import com.servify.publicaciones.application.dto.CategoriaServicioResult;

public interface CambiarEstadoCategoriaServicioUseCase {

    CategoriaServicioResult cambiarEstado(CambiarEstadoCategoriaServicioCommand command);
}
