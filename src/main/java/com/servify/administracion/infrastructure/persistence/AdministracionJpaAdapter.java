package com.servify.administracion.infrastructure.persistence;

import com.servify.administracion.application.port.out.ConfiguracionGeneralRepositoryPort;
import com.servify.administracion.application.port.out.MedidaAdministrativaUsuarioRepositoryPort;
import com.servify.administracion.domain.enumtype.TipoMedida;
import com.servify.administracion.domain.model.ConfiguracionGeneral;
import com.servify.administracion.domain.model.MedidaAdministrativaUsuario;
import com.servify.solicitudes.application.port.out.ConfiguracionDistribucionPort;
import com.servify.usuarios.infrastructure.persistence.UsuarioJpaAdapter;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// ── ConfiguracionGeneralJpaEntity ────────────────────────────
@Entity
@Table(name = "configuracion_general")
class ConfiguracionGeneralJpaEntity {
    @Id @Column(columnDefinition = "uuid") private UUID id;
    @Column(name = "radio_busqueda_inicial_km", nullable = false) private Integer radioBusquedaInicialKm;
    @Column(name = "radio_busqueda_expansion_km", nullable = false) private Integer radioBusquedaExpansionKm;
    @Column(name = "tiempo_espera_expansion_min", nullable = false) private Integer tiempoEsperaExpansionMin;
    @Column(name = "validacion_identidad_requerida", nullable = false) private Boolean validacionIdentidadRequerida;
    @Column(name = "precio_base_minimo_referencia", nullable = false) private BigDecimal precioBaseMinimoReferencia;
    @Column(name = "plataforma_activa", nullable = false) private Boolean plataformaActiva;
    @Column(name = "fecha_ultima_actualizacion", nullable = false) private LocalDateTime fechaUltimaActualizacion;
    @Column(name = "created_at", nullable = false) private LocalDateTime createdAt;

    protected ConfiguracionGeneralJpaEntity() {}
    public UUID getId() { return id; } public void setId(UUID id) { this.id = id; }
    public Integer getRadioBusquedaInicialKm() { return radioBusquedaInicialKm; } public void setRadioBusquedaInicialKm(Integer v) { this.radioBusquedaInicialKm = v; }
    public Integer getRadioBusquedaExpansionKm() { return radioBusquedaExpansionKm; } public void setRadioBusquedaExpansionKm(Integer v) { this.radioBusquedaExpansionKm = v; }
    public Integer getTiempoEsperaExpansionMin() { return tiempoEsperaExpansionMin; } public void setTiempoEsperaExpansionMin(Integer v) { this.tiempoEsperaExpansionMin = v; }
    public Boolean getValidacionIdentidadRequerida() { return validacionIdentidadRequerida; } public void setValidacionIdentidadRequerida(Boolean v) { this.validacionIdentidadRequerida = v; }
    public BigDecimal getPrecioBaseMinimoReferencia() { return precioBaseMinimoReferencia; } public void setPrecioBaseMinimoReferencia(BigDecimal v) { this.precioBaseMinimoReferencia = v; }
    public Boolean getPlataformaActiva() { return plataformaActiva; } public void setPlataformaActiva(Boolean v) { this.plataformaActiva = v; }
    public LocalDateTime getFechaUltimaActualizacion() { return fechaUltimaActualizacion; } public void setFechaUltimaActualizacion(LocalDateTime v) { this.fechaUltimaActualizacion = v; }
    public LocalDateTime getCreatedAt() { return createdAt; } public void setCreatedAt(LocalDateTime v) { this.createdAt = v; }
}

