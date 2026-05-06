package com.servify.usuarios.application.service;

import com.servify.shared.domain.valueobject.Ubicacion;
import com.servify.usuarios.application.dto.ConfiguracionCuentaResult;
import com.servify.usuarios.application.dto.PerfilUsuarioResult;
import com.servify.usuarios.application.dto.UbicacionResult;
import com.servify.usuarios.application.dto.UsuarioResult;
import com.servify.usuarios.application.port.in.ObtenerConfiguracionCuentaUseCase;
import com.servify.usuarios.application.port.out.PerfilUsuarioRepositoryPort;
import com.servify.usuarios.application.port.out.UsuarioRepositoryPort;
import com.servify.usuarios.domain.model.PerfilUsuario;
import com.servify.usuarios.domain.model.Usuario;

import java.util.Optional;
import java.util.UUID;

public class ObtenerConfiguracionCuentaService implements ObtenerConfiguracionCuentaUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final PerfilUsuarioRepositoryPort perfilUsuarioRepositoryPort;

    public ObtenerConfiguracionCuentaService(UsuarioRepositoryPort usuarioRepositoryPort,
                                             PerfilUsuarioRepositoryPort perfilUsuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.perfilUsuarioRepositoryPort = perfilUsuarioRepositoryPort;
    }

    @Override
    public Optional<ConfiguracionCuentaResult> obtenerPorUsuarioId(UUID usuarioId) {
        // TODO implementar obtención de configuración de cuenta.
        // Debe:
        // - validar que el usuarioId no sea nulo
        // - consultar el usuario mediante UsuarioRepositoryPort
        // - si el usuario no existe, devolver Optional.empty()
        // - consultar opcionalmente el perfil mediante PerfilUsuarioRepositoryPort
        // - construir UsuarioResult con los datos de cuenta
        // - construir PerfilUsuarioResult solo si existe perfil
        // - devolver ConfiguracionCuentaResult combinando ambos resultados
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected UsuarioResult construirUsuarioResult(Usuario usuario) {
        // TODO implementar mapeo de Usuario a UsuarioResult.
        // Debe incluir:
        // - id
        // - email y teléfono desde Contacto
        // - rol
        // - estado
        // - estado de validación de identidad
        // - fecha de registro
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected PerfilUsuarioResult construirPerfilResult(PerfilUsuario perfilUsuario) {
        // TODO implementar mapeo de PerfilUsuario a PerfilUsuarioResult.
        // Debe incluir:
        // - id del perfil
        // - usuarioId asociado
        // - nombre y apellido desde NombreCompleto
        // - edad
        // - foto de perfil
        // - ubicación mapeada a UbicacionResult
        // - descripción personal
        // - estado de perfil completo
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected UbicacionResult construirUbicacionResult(Ubicacion ubicacion) {
        // TODO implementar mapeo de Ubicacion a UbicacionResult.
        // Debe contemplar el caso de ubicación nula si el perfil todavía no la tiene cargada.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected ConfiguracionCuentaResult construirResultado(UsuarioResult usuarioResult,
                                                           PerfilUsuarioResult perfilUsuarioResult) {
        // TODO implementar construcción de ConfiguracionCuentaResult.
        // Debe combinar:
        // - los datos de cuenta obligatorios
        // - los datos de perfil, que podrían ser nulos si todavía no existe perfil asociado
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}