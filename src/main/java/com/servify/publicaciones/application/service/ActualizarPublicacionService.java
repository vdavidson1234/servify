package com.servify.publicaciones.application.service;

import com.servify.publicaciones.application.dto.ActualizarPublicacionCommand;
import com.servify.publicaciones.application.dto.PublicacionServicioResult;
import com.servify.publicaciones.application.port.in.ActualizarPublicacionUseCase;
import com.servify.publicaciones.application.port.out.CategoriaServicioRepositoryPort;
import com.servify.publicaciones.application.port.out.PublicacionServicioRepositoryPort;
import com.servify.publicaciones.domain.model.CategoriaServicio;
import com.servify.publicaciones.domain.model.PublicacionServicio;
import com.servify.publicaciones.domain.service.ValidadorDisponibilidadHoraria;

import java.util.Optional;
import java.util.UUID;

public class ActualizarPublicacionService implements ActualizarPublicacionUseCase {

    private final PublicacionServicioRepositoryPort publicacionServicioRepositoryPort;
    private final CategoriaServicioRepositoryPort categoriaServicioRepositoryPort;
    private final ValidadorDisponibilidadHoraria validadorDisponibilidadHoraria;

    public ActualizarPublicacionService(PublicacionServicioRepositoryPort publicacionServicioRepositoryPort,
                                        CategoriaServicioRepositoryPort categoriaServicioRepositoryPort,
                                        ValidadorDisponibilidadHoraria validadorDisponibilidadHoraria) {
        this.publicacionServicioRepositoryPort = publicacionServicioRepositoryPort;
        this.categoriaServicioRepositoryPort = categoriaServicioRepositoryPort;
        this.validadorDisponibilidadHoraria = validadorDisponibilidadHoraria;
    }

    @Override
    public PublicacionServicioResult actualizar(ActualizarPublicacionCommand command) {
        // TODO implementar actualizacion de publicacion.
        // Debe:
        // - validar que el command no sea nulo
        // - recuperar la publicacion existente
        // - validar que pertenezca al usuario indicado
        // - obtener la nueva categoria si fue informada
        // - aplicar cambios invocando metodos del dominio
        // - validar disponibilidades si fueron modificadas
        // - persistir la publicacion actualizada
        // - devolver PublicacionServicioResult usando builder
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected PublicacionServicio obtenerPublicacionExistente(UUID publicacionServicioId) {
        // TODO implementar busqueda obligatoria de publicacion.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected void validarPertenencia(PublicacionServicio publicacionServicio,
                                      UUID usuarioId) {
        // TODO implementar validacion de pertenencia.
        // Debe usar PublicacionServicio.perteneceA(usuarioId).
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected Optional<CategoriaServicio> obtenerCategoriaSiCorresponde(UUID categoriaServicioId) {
        // TODO implementar busqueda opcional de categoria.
        // Si se informa una categoria nueva, debe existir y estar activa.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected void aplicarActualizaciones(PublicacionServicio publicacionServicio,
                                          ActualizarPublicacionCommand command,
                                          Optional<CategoriaServicio> categoriaServicio) {
        // TODO implementar aplicacion de cambios sobre el agregado.
        // Debe invocar los metodos actualizarTitulo, actualizarDescripcion,
        // actualizarCategoria, actualizarModalidad, actualizarUbicacion,
        // actualizarDisponibilidades y actualizarPrecioBase segun corresponda.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected void validarDisponibilidades(ActualizarPublicacionCommand command) {
        // TODO implementar validacion de disponibilidades cuando sean informadas.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected PublicacionServicioResult construirResultado(PublicacionServicio publicacionServicio) {
        // TODO implementar mapeo con PublicacionServicioResult.builder().
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }
}
