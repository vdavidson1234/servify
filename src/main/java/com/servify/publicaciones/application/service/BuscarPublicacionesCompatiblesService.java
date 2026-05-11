package com.servify.publicaciones.application.service;

import com.servify.publicaciones.application.dto.BuscarPublicacionesCompatiblesQuery;
import com.servify.publicaciones.application.dto.PublicacionCompatibleResult;
import com.servify.publicaciones.application.port.in.BuscarPublicacionesCompatiblesUseCase;
import com.servify.publicaciones.application.port.out.CategoriaServicioRepositoryPort;
import com.servify.publicaciones.application.port.out.PublicacionServicioRepositoryPort;
import com.servify.publicaciones.domain.model.CategoriaServicio;
import com.servify.publicaciones.domain.model.PublicacionServicio;
import com.servify.publicaciones.domain.service.PoliticaCompatibilidadPublicacion;

import java.util.List;

/**
 * Servicio de aplicacion que coordina la busqueda de publicaciones compatibles.
 */
public class BuscarPublicacionesCompatiblesService implements BuscarPublicacionesCompatiblesUseCase {

    private final PublicacionServicioRepositoryPort publicacionServicioRepositoryPort;
    private final CategoriaServicioRepositoryPort categoriaServicioRepositoryPort;
    private final PoliticaCompatibilidadPublicacion politicaCompatibilidadPublicacion;

    public BuscarPublicacionesCompatiblesService(PublicacionServicioRepositoryPort publicacionServicioRepositoryPort,
                                                 CategoriaServicioRepositoryPort categoriaServicioRepositoryPort,
                                                 PoliticaCompatibilidadPublicacion politicaCompatibilidadPublicacion) {
        this.publicacionServicioRepositoryPort = publicacionServicioRepositoryPort;
        this.categoriaServicioRepositoryPort = categoriaServicioRepositoryPort;
        this.politicaCompatibilidadPublicacion = politicaCompatibilidadPublicacion;
    }

    @Override
    public List<PublicacionCompatibleResult> buscarCompatibles(BuscarPublicacionesCompatiblesQuery query) {
        // TODO implementar busqueda de publicaciones compatibles para distribucion.
        // Debe:
        // - validar que la query no sea nula
        // - obtener categoria requerida
        // - consultar candidatas activas por categoria
        // - filtrar con PoliticaCompatibilidadPublicacion
        // - calcular distancia cuando exista soporte geografico
        // - devolver resultados livianos para MotorDistribucionSolicitudes
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected CategoriaServicio obtenerCategoriaRequerida(BuscarPublicacionesCompatiblesQuery query) {
        // TODO implementar obtencion obligatoria de categoria requerida.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected List<PublicacionServicio> obtenerCandidatas(BuscarPublicacionesCompatiblesQuery query) {
        // TODO implementar consulta de publicaciones candidatas.
        // Debe preferir busquedas acotadas por categoria y estado activo.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected boolean esCompatible(PublicacionServicio publicacionServicio,
                                  CategoriaServicio categoriaRequerida,
                                  BuscarPublicacionesCompatiblesQuery query) {
        // TODO implementar delegacion en PoliticaCompatibilidadPublicacion.
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }

    protected PublicacionCompatibleResult construirResultado(PublicacionServicio publicacionServicio,
                                                            Double distanciaKm) {
        // TODO implementar mapeo con PublicacionCompatibleResult.builder().
        throw new UnsupportedOperationException("Pendiente de implementacion");
    }
}