// ── MedidaAdministrativaUsuarioJpaEntity ─────────────────────
@Entity
@Table(name = "medida_administrativa_usuario")
class MedidaAdministrativaUsuarioJpaEntity {
    @Id @Column(columnDefinition = "uuid") private UUID id;
    @Column(name = "usuario_id", nullable = false) private Long usuarioId;
    @Column(name = "administrador_id", nullable = false) private Long administradorId;
    @Column(name = "tipo_medida", nullable = false) private String tipoMedida;
    @Column(name = "motivo", nullable = false) private String motivo;
    @Column(name = "fecha_aplicacion", nullable = false) private LocalDateTime fechaAplicacion;
    @Column(name = "fecha_fin_vigencia") private LocalDateTime fechaFinVigencia;
    @Column(name = "activa", nullable = false) private Boolean activa;
    @Column(name = "created_at", nullable = false) private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false) private LocalDateTime updatedAt;

    protected MedidaAdministrativaUsuarioJpaEntity() {}
    public UUID getId() { return id; } public void setId(UUID id) { this.id = id; }
    public Long getUsuarioId() { return usuarioId; } public void setUsuarioId(Long v) { this.usuarioId = v; }
    public Long getAdministradorId() { return administradorId; } public void setAdministradorId(Long v) { this.administradorId = v; }
    public String getTipoMedida() { return tipoMedida; } public void setTipoMedida(String v) { this.tipoMedida = v; }
    public String getMotivo() { return motivo; } public void setMotivo(String v) { this.motivo = v; }
    public LocalDateTime getFechaAplicacion() { return fechaAplicacion; } public void setFechaAplicacion(LocalDateTime v) { this.fechaAplicacion = v; }
    public LocalDateTime getFechaFinVigencia() { return fechaFinVigencia; } public void setFechaFinVigencia(LocalDateTime v) { this.fechaFinVigencia = v; }
    public Boolean getActiva() { return activa; } public void setActiva(Boolean v) { this.activa = v; }
    public LocalDateTime getCreatedAt() { return createdAt; } public void setCreatedAt(LocalDateTime v) { this.createdAt = v; }
    public LocalDateTime getUpdatedAt() { return updatedAt; } public void setUpdatedAt(LocalDateTime v) { this.updatedAt = v; }
}

// ── Repositories ─────────────────────────────────────────────
interface ConfiguracionGeneralJpaRepository extends JpaRepository<ConfiguracionGeneralJpaEntity, UUID> {
    Optional<ConfiguracionGeneralJpaEntity> findTopByOrderByFechaUltimaActualizacionDesc();
}

interface MedidaAdministrativaUsuarioJpaRepository extends JpaRepository<MedidaAdministrativaUsuarioJpaEntity, UUID> {
    List<MedidaAdministrativaUsuarioJpaEntity> findByUsuarioId(Long usuarioId);
    List<MedidaAdministrativaUsuarioJpaEntity> findByUsuarioIdAndActivaTrue(Long usuarioId);
}

