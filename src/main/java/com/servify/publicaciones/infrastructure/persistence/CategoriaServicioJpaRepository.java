package com.servify.publicaciones.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CategoriaServicioJpaRepository extends JpaRepository<CategoriaServicioJpaEntity, Long> {
    Optional<CategoriaServicioJpaEntity> findByNombreIgnoreCase(String nombre);
    List<CategoriaServicioJpaEntity> findByActivaTrue();
    boolean existsByNombreIgnoreCase(String nombre);
}
