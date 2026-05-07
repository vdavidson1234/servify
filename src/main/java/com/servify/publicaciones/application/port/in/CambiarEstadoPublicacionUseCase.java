package com.servify.publicaciones.application.port.in;

import com.servify.publicaciones.application.dto.CambiarEstadoPublicacionCommand;
import com.servify.publicaciones.application.dto.PublicacionServicioResult;

public interface CambiarEstadoPublicacionUseCase {

    PublicacionServicioResult cambiarEstado(CambiarEstadoPublicacionCommand command);
}
