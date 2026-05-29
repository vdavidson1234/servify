package com.servify.autenticacion.infrastructure.persistence;

import com.servify.autenticacion.application.port.out.CredencialAccesoRepositoryPort;
import com.servify.autenticacion.application.port.out.RefreshTokenRepositoryPort;
import com.servify.autenticacion.domain.model.CredencialAcceso;
import com.servify.autenticacion.domain.model.RefreshToken;
import com.servify.usuarios.infrastructure.persistence.UsuarioJpaAdapter;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// ── CredencialAccesoJpaEntity ────────────────────────────────
@Entity
@Table(name = "credencial_acceso")
class CredencialAccesoJpaEntity {
    @Id @Column(columnDefinition = "uuid") private UUID id;
    @Column(name = "usuario_id", nullable = false, unique = true) private Long usuarioId;
    @Column(name = "email_acceso", nullable = false, unique = true) private String emailAcceso;
    @Column(name = "password_hash", nullable = false) private String passwordHash;
    @Column(name = "habilitada", nullable = false) private Boolean habilitada;
    @Column(name = "ultimo_acceso") private LocalDateTime ultimoAcceso;
    @Column(name = "intentos_fallidos", nullable = false) private Integer intentosFallidos;
    @Column(name = "created_at", nullable = false) private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false) private LocalDateTime updatedAt;

    protected CredencialAccesoJpaEntity() {}
    public UUID getId() { return id; } public void setId(UUID id) { this.id = id; }
    public Long getUsuarioId() { return usuarioId; } public void setUsuarioId(Long v) { this.usuarioId = v; }
    public String getEmailAcceso() { return emailAcceso; } public void setEmailAcceso(String v) { this.emailAcceso = v; }
    public String getPasswordHash() { return passwordHash; } public void setPasswordHash(String v) { this.passwordHash = v; }
    public Boolean getHabilitada() { return habilitada; } public void setHabilitada(Boolean v) { this.habilitada = v; }
    public LocalDateTime getUltimoAcceso() { return ultimoAcceso; } public void setUltimoAcceso(LocalDateTime v) { this.ultimoAcceso = v; }
    public Integer getIntentosFallidos() { return intentosFallidos; } public void setIntentosFallidos(Integer v) { this.intentosFallidos = v; }
    public LocalDateTime getCreatedAt() { return createdAt; } public void setCreatedAt(LocalDateTime v) { this.createdAt = v; }
    public LocalDateTime getUpdatedAt() { return updatedAt; } public void setUpdatedAt(LocalDateTime v) { this.updatedAt = v; }
}

// ── RefreshTokenJpaEntity ────────────────────────────────────
@Entity
@Table(name = "refresh_token")
class RefreshTokenJpaEntity {
    @Id @Column(columnDefinition = "uuid") private UUID id;
    @Column(name = "usuario_id", nullable = false) private Long usuarioId;
    @Column(name = "credencial_acceso_id", nullable = false, columnDefinition = "uuid") private UUID credencialAccesoId;
    @Column(name = "token_hash", nullable = false, unique = true) private String tokenHash;
    @Column(name = "fecha_emision", nullable = false) private LocalDateTime fechaEmision;
    @Column(name = "fecha_expiracion", nullable = false) private LocalDateTime fechaExpiracion;
    @Column(name = "fecha_revocacion") private LocalDateTime fechaRevocacion;
    @Column(name = "activo", nullable = false) private Boolean activo;
    @Column(name = "created_at", nullable = false) private LocalDateTime createdAt;

    protected RefreshTokenJpaEntity() {}
    public UUID getId() { return id; } public void setId(UUID id) { this.id = id; }
    public Long getUsuarioId() { return usuarioId; } public void setUsuarioId(Long v) { this.usuarioId = v; }
    public UUID getCredencialAccesoId() { return credencialAccesoId; } public void setCredencialAccesoId(UUID v) { this.credencialAccesoId = v; }
    public String getTokenHash() { return tokenHash; } public void setTokenHash(String v) { this.tokenHash = v; }
    public LocalDateTime getFechaEmision() { return fechaEmision; } public void setFechaEmision(LocalDateTime v) { this.fechaEmision = v; }
    public LocalDateTime getFechaExpiracion() { return fechaExpiracion; } public void setFechaExpiracion(LocalDateTime v) { this.fechaExpiracion = v; }
    public LocalDateTime getFechaRevocacion() { return fechaRevocacion; } public void setFechaRevocacion(LocalDateTime v) { this.fechaRevocacion = v; }
    public Boolean getActivo() { return activo; } public void setActivo(Boolean v) { this.activo = v; }
    public LocalDateTime getCreatedAt() { return createdAt; } public void setCreatedAt(LocalDateTime v) { this.createdAt = v; }
}

// ── Repositories ─────────────────────────────────────────────
interface CredencialAccesoJpaRepository extends JpaRepository<CredencialAccesoJpaEntity, UUID> {
    Optional<CredencialAccesoJpaEntity> findByUsuarioId(Long usuarioId);
    Optional<CredencialAccesoJpaEntity> findByEmailAccesoIgnoreCase(String emailAcceso);
    boolean existsByEmailAccesoIgnoreCase(String emailAcceso);
}

interface RefreshTokenJpaRepository extends JpaRepository<RefreshTokenJpaEntity, UUID> {
    Optional<RefreshTokenJpaEntity> findByTokenHash(String tokenHash);
    List<RefreshTokenJpaEntity> findByUsuarioIdAndActivoTrue(Long usuarioId);
}

