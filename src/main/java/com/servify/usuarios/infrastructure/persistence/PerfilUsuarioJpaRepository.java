package com.servify.usuarios.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PerfilUsuarioJpaRepository extends JpaRepository<PerfilUsuarioJpaEntity, Long> {
    Optional<PerfilUsuarioJpaEntity> findByUsuarioId(Long usuarioId);
}
