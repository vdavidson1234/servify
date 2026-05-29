package com.servify.publicaciones.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PublicacionServicioJpaRepository extends JpaRepository<PublicacionServicioJpaEntity, Long> {
    List<PublicacionServicioJpaEntity> findByUsuarioId(Long usuarioId);
    List<PublicacionServicioJpaEntity> findByEstado(String estado);
    List<PublicacionServicioJpaEntity> findByEstadoAndCategoriaId(String estado, Long categoriaId);
    boolean existsByUsuarioIdAndTituloIgnoreCase(Long usuarioId, String titulo);
}