// ── Adapter ──────────────────────────────────────────────────
@Component
public class AutenticacionJpaAdapter implements CredencialAccesoRepositoryPort, RefreshTokenRepositoryPort {

    private final CredencialAccesoJpaRepository credencialRepo;
    private final RefreshTokenJpaRepository refreshTokenRepo;

    public AutenticacionJpaAdapter(CredencialAccesoJpaRepository credencialRepo,
                                    RefreshTokenJpaRepository refreshTokenRepo) {
        this.credencialRepo = credencialRepo;
        this.refreshTokenRepo = refreshTokenRepo;
    }

    // ── CredencialAccesoRepositoryPort ───────────────────────

    @Override
    public CredencialAcceso guardar(CredencialAcceso c) {
        CredencialAccesoJpaEntity e = toCredencialEntity(c);
        if (e.getCreatedAt() == null) e.setCreatedAt(LocalDateTime.now());
        e.setUpdatedAt(LocalDateTime.now());
        return toCredencialDomain(credencialRepo.save(e));
    }

    @Override
    public Optional<CredencialAcceso> buscarPorId(UUID credencialId) {
        return credencialRepo.findById(credencialId).map(this::toCredencialDomain);
    }

    @Override
    public Optional<CredencialAcceso> buscarPorUsuarioId(UUID usuarioId) {
        return credencialRepo.findByUsuarioId(UsuarioJpaAdapter.uuidFromLong(usuarioId).getLeastSignificantBits())
                .map(this::toCredencialDomain);
    }

    @Override
    public Optional<CredencialAcceso> buscarPorEmailAcceso(String emailAcceso) {
        return credencialRepo.findByEmailAccesoIgnoreCase(emailAcceso).map(this::toCredencialDomain);
    }

    @Override
    public boolean existePorEmailAcceso(String emailAcceso) {
        return credencialRepo.existsByEmailAccesoIgnoreCase(emailAcceso);
    }

    // ── RefreshTokenRepositoryPort ───────────────────────────

    @Override
    public RefreshToken guardar(RefreshToken r) {
        RefreshTokenJpaEntity e = toRefreshEntity(r);
        if (e.getCreatedAt() == null) e.setCreatedAt(LocalDateTime.now());
        return toRefreshDomain(refreshTokenRepo.save(e));
    }

    @Override
    public Optional<RefreshToken> buscarPorId(UUID refreshTokenId) {
        return refreshTokenRepo.findById(refreshTokenId).map(this::toRefreshDomain);
    }

    @Override
    public Optional<RefreshToken> buscarPorTokenHash(String tokenHash) {
        return refreshTokenRepo.findByTokenHash(tokenHash).map(this::toRefreshDomain);
    }

    @Override
    public List<RefreshToken> buscarActivosPorUsuarioId(UUID usuarioId) {
        return refreshTokenRepo.findByUsuarioIdAndActivoTrue(usuarioId.getLeastSignificantBits())
                .stream().map(this::toRefreshDomain).toList();
    }

    // ── Mapeos ───────────────────────────────────────────────

    private CredencialAccesoJpaEntity toCredencialEntity(CredencialAcceso c) {
        CredencialAccesoJpaEntity e = new CredencialAccesoJpaEntity();
        e.setId(c.getId());
        e.setUsuarioId(c.getUsuarioId() != null ? c.getUsuarioId().getLeastSignificantBits() : null);
        e.setEmailAcceso(c.getEmailAcceso());
        e.setPasswordHash(c.getPasswordHash());
        e.setHabilitada(c.getHabilitada());
        e.setUltimoAcceso(c.getUltimoAcceso());
        e.setIntentosFallidos(c.getIntentosFallidos() != null ? c.getIntentosFallidos() : 0);
        return e;
    }

    private CredencialAcceso toCredencialDomain(CredencialAccesoJpaEntity e) {
        CredencialAcceso c = new CredencialAcceso(
                e.getId(),
                UsuarioJpaAdapter.uuidFromLong(e.getUsuarioId()),
                e.getEmailAcceso(), e.getPasswordHash(), e.getHabilitada(),
                e.getUltimoAcceso(), e.getIntentosFallidos());
        if (e.getCreatedAt() != null) c.marcarCreacion(e.getCreatedAt());
        if (e.getUpdatedAt() != null) c.marcarModificacion(e.getUpdatedAt());
        return c;
    }

    private RefreshTokenJpaEntity toRefreshEntity(RefreshToken r) {
        RefreshTokenJpaEntity e = new RefreshTokenJpaEntity();
        e.setId(r.getId());
        e.setUsuarioId(r.getUsuarioId() != null ? r.getUsuarioId().getLeastSignificantBits() : null);
        e.setCredencialAccesoId(r.getCredencialAccesoId());
        e.setTokenHash(r.getTokenHash());
        e.setFechaEmision(r.getFechaEmision());
        e.setFechaExpiracion(r.getFechaExpiracion());
        e.setFechaRevocacion(r.getFechaRevocacion());
        e.setActivo(r.getActivo());
        return e;
    }

    private RefreshToken toRefreshDomain(RefreshTokenJpaEntity e) {
        RefreshToken r = new RefreshToken(
                e.getId(),
                UsuarioJpaAdapter.uuidFromLong(e.getUsuarioId()),
                e.getCredencialAccesoId(), e.getTokenHash(),
                e.getFechaEmision(), e.getFechaExpiracion(),
                e.getFechaRevocacion(), e.getActivo());
        if (e.getCreatedAt() != null) r.marcarCreacion(e.getCreatedAt());
        return r;
    }
}
