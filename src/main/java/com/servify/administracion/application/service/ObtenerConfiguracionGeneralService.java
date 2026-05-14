package com.servify.administracion.application.service;

import com.servify.administracion.application.dto.ConfiguracionGeneralResult;
import com.servify.administracion.application.port.in.ObtenerConfiguracionGeneralUseCase;
import com.servify.administracion.application.port.out.ConfiguracionGeneralRepositoryPort;
import com.servify.administracion.domain.model.ConfiguracionGeneral;

import java.util.Optional;
import java.util.UUID;

public class ObtenerConfiguracionGeneralService implements ObtenerConfiguracionGeneralUseCase {

    private final ConfiguracionGeneralRepositoryPort configuracionGeneralRepositoryPort;

    public ObtenerConfiguracionGeneralService(ConfiguracionGeneralRepositoryPort configuracionGeneralRepositoryPort) {
        this.configuracionGeneralRepositoryPort = configuracionGeneralRepositoryPort;
    }

    @Override
    public Optional<ConfiguracionGeneralResult> obtenerVigente() {
        // TODO implementar consulta de configuracion vigente.
        // Debe delegar en ConfiguracionGeneralRepositoryPort y mapear si existe.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    @Override
    public Optional<ConfiguracionGeneralResult> obtenerPorId(UUID configuracionGeneralId) {
        // TODO implementar consulta de configuracion por id.
        // Debe validar id, delegar en repositorio y mapear si existe.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected ConfiguracionGeneralResult construirResultado(ConfiguracionGeneral configuracionGeneral) {
        // TODO implementar mapeo de ConfiguracionGeneral a result.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }
}
