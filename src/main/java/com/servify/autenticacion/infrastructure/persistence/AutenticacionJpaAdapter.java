package com.servify.autenticacion.infrastructure.persistence;

import com.servify.autenticacion.application.port.out.CredencialAccesoRepositoryPort;
import com.servify.autenticacion.application.port.out.IdentidadExternaRepositoryPort;
import com.servify.autenticacion.application.port.out.RefreshTokenRepositoryPort;
import com.servify.autenticacion.domain.enumtype.ProveedorIdentidadExterna;
import com.servify.autenticacion.domain.model.CredencialAcceso;
import com.servify.autenticacion.domain.model.IdentidadExterna;
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
    @Column(name = "credencial_acceso_id", columnDefinition = "uuid") private UUID credencialAccesoId;
    @Column(name = "identidad_externa_id", columnDefinition = "uuid") private UUID identidadExternaId;
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
    public UUID getIdentidadExternaId() { return identidadExternaId; } public void setIdentidadExternaId(UUID v) { this.identidadExternaId = v; }
    public String getTokenHash() { return tokenHash; } public void setTokenHash(String v) { this.tokenHash = v; }
    public LocalDateTime getFechaEmision() { return fechaEmision; } public void setFechaEmision(LocalDateTime v) { this.fechaEmision = v; }
    public LocalDateTime getFechaExpiracion() { return fechaExpiracion; } public void setFechaExpiracion(LocalDateTime v) { this.fechaExpiracion = v; }
    public LocalDateTime getFechaRevocacion() { return fechaRevocacion; } public void setFechaRevocacion(LocalDateTime v) { this.fechaRevocacion = v; }
    public Boolean getActivo() { return activo; } public void setActivo(Boolean v) { this.activo = v; }
    public LocalDateTime getCreatedAt() { return createdAt; } public void setCreatedAt(LocalDateTime v) { this.createdAt = v; }
}

// ── IdentidadExternaJpaEntity ─────────────────────────────────
@Entity
@Table(name = "identidad_externa")
class IdentidadExternaJpaEntity {
    @Id @Column(columnDefinition = "uuid") private UUID id;
    @Column(name = "usuario_id", nullable = false) private Long usuarioId;
    @Column(name = "proveedor", nullable = false) private String proveedor;
    @Column(name = "subject", nullable = false) private String subject;
    @Column(name = "email", nullable = false) private String email;
    @Column(name = "email_verificado", nullable = false) private Boolean emailVerificado;
    @Column(name = "nombre_mostrado") private String nombreMostrado;
    @Column(name = "fecha_vinculacion", nullable = false) private LocalDateTime fechaVinculacion;
    @Column(name = "ultimo_acceso") private LocalDateTime ultimoAcceso;
    @Column(name = "habilitada", nullable = false) private Boolean habilitada;
    @Column(name = "created_at", nullable = false) private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false) private LocalDateTime updatedAt;

    protected IdentidadExternaJpaEntity() {}
    public UUID getId() { return id; } public void setId(UUID id) { this.id = id; }
    public Long getUsuarioId() { return usuarioId; } public void setUsuarioId(Long v) { this.usuarioId = v; }
    public String getProveedor() { return proveedor; } public void setProveedor(String v) { this.proveedor = v; }
    public String getSubject() { return subject; } public void setSubject(String v) { this.subject = v; }
    public String getEmail() { return email; } public void setEmail(String v) { this.email = v; }
    public Boolean getEmailVerificado() { return emailVerificado; } public void setEmailVerificado(Boolean v) { this.emailVerificado = v; }
    public String getNombreMostrado() { return nombreMostrado; } public void setNombreMostrado(String v) { this.nombreMostrado = v; }
    public LocalDateTime getFechaVinculacion() { return fechaVinculacion; } public void setFechaVinculacion(LocalDateTime v) { this.fechaVinculacion = v; }
    public LocalDateTime getUltimoAcceso() { return ultimoAcceso; } public void setUltimoAcceso(LocalDateTime v) { this.ultimoAcceso = v; }
    public Boolean getHabilitada() { return habilitada; } public void setHabilitada(Boolean v) { this.habilitada = v; }
    public LocalDateTime getCreatedAt() { return createdAt; } public void setCreatedAt(LocalDateTime v) { this.createdAt = v; }
    public LocalDateTime getUpdatedAt() { return updatedAt; } public void setUpdatedAt(LocalDateTime v) { this.updatedAt = v; }
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

