package com.servify.autenticacion.application.port.in;

import com.servify.autenticacion.application.dto.AutenticarConIdentidadExternaCommand;
import com.servify.autenticacion.application.dto.SesionResult;

public interface AutenticarConIdentidadExternaUseCase {

    SesionResult autenticar(AutenticarConIdentidadExternaCommand command);
}
