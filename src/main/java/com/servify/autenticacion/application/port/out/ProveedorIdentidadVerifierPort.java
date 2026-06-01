package com.servify.autenticacion.application.port.out;

import com.servify.autenticacion.application.dto.AutenticarConIdentidadExternaCommand;
import com.servify.autenticacion.application.dto.IdentidadExternaVerificadaResult;

public interface ProveedorIdentidadVerifierPort {

    IdentidadExternaVerificadaResult verificar(AutenticarConIdentidadExternaCommand command);
}
