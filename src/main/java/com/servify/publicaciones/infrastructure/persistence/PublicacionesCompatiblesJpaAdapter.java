package com.servify.publicaciones.infrastructure.persistence;

import com.servify.publicaciones.domain.service.PoliticaCompatibilidadPublicacion;
import com.servify.shared.domain.enumtype.ModalidadServicio;
import com.servify.shared.domain.valueobject.DisponibilidadHoraria;
import com.servify.shared.domain.valueobject.Ubicacion;
import com.servify.solicitudes.application.port.out.PublicacionesCompatiblesPort;
import com.servify.usuarios.infrastructure.persistence.UsuarioJpaAdapter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class PublicacionesCompatiblesJpaAdapter implements PublicacionesCompatiblesPort {

    private final PublicacionServicioJpaRepository publicacionRepo;
    private final CategoriaServicioJpaAdapter categoriaAdapter;
    private final PublicacionJpaAdapter publicacionAdapter;
    private final PoliticaCompatibilidadPublicacion politica;

    public PublicacionesCompatiblesJpaAdapter(PublicacionServicioJpaRepository publicacionRepo,
                                               CategoriaServicioJpaAdapter categoriaAdapter,
                                               PublicacionJpaAdapter publicacionAdapter) {
        this.publicacionRepo = publicacionRepo;
        this.categoriaAdapter = categoriaAdapter;
        this.publicacionAdapter = publicacionAdapter;
        this.politica = new PoliticaCompatibilidadPublicacion();
    }

    @Override
    public Map<UUID, UUID> buscarPublicacionesCompatibles(UUID solicitudId,
                                                          UUID categoriaServicioId,
                                                          ModalidadServicio modalidadRequerida,
                                                          Ubicacion ubicacionRequerida,
                                                          DisponibilidadHoraria disponibilidadRequerida,
                                                          BigDecimal precioMaximo,
                                                          Integer radioBusquedaKm) {
        var categoria = categoriaAdapter.buscarPorId(categoriaServicioId).orElse(null);
        Map<UUID, UUID> compatibles = new LinkedHashMap<>();

        publicacionRepo.findByEstado("activa").forEach(e -> {
            if (precioMaximo != null && e.getPrecioBase() != null
                    && e.getPrecioBase().compareTo(precioMaximo) > 0) {
                return;
            }
            var publicacion = publicacionAdapter.buscarPorId(UsuarioJpaAdapter.uuidFromLong(e.getId())).orElse(null);
            if (publicacion != null && politica.esCompatible(
                    publicacion, categoria, modalidadRequerida, ubicacionRequerida, disponibilidadRequerida)) {
                compatibles.put(publicacion.getId(), publicacion.getUsuarioId());
            }
        });

        return compatibles;
    }
}
