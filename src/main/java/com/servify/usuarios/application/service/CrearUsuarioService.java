package com.servify.usuarios.application.service;

import com.servify.usuarios.application.dto.CrearUsuarioCommand;
import com.servify.usuarios.application.dto.UsuarioResult;
import com.servify.usuarios.application.port.in.CrearUsuarioUseCase;
import com.servify.usuarios.application.port.out.UsuarioRepositoryPort;
import com.servify.usuarios.domain.enumtype.EstadoUsuario;
import com.servify.usuarios.domain.enumtype.EstadoValidacionIdentidad;
import com.servify.usuarios.domain.model.Usuario;
import com.servify.usuarios.domain.valueobject.Contacto;

import java.time.LocalDateTime;
import java.util.UUID;



public class CrearUsuarioService implements CrearUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public CrearUsuarioService(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public UsuarioResult crear(CrearUsuarioCommand command) {
        // TODO implementar creación de usuario.
        // Debe:
        // - validar que el command no sea nulo
        // - validar que el email no exista previamente
        // - construir el value object Contacto
        // - crear la entidad Usuario con estado inicial válido
        // - persistir el usuario mediante UsuarioRepositoryPort
        // - mapear la entidad persistida a UsuarioResult
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected Usuario construirUsuario(CrearUsuarioCommand command) {
        // TODO implementar construcción inicial del usuario.
        // Debe crear la entidad Usuario con:
        // - id nuevo
        // - Contacto válido
        // - rol recibido en el command
        // - estado inicial adecuado
        // - estado de validación inicial adecuado
        // - perfil aún no asociado
        // - fecha de registro actual
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected Contacto construirContacto(CrearUsuarioCommand command) {
        // TODO implementar construcción del contacto.
        // Debe construir el value object Contacto a partir del email y teléfono
        // recibidos en el command, aplicando las validaciones necesarias.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected UsuarioResult construirResultado(Usuario usuario) {
        // TODO implementar mapeo de Usuario a UsuarioResult.
        // Debe devolver un DTO de salida con los datos relevantes del usuario creado.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected EstadoUsuario obtenerEstadoInicialUsuario() {
        // TODO implementar definición del estado inicial del usuario.
        // Debe devolver el estado con el que una cuenta nueva debe registrarse
        // según las reglas del sistema.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected EstadoValidacionIdentidad obtenerEstadoInicialValidacionIdentidad() {
        // TODO implementar definición del estado inicial de validación de identidad.
        // Debe devolver el estado inicial correcto para una cuenta recién creada
        // según la política vigente del sistema.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected UUID generarIdUsuario() {
        // TODO implementar generación de identificador de usuario.
        // Por el momento puede resolverse con UUID aleatorio si esa es la estrategia elegida.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    protected LocalDateTime obtenerFechaRegistroActual() {
        // TODO implementar obtención de fecha actual de registro.
        // Debe centralizar la fecha/hora usada al crear la cuenta.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}