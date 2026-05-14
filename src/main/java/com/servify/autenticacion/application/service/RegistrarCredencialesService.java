package com.servify.autenticacion.application.service;

import com.servify.autenticacion.application.dto.RegistrarCredencialesCommand;
import com.servify.autenticacion.application.port.in.RegistrarCredencialesUseCase;
import com.servify.autenticacion.application.port.out.CredencialAccesoRepositoryPort;
import com.servify.autenticacion.application.port.out.PasswordHasherPort;
import com.servify.autenticacion.application.port.out.UsuarioAutenticablePort;
import com.servify.autenticacion.domain.model.CredencialAcceso;

import java.util.UUID;

public class RegistrarCredencialesService implements RegistrarCredencialesUseCase {

    private final CredencialAccesoRepositoryPort credencialAccesoRepositoryPort;
    private final PasswordHasherPort passwordHasherPort;
    private final UsuarioAutenticablePort usuarioAutenticablePort;

    public RegistrarCredencialesService(CredencialAccesoRepositoryPort credencialAccesoRepositoryPort,
                                        PasswordHasherPort passwordHasherPort,
                                        UsuarioAutenticablePort usuarioAutenticablePort) {
        this.credencialAccesoRepositoryPort = credencialAccesoRepositoryPort;
        this.passwordHasherPort = passwordHasherPort;
        this.usuarioAutenticablePort = usuarioAutenticablePort;
    }

    @Override
    public void registrar(RegistrarCredencialesCommand command) {
        // TODO implementar registro de credenciales.
        // Debe:
        // - validar command y datos obligatorios
        // - verificar existencia del usuario mediante UsuarioAutenticablePort
        // - validar que el email corresponda al usuario si esa politica aplica
        // - verificar unicidad de email de acceso
        // - hashear password mediante PasswordHasherPort
        // - construir CredencialAcceso habilitada
        // - persistir mediante CredencialAccesoRepositoryPort
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected void validarUsuario(UUID usuarioId, String emailAcceso) {
        // TODO implementar validacion de usuario autenticable sin depender de usuarios.domain.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected CredencialAcceso construirCredencial(RegistrarCredencialesCommand command,
                                                   String passwordHash) {
        // TODO implementar construccion de CredencialAcceso inicial.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected UUID generarIdCredencial() {
        // TODO implementar generacion de identificador de credencial.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }
}
