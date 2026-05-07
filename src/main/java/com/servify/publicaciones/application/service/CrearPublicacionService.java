package com.servify.publicaciones.application.service;

import com.servify.publicaciones.application.dto.CrearPublicacionCommand;
import com.servify.publicaciones.application.dto.PublicacionServicioResult;
import com.servify.publicaciones.application.port.in.CrearPublicacionUseCase;
import com.servify.publicaciones.application.port.out.CategoriaServicioRepositoryPort;
import com.servify.publicaciones.application.port.out.PublicacionServicioRepositoryPort;
import com.servify.publicaciones.application.port.out.UsuarioPublicadorPort;
import com.servify.publicaciones.domain.model.CategoriaServicio;
import com.servify.publicaciones.domain.model.PublicacionServicio;
import com.servify.publicaciones.domain.service.ValidadorDisponibilidadHoraria;

import java.util.UUID;

public class CrearPublicacionService implements CrearPublicacionUseCase {

    private final PublicacionServicioRepositoryPort publicacionServicioRepositoryPort;
    private final CategoriaServicioRepositoryPort categoriaServicioRepositoryPort;
    private final UsuarioPublicadorPort usuarioPublicadorPort;
    private final ValidadorDisponibilidadHoraria validadorDisponibilidadHoraria;

    public CrearPublicacionService(PublicacionServicioRepositoryPort publicacionServicioRepositoryPort,
                                   CategoriaServicioRepositoryPort categoriaServicioRepositoryPort,
                                   UsuarioPublicadorPort usuarioPublicadorPort,
                                   ValidadorDisponibilidadHoraria validadorDisponibilidadHoraria) {
        this.publicacionServicioRepositoryPort = publicacionServicioRepositoryPort;
        this.categoriaServicioRepositoryPort = categoriaServicioRepositoryPort;
        this.usuarioPublicadorPort = usuarioPublicadorPort;
        this.validadorDisponibilidadHoraria = validadorDisponibilidadHoraria;
    }

    @Override
    public PublicacionServicioResult crear(CrearPublicacionCommand command) {
        // TODO implementar creacion de publicacion.
        // Debe:
        // - validar que el command no sea nulo
        // - verificar que el usuario exista y pueda publicar servicios mediante UsuarioPublicadorPort
        // - validar que no exista una publicacion duplicada para el usuario si aplica la politica
        // - recuperar una CategoriaServicio activa
        // - validar modalidad, ubicacion, disponibilidades y precio base
        // - construir la entidad PublicacionServicio con estado inicial correcto
        // - persistir mediante PublicacionServicioRepositoryPort
        // - devolver PublicacionServicioResult usando builder
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected void validarUsuarioPuedePublicar(UUID usuarioId) {
        // TODO implementar validacion de publicador.
        // Debe delegar en UsuarioPublicadorPort para mantener bajo acoplamiento
        // con el modulo de usuarios.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected CategoriaServicio obtenerCategoriaActiva(UUID categoriaServicioId) {
        // TODO implementar obtencion obligatoria de categoria activa.
        // Debe buscar la categoria, validar existencia y confirmar que este activa.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected void validarDisponibilidades(CrearPublicacionCommand command) {
        // TODO implementar validacion de disponibilidades.
        // Debe delegar en ValidadorDisponibilidadHoraria para centralizar
        // reglas de rango horario y superposiciones.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected PublicacionServicio construirPublicacion(CrearPublicacionCommand command,
                                                       CategoriaServicio categoriaServicio) {
        // TODO implementar construccion inicial de la publicacion.
        // Debe generar id, asociar usuario/categoria, asignar estado inicial
        // y copiar los datos principales del command.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected PublicacionServicioResult construirResultado(PublicacionServicio publicacionServicio) {
        // TODO implementar mapeo de PublicacionServicio a PublicacionServicioResult.
        // Debe utilizar PublicacionServicioResult.builder() para evitar constructores gigantes.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected UUID generarIdPublicacion() {
        // TODO implementar generacion de identificador para publicacion.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }
}
