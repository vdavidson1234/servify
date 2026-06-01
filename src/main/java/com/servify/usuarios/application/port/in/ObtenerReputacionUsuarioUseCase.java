package com.servify.usuarios.application.port.in;

import com.servify.usuarios.application.dto.ReputacionUsuarioResult;

import java.util.UUID;

public interface ObtenerReputacionUsuarioUseCase {

    ReputacionUsuarioResult obtenerPorUsuarioId(UUID usuarioId);
}
