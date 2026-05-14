package com.servify.administracion.application.service;

import com.servify.administracion.application.dto.AplicarMedidaAdministrativaUsuarioCommand;
import com.servify.administracion.application.dto.MedidaAdministrativaUsuarioResult;
import com.servify.administracion.application.port.in.AplicarMedidaAdministrativaUsuarioUseCase;
import com.servify.administracion.application.port.out.MedidaAdministrativaUsuarioRepositoryPort;
import com.servify.administracion.application.port.out.UsuarioAdministrablePort;
import com.servify.administracion.domain.model.MedidaAdministrativaUsuario;

import java.time.LocalDateTime;
import java.util.UUID;

public class AplicarMedidaAdministrativaUsuarioService implements AplicarMedidaAdministrativaUsuarioUseCase {

    private final MedidaAdministrativaUsuarioRepositoryPort medidaAdministrativaUsuarioRepositoryPort;
    private final UsuarioAdministrablePort usuarioAdministrablePort;

    public AplicarMedidaAdministrativaUsuarioService(MedidaAdministrativaUsuarioRepositoryPort medidaAdministrativaUsuarioRepositoryPort,
                                                    UsuarioAdministrablePort usuarioAdministrablePort) {
        this.medidaAdministrativaUsuarioRepositoryPort = medidaAdministrativaUsuarioRepositoryPort;
        this.usuarioAdministrablePort = usuarioAdministrablePort;
    }

    @Override
    public MedidaAdministrativaUsuarioResult aplicar(AplicarMedidaAdministrativaUsuarioCommand command) {
        // TODO implementar aplicacion de medida administrativa.
        // Debe:
        // - validar command
        // - verificar que el administrador exista y tenga permisos mediante UsuarioAdministrablePort
        // - verificar que el usuario afectado exista
        // - construir MedidaAdministrativaUsuario
        // - persistir la medida
        // - aplicar el efecto sobre el usuario mediante UsuarioAdministrablePort
        // - devolver MedidaAdministrativaUsuarioResult usando builder
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected void validarAdministrador(UUID administradorId) {
        // TODO implementar validacion de administrador.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected void validarUsuarioAfectado(UUID usuarioId) {
        // TODO implementar validacion de usuario afectado.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected MedidaAdministrativaUsuario construirMedida(AplicarMedidaAdministrativaUsuarioCommand command,
                                                          LocalDateTime fechaAplicacion) {
        // TODO implementar construccion de MedidaAdministrativaUsuario.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected MedidaAdministrativaUsuarioResult construirResultado(MedidaAdministrativaUsuario medidaAdministrativaUsuario) {
        // TODO implementar mapeo de MedidaAdministrativaUsuario a result.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected UUID generarIdMedida() {
        // TODO implementar generacion de identificador de medida.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected LocalDateTime obtenerFechaActual() {
        // TODO implementar obtencion centralizada de fecha actual.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }
}
