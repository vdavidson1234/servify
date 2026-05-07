package com.servify.publicaciones.application.port.in;

import com.servify.publicaciones.application.dto.ActualizarPublicacionCommand;
import com.servify.publicaciones.application.dto.PublicacionServicioResult;

public interface ActualizarPublicacionUseCase {

    PublicacionServicioResult actualizar(ActualizarPublicacionCommand command);
}
