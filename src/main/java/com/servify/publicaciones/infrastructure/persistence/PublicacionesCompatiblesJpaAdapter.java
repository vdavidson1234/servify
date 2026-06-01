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
            if (tienePrecioMaximo(precioMaximo) && e.getPrecioBase() != null
                    && e.getPrecioBase().compareTo(precioMaximo) > 0) {
                return;
            }
            var publicacion = publicacionAdapter.buscarPorId(UsuarioJpaAdapter.uuidFromLong(e.getId())).orElse(null);
            if (publicacion != null && politica.esCompatible(
                    publicacion, categoria, modalidadRequerida, ubicacionRequerida, disponibilidadRequerida)
                    && estaDentroDelRadio(modalidadRequerida, ubicacionRequerida, publicacion.getUbicacion(), radioBusquedaKm)) {
                compatibles.put(publicacion.getId(), publicacion.getUsuarioId());
            }
        });

        return compatibles;
    }

    private boolean tienePrecioMaximo(BigDecimal precioMaximo) {
        return precioMaximo != null && precioMaximo.compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean estaDentroDelRadio(ModalidadServicio modalidadRequerida,
                                       Ubicacion ubicacionRequerida,
                                       Ubicacion ubicacionPublicacion,
                                       Integer radioBusquedaKm) {
        if (ModalidadServicio.VIRTUAL.equals(modalidadRequerida)) {
            return true;
        }
        if (radioBusquedaKm == null || radioBusquedaKm <= 0) {
            return true;
        }
        if (ubicacionRequerida == null || ubicacionPublicacion == null
                || !ubicacionRequerida.tieneCoordenadasValidas()
                || !ubicacionPublicacion.tieneCoordenadasValidas()) {
            return true;
        }
        return calcularHaversine(
                ubicacionRequerida.getLatitud(),
                ubicacionRequerida.getLongitud(),
                ubicacionPublicacion.getLatitud(),
                ubicacionPublicacion.getLongitud()
        ) <= radioBusquedaKm;
    }

    private double calcularHaversine(double lat1, double lon1, double lat2, double lon2) {
        final int radioTierraKm = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return radioTierraKm * c;
    }
}
