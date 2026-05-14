package com.servify.administracion.application.service;

import com.servify.administracion.application.dto.ActualizarConfiguracionGeneralCommand;
import com.servify.administracion.application.dto.ConfiguracionGeneralResult;
import com.servify.administracion.application.port.in.ActualizarConfiguracionGeneralUseCase;
import com.servify.administracion.application.port.out.ConfiguracionGeneralRepositoryPort;
import com.servify.administracion.application.port.out.UsuarioAdministrablePort;
import com.servify.administracion.domain.model.ConfiguracionGeneral;

import java.time.LocalDateTime;
import java.util.UUID;

public class ActualizarConfiguracionGeneralService implements ActualizarConfiguracionGeneralUseCase {

    private final ConfiguracionGeneralRepositoryPort configuracionGeneralRepositoryPort;
    private final UsuarioAdministrablePort usuarioAdministrablePort;

    public ActualizarConfiguracionGeneralService(ConfiguracionGeneralRepositoryPort configuracionGeneralRepositoryPort,
                                                 UsuarioAdministrablePort usuarioAdministrablePort) {
        this.configuracionGeneralRepositoryPort = configuracionGeneralRepositoryPort;
        this.usuarioAdministrablePort = usuarioAdministrablePort;
    }

    @Override
    public ConfiguracionGeneralResult actualizar(ActualizarConfiguracionGeneralCommand command) {
        // TODO implementar actualizacion de configuracion general.
        // Debe:
        // - validar command
        // - validar administrador mediante UsuarioAdministrablePort
        // - obtener configuracion por id o vigente
        // - aplicar cambios invocando metodos del dominio
        // - registrar fecha de actualizacion
        // - persistir configuracion
        // - devolver ConfiguracionGeneralResult usando builder
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected void validarAdministrador(UUID administradorId) {
        // TODO implementar validacion de administrador.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected ConfiguracionGeneral obtenerConfiguracion(ActualizarConfiguracionGeneralCommand command) {
        // TODO implementar obtencion de configuracion a actualizar.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected void aplicarActualizaciones(ConfiguracionGeneral configuracionGeneral,
                                          ActualizarConfiguracionGeneralCommand command,
                                          LocalDateTime fechaActualizacion) {
        // TODO implementar aplicacion de cambios sobre ConfiguracionGeneral.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected ConfiguracionGeneralResult construirResultado(ConfiguracionGeneral configuracionGeneral) {
        // TODO implementar mapeo de ConfiguracionGeneral a result.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected LocalDateTime obtenerFechaActual() {
        // TODO implementar obtencion centralizada de fecha actual.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }
}
