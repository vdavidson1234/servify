package com.servify.publicaciones.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DisponibilidadHorariaJpaRepository extends JpaRepository<DisponibilidadHorariaJpaEntity, Long> {
    List<DisponibilidadHorariaJpaEntity> findByPublicacionId(Long publicacionId);
    void deleteByPublicacionId(Long publicacionId);
}