// ── Adapter ──────────────────────────────────────────────────
@Component
public class AdministracionJpaAdapter implements ConfiguracionGeneralRepositoryPort,
        ConfiguracionDistribucionPort, MedidaAdministrativaUsuarioRepositoryPort {

    private final ConfiguracionGeneralJpaRepository configRepo;
    private final MedidaAdministrativaUsuarioJpaRepository medidaRepo;

    public AdministracionJpaAdapter(ConfiguracionGeneralJpaRepository configRepo,
                                     MedidaAdministrativaUsuarioJpaRepository medidaRepo) {
        this.configRepo = configRepo;
        this.medidaRepo = medidaRepo;
    }

    // ── ConfiguracionGeneralRepositoryPort ───────────────────

    @Override
    public ConfiguracionGeneral guardar(ConfiguracionGeneral c) {
        ConfiguracionGeneralJpaEntity e = toConfigEntity(c);
        if (e.getCreatedAt() == null) e.setCreatedAt(LocalDateTime.now());
        return toConfigDomain(configRepo.save(e));
    }

    @Override
    public Optional<ConfiguracionGeneral> buscarPorId(UUID configId) {
        return configRepo.findById(configId).map(this::toConfigDomain);
    }

    @Override
    public Optional<ConfiguracionGeneral> obtenerVigente() {
        return configRepo.findTopByOrderByFechaUltimaActualizacionDesc().map(this::toConfigDomain);
    }

    // ── ConfiguracionDistribucionPort ────────────────────────

    @Override
    public Integer obtenerRadioBusquedaInicialKm() {
        return obtenerVigente().map(ConfiguracionGeneral::getRadioBusquedaInicialKm).orElse(10);
    }

    @Override
    public Integer obtenerRadioBusquedaExpansionKm() {
        return obtenerVigente().map(ConfiguracionGeneral::getRadioBusquedaExpansionKm).orElse(25);
    }

    @Override
    public Integer obtenerTiempoEsperaExpansionMinutos() {
        return obtenerVigente().map(ConfiguracionGeneral::getTiempoEsperaExpansionMinutos).orElse(30);
    }

    @Override
    public BigDecimal obtenerPrecioBaseMinimoReferencia() {
        return obtenerVigente().map(ConfiguracionGeneral::getPrecioBaseMinimoReferencia).orElse(BigDecimal.ZERO);
    }

    // ── MedidaAdministrativaUsuarioRepositoryPort ────────────

    @Override
    public MedidaAdministrativaUsuario guardar(MedidaAdministrativaUsuario m) {
        MedidaAdministrativaUsuarioJpaEntity e = toMedidaEntity(m);
        if (e.getCreatedAt() == null) e.setCreatedAt(LocalDateTime.now());
        e.setUpdatedAt(LocalDateTime.now());
        return toMedidaDomain(medidaRepo.save(e));
    }

    @Override
    public Optional<MedidaAdministrativaUsuario> buscarPorId(UUID medidaId) {
        return medidaRepo.findById(medidaId).map(this::toMedidaDomain);
    }

    @Override
    public List<MedidaAdministrativaUsuario> buscarPorUsuarioId(UUID usuarioId) {
        return medidaRepo.findByUsuarioId(usuarioId.getLeastSignificantBits())
                .stream().map(this::toMedidaDomain).toList();
    }

    @Override
    public List<MedidaAdministrativaUsuario> buscarActivasPorUsuarioId(UUID usuarioId) {
        return medidaRepo.findByUsuarioIdAndActivaTrue(usuarioId.getLeastSignificantBits())
                .stream().map(this::toMedidaDomain).toList();
    }

    // ── Mapeos ───────────────────────────────────────────────

    private ConfiguracionGeneralJpaEntity toConfigEntity(ConfiguracionGeneral c) {
        ConfiguracionGeneralJpaEntity e = new ConfiguracionGeneralJpaEntity();
        e.setId(c.getId());
        e.setRadioBusquedaInicialKm(c.getRadioBusquedaInicialKm());
        e.setRadioBusquedaExpansionKm(c.getRadioBusquedaExpansionKm());
        e.setTiempoEsperaExpansionMin(c.getTiempoEsperaExpansionMinutos());
        e.setValidacionIdentidadRequerida(c.getValidacionIdentidadRequerida());
        e.setPrecioBaseMinimoReferencia(c.getPrecioBaseMinimoReferencia());
        e.setPlataformaActiva(c.getPlataformaActiva());
        e.setFechaUltimaActualizacion(c.getFechaUltimaActualizacion() != null
                ? c.getFechaUltimaActualizacion() : LocalDateTime.now());
        return e;
    }

    private ConfiguracionGeneral toConfigDomain(ConfiguracionGeneralJpaEntity e) {
        ConfiguracionGeneral c = new ConfiguracionGeneral(
                e.getId(), e.getRadioBusquedaInicialKm(), e.getRadioBusquedaExpansionKm(),
                e.getTiempoEsperaExpansionMin(), e.getValidacionIdentidadRequerida(),
                e.getPrecioBaseMinimoReferencia(), e.getPlataformaActiva(),
                e.getFechaUltimaActualizacion());
        if (e.getCreatedAt() != null) c.marcarCreacion(e.getCreatedAt());
        return c;
    }

    private MedidaAdministrativaUsuarioJpaEntity toMedidaEntity(MedidaAdministrativaUsuario m) {
        MedidaAdministrativaUsuarioJpaEntity e = new MedidaAdministrativaUsuarioJpaEntity();
        e.setId(m.getId());
        e.setUsuarioId(m.getUsuarioId() != null ? m.getUsuarioId().getLeastSignificantBits() : null);
        e.setAdministradorId(m.getAdministradorId() != null ? m.getAdministradorId().getLeastSignificantBits() : null);
        e.setTipoMedida(m.getTipoMedida() != null ? m.getTipoMedida().name().toLowerCase() : null);
        e.setMotivo(m.getMotivo());
        e.setFechaAplicacion(m.getFechaAplicacion());
        e.setFechaFinVigencia(m.getFechaFinVigencia());
        e.setActiva(m.getActiva());
        return e;
    }

    private MedidaAdministrativaUsuario toMedidaDomain(MedidaAdministrativaUsuarioJpaEntity e) {
        MedidaAdministrativaUsuario m = new MedidaAdministrativaUsuario(
                e.getId(),
                UsuarioJpaAdapter.uuidFromLong(e.getUsuarioId()),
                UsuarioJpaAdapter.uuidFromLong(e.getAdministradorId()),
                TipoMedida.valueOf(e.getTipoMedida().toUpperCase()),
                e.getMotivo(), e.getFechaAplicacion(), e.getFechaFinVigencia(), e.getActiva());
        if (e.getCreatedAt() != null) m.marcarCreacion(e.getCreatedAt());
        if (e.getUpdatedAt() != null) m.marcarModificacion(e.getUpdatedAt());
        return m;
    }
}