interface IdentidadExternaJpaRepository extends JpaRepository<IdentidadExternaJpaEntity, UUID> {
    Optional<IdentidadExternaJpaEntity> findByProveedorAndSubject(String proveedor, String subject);
    Optional<IdentidadExternaJpaEntity> findByUsuarioIdAndProveedor(Long usuarioId, String proveedor);
    List<IdentidadExternaJpaEntity> findByUsuarioId(Long usuarioId);
}

// ── CredencialAccesoJpaAdapter ───────────────────────────────
@Component
class CredencialAccesoJpaAdapter implements CredencialAccesoRepositoryPort {

    private final CredencialAccesoJpaRepository credencialRepo;

    CredencialAccesoJpaAdapter(CredencialAccesoJpaRepository credencialRepo) {
        this.credencialRepo = credencialRepo;
    }

    @Override
    public CredencialAcceso guardar(CredencialAcceso c) {
        CredencialAccesoJpaEntity e = toEntity(c);
        if (e.getCreatedAt() == null) e.setCreatedAt(LocalDateTime.now());
        e.setUpdatedAt(LocalDateTime.now());
        return toDomain(credencialRepo.save(e));
    }

    @Override
    public Optional<CredencialAcceso> buscarPorId(UUID credencialId) {
        return credencialRepo.findById(credencialId).map(this::toDomain);
    }

    @Override
    public Optional<CredencialAcceso> buscarPorUsuarioId(UUID usuarioId) {
        return credencialRepo.findByUsuarioId(usuarioId.getLeastSignificantBits()).map(this::toDomain);
    }

    @Override
    public Optional<CredencialAcceso> buscarPorEmailAcceso(String emailAcceso) {
        return credencialRepo.findByEmailAccesoIgnoreCase(emailAcceso).map(this::toDomain);
    }

    @Override
    public boolean existePorEmailAcceso(String emailAcceso) {
        return credencialRepo.existsByEmailAccesoIgnoreCase(emailAcceso);
    }

    private CredencialAccesoJpaEntity toEntity(CredencialAcceso c) {
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

    private CredencialAcceso toDomain(CredencialAccesoJpaEntity e) {
        CredencialAcceso c = new CredencialAcceso(
                e.getId(), UsuarioJpaAdapter.uuidFromLong(e.getUsuarioId()),
                e.getEmailAcceso(), e.getPasswordHash(), e.getHabilitada(),
                e.getUltimoAcceso(), e.getIntentosFallidos());
        if (e.getCreatedAt() != null) c.marcarCreacion(e.getCreatedAt());
        if (e.getUpdatedAt() != null) c.marcarModificacion(e.getUpdatedAt());
        return c;
    }
}

// ── IdentidadExternaJpaAdapter ────────────────────────────────
@Component
class IdentidadExternaJpaAdapter implements IdentidadExternaRepositoryPort {

    private final IdentidadExternaJpaRepository identidadRepo;

    IdentidadExternaJpaAdapter(IdentidadExternaJpaRepository identidadRepo) {
        this.identidadRepo = identidadRepo;
    }

    @Override
    public IdentidadExterna guardar(IdentidadExterna identidad) {
        IdentidadExternaJpaEntity e = toEntity(identidad);
        if (e.getCreatedAt() == null) e.setCreatedAt(LocalDateTime.now());
        e.setUpdatedAt(LocalDateTime.now());
        return toDomain(identidadRepo.save(e));
    }

    @Override
    public Optional<IdentidadExterna> buscarPorId(UUID identidadExternaId) {
        return identidadRepo.findById(identidadExternaId).map(this::toDomain);
    }

    @Override
    public Optional<IdentidadExterna> buscarPorProveedorYSubject(
            ProveedorIdentidadExterna proveedor,
            String subject
    ) {
        return identidadRepo.findByProveedorAndSubject(toDbProveedor(proveedor), subject).map(this::toDomain);
    }

    @Override
    public Optional<IdentidadExterna> buscarPorUsuarioIdYProveedor(
            UUID usuarioId,
            ProveedorIdentidadExterna proveedor
    ) {
        return identidadRepo.findByUsuarioIdAndProveedor(
                usuarioId.getLeastSignificantBits(),
                toDbProveedor(proveedor)
        ).map(this::toDomain);
    }

    @Override
    public List<IdentidadExterna> buscarPorUsuarioId(UUID usuarioId) {
        return identidadRepo.findByUsuarioId(usuarioId.getLeastSignificantBits())
                .stream()
                .map(this::toDomain)
                .toList();
    }

