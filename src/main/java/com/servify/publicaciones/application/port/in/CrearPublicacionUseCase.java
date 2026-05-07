package com.servify.publicaciones.application.port.in;

import com.servify.publicaciones.application.dto.CrearPublicacionCommand;
import com.servify.publicaciones.application.dto.PublicacionServicioResult;

public interface CrearPublicacionUseCase {

    PublicacionServicioResult crear(CrearPublicacionCommand command);
}
