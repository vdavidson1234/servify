package com.servify.administracion.application.service;

import com.servify.administracion.application.dto.MedidaAdministrativaUsuarioResult;
import com.servify.administracion.application.port.in.ObtenerMedidasAdministrativasDeUsuarioUseCase;
import com.servify.administracion.application.port.out.MedidaAdministrativaUsuarioRepositoryPort;
import com.servify.administracion.domain.model.MedidaAdministrativaUsuario;

import java.util.List;
import java.util.UUID;

public class ObtenerMedidasAdministrativasDeUsuarioService implements ObtenerMedidasAdministrativasDeUsuarioUseCase {

    private final MedidaAdministrativaUsuarioRepositoryPort medidaAdministrativaUsuarioRepositoryPort;

    public ObtenerMedidasAdministrativasDeUsuarioService(MedidaAdministrativaUsuarioRepositoryPort medidaAdministrativaUsuarioRepositoryPort) {
        this.medidaAdministrativaUsuarioRepositoryPort = medidaAdministrativaUsuarioRepositoryPort;
    }

    @Override
    public List<MedidaAdministrativaUsuarioResult> obtenerPorUsuarioId(UUID usuarioId) {
        // TODO implementar consulta de medidas administrativas por usuario.
        // Debe validar usuarioId, consultar repositorio y mapear resultados.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected MedidaAdministrativaUsuarioResult construirResultado(MedidaAdministrativaUsuario medidaAdministrativaUsuario) {
        // TODO implementar mapeo de MedidaAdministrativaUsuario a result.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }
}
