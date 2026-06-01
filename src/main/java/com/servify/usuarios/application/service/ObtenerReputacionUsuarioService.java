package com.servify.usuarios.application.service;

import com.servify.solicitudes.application.port.out.CalificacionRepositoryPort;
import com.servify.solicitudes.domain.model.Calificacion;
import com.servify.usuarios.application.dto.ReputacionUsuarioResult;
import com.servify.usuarios.application.port.in.ObtenerReputacionUsuarioUseCase;

import java.util.List;
import java.util.UUID;

public class ObtenerReputacionUsuarioService implements ObtenerReputacionUsuarioUseCase {

    private final CalificacionRepositoryPort calificacionRepositoryPort;

    public ObtenerReputacionUsuarioService(CalificacionRepositoryPort calificacionRepositoryPort) {
        this.calificacionRepositoryPort = calificacionRepositoryPort;
    }

    @Override
    public ReputacionUsuarioResult obtenerPorUsuarioId(UUID usuarioId) {
        if (usuarioId == null) {
            throw new IllegalArgumentException("usuarioId no puede ser nulo");
        }

        List<Calificacion> calificaciones = calificacionRepositoryPort.buscarPorPrestadorId(usuarioId);
        double promedio = calificaciones.stream()
                .filter(calificacion -> calificacion.getPuntaje() != null)
                .mapToInt(Calificacion::getPuntaje)
                .average()
                .orElse(0.0);

        return new ReputacionUsuarioResult(
                usuarioId,
                calificaciones.size(),
                Math.round(promedio * 10.0) / 10.0
        );
    }
}