    private IdentidadExternaJpaEntity toEntity(IdentidadExterna identidad) {
        IdentidadExternaJpaEntity e = new IdentidadExternaJpaEntity();
        e.setId(identidad.getId());
        e.setUsuarioId(identidad.getUsuarioId() != null ? identidad.getUsuarioId().getLeastSignificantBits() : null);
        e.setProveedor(toDbProveedor(identidad.getProveedor()));
        e.setSubject(identidad.getSubject());
        e.setEmail(identidad.getEmail());
        e.setEmailVerificado(identidad.getEmailVerificado());
        e.setNombreMostrado(identidad.getNombreMostrado());
        e.setFechaVinculacion(identidad.getFechaVinculacion());
        e.setUltimoAcceso(identidad.getUltimoAcceso());
        e.setHabilitada(identidad.getHabilitada());
        return e;
    }

    private IdentidadExterna toDomain(IdentidadExternaJpaEntity e) {
        IdentidadExterna identidad = new IdentidadExterna(
                e.getId(),
                UsuarioJpaAdapter.uuidFromLong(e.getUsuarioId()),
                ProveedorIdentidadExterna.desdeApiValue(e.getProveedor()),
                e.getSubject(),
                e.getEmail(),
                e.getEmailVerificado(),
                e.getNombreMostrado(),
                e.getFechaVinculacion(),
                e.getUltimoAcceso(),
                e.getHabilitada()
        );
        if (e.getCreatedAt() != null) identidad.marcarCreacion(e.getCreatedAt());
        if (e.getUpdatedAt() != null) identidad.marcarModificacion(e.getUpdatedAt());
        return identidad;
    }

    private String toDbProveedor(ProveedorIdentidadExterna proveedor) {
        return proveedor != null ? proveedor.getApiValue() : null;
    }
}

// ── RefreshTokenJpaAdapter ───────────────────────────────────
@Component
public class AutenticacionJpaAdapter implements RefreshTokenRepositoryPort {

    private final RefreshTokenJpaRepository refreshTokenRepo;

    public AutenticacionJpaAdapter(RefreshTokenJpaRepository refreshTokenRepo) {
        this.refreshTokenRepo = refreshTokenRepo;
    }

    @Override
    public RefreshToken guardar(RefreshToken r) {
        RefreshTokenJpaEntity e = toEntity(r);
        if (e.getCreatedAt() == null) e.setCreatedAt(LocalDateTime.now());
        return toDomain(refreshTokenRepo.save(e));
    }

    @Override
    public Optional<RefreshToken> buscarPorId(UUID refreshTokenId) {
        return refreshTokenRepo.findById(refreshTokenId).map(this::toDomain);
    }

    @Override
    public Optional<RefreshToken> buscarPorTokenHash(String tokenHash) {
        return refreshTokenRepo.findByTokenHash(tokenHash).map(this::toDomain);
    }

    @Override
    public List<RefreshToken> buscarActivosPorUsuarioId(UUID usuarioId) {
        return refreshTokenRepo.findByUsuarioIdAndActivoTrue(usuarioId.getLeastSignificantBits())
                .stream().map(this::toDomain).toList();
    }

    private RefreshTokenJpaEntity toEntity(RefreshToken r) {
        RefreshTokenJpaEntity e = new RefreshTokenJpaEntity();
        e.setId(r.getId());
        e.setUsuarioId(r.getUsuarioId() != null ? r.getUsuarioId().getLeastSignificantBits() : null);
        e.setCredencialAccesoId(r.getCredencialAccesoId());
        e.setIdentidadExternaId(r.getIdentidadExternaId());
        e.setTokenHash(r.getTokenHash());
        e.setFechaEmision(r.getFechaEmision());
        e.setFechaExpiracion(r.getFechaExpiracion());
        e.setFechaRevocacion(r.getFechaRevocacion());
        e.setActivo(r.getActivo());
        return e;
    }

    private RefreshToken toDomain(RefreshTokenJpaEntity e) {
        RefreshToken r = new RefreshToken(
                e.getId(), UsuarioJpaAdapter.uuidFromLong(e.getUsuarioId()),
                e.getCredencialAccesoId(), e.getIdentidadExternaId(), e.getTokenHash(),
                e.getFechaEmision(), e.getFechaExpiracion(),
                e.getFechaRevocacion(), e.getActivo());
        if (e.getCreatedAt() != null) r.marcarCreacion(e.getCreatedAt());
        return r;
    }
}
