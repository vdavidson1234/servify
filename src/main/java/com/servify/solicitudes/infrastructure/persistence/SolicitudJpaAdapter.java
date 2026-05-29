package com.servify.solicitudes.infrastructure.persistence;

import com.servify.shared.domain.enumtype.ModalidadServicio;
import com.servify.shared.domain.valueobject.DisponibilidadHoraria;
import com.servify.shared.domain.valueobject.Ubicacion;
import com.servify.solicitudes.application.port.out.*;
import com.servify.solicitudes.domain.enumtype.*;
import com.servify.solicitudes.domain.model.*;
import com.servify.usuarios.infrastructure.persistence.UsuarioJpaAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class SolicitudJpaAdapter implements
        SolicitudServicioRepositoryPort,
        DistribucionSolicitudRepositoryPort,
        AsignacionServicioRepositoryPort,
        CalificacionRepositoryPort,
        ContraofertaRepositoryPort,
        ConfirmacionFinalizacionRepositoryPort {

    private final SolicitudServicioJpaRepository solicitudRepo;
    private final DistribucionSolicitudJpaRepository distribucionRepo;
    private final AsignacionServicioJpaRepository asignacionRepo;
    private final CalificacionJpaRepository calificacionRepo;
    private final ContraofertaJpaRepository contraofertaRepo;
    private final ConfirmacionFinalizacionJpaRepository confirmacionRepo;

    public SolicitudJpaAdapter(SolicitudServicioJpaRepository solicitudRepo,
                                DistribucionSolicitudJpaRepository distribucionRepo,
                                AsignacionServicioJpaRepository asignacionRepo,
                                CalificacionJpaRepository calificacionRepo,
                                ContraofertaJpaRepository contraofertaRepo,
                                ConfirmacionFinalizacionJpaRepository confirmacionRepo) {
        this.solicitudRepo = solicitudRepo;
        this.distribucionRepo = distribucionRepo;
        this.asignacionRepo = asignacionRepo;
        this.calificacionRepo = calificacionRepo;
        this.contraofertaRepo = contraofertaRepo;
        this.confirmacionRepo = confirmacionRepo;
    }

    // ── SolicitudServicioRepositoryPort ──────────────────────

    @Override
    public SolicitudServicio guardar(SolicitudServicio s) {
        SolicitudServicioJpaEntity e = toSolicitudEntity(s);
        if (e.getCreatedAt() == null) e.setCreatedAt(LocalDateTime.now());
        e.setUpdatedAt(LocalDateTime.now());
        return toSolicitudDomain(solicitudRepo.save(e));
    }

    @Override
    public Optional<SolicitudServicio> buscarPorId(UUID solicitudId) {
        return solicitudRepo.findAll().stream()
                .filter(e -> UsuarioJpaAdapter.uuidFromLong(e.getId()).equals(solicitudId))
                .findFirst().map(this::toSolicitudDomain);
    }

    @Override
    public List<SolicitudServicio> buscarPorSolicitanteId(UUID solicitanteId) {
        return solicitudRepo.findAll().stream()
                .filter(e -> UsuarioJpaAdapter.uuidFromLong(e.getSolicitanteId()).equals(solicitanteId))
                .map(this::toSolicitudDomain).toList();
    }

    // ── DistribucionSolicitudRepositoryPort ──────────────────

    @Override
    public DistribucionSolicitud guardar(DistribucionSolicitud d) {
        DistribucionSolicitudJpaEntity e = toDistribucionEntity(d);
        if (e.getCreatedAt() == null) e.setCreatedAt(LocalDateTime.now());
        return toDistribucionDomain(distribucionRepo.save(e));
    }

    @Override
    public Optional<DistribucionSolicitud> buscarPorId(UUID distribucionId) {
        return distribucionRepo.findAll().stream()
                .filter(e -> UsuarioJpaAdapter.uuidFromLong(e.getId()).equals(distribucionId))
                .findFirst().map(this::toDistribucionDomain);
    }

    @Override
    public List<DistribucionSolicitud> buscarPorSolicitudId(UUID solicitudId) {
        return distribucionRepo.findAll().stream()
                .filter(e -> UsuarioJpaAdapter.uuidFromLong(e.getSolicitudId()).equals(solicitudId))
                .map(this::toDistribucionDomain).toList();
    }

    @Override
    public List<DistribucionSolicitud> buscarPorPrestadorId(UUID prestadorId) {
        return distribucionRepo.findAll().stream()
                .filter(e -> UsuarioJpaAdapter.uuidFromLong(e.getPrestadorId()).equals(prestadorId))
                .map(this::toDistribucionDomain).toList();
    }

    @Override
    public List<DistribucionSolicitud> buscarActivasPorSolicitudId(UUID solicitudId) {
        return buscarPorSolicitudId(solicitudId).stream()
                .filter(d -> d.estaEnviada() || d.estaAceptada() || d.estaContraofertada())
                .toList();
    }

    // ── AsignacionServicioRepositoryPort ─────────────────────

    @Override
    public AsignacionServicio guardar(AsignacionServicio a) {
        AsignacionServicioJpaEntity e = toAsignacionEntity(a);
        if (e.getCreatedAt() == null) e.setCreatedAt(LocalDateTime.now());
        e.setUpdatedAt(LocalDateTime.now());
        return toAsignacionDomain(asignacionRepo.save(e));
    }

    @Override
    public Optional<AsignacionServicio> buscarPorId(UUID asignacionId) {
        return asignacionRepo.findAll().stream()
                .filter(e -> UsuarioJpaAdapter.uuidFromLong(e.getId()).equals(asignacionId))
                .findFirst().map(this::toAsignacionDomain);
    }

    @Override
    public Optional<AsignacionServicio> buscarPorSolicitudId(UUID solicitudId) {
        return asignacionRepo.findAll().stream()
                .filter(e -> UsuarioJpaAdapter.uuidFromLong(e.getSolicitudId()).equals(solicitudId))
                .findFirst().map(this::toAsignacionDomain);
    }

    @Override
    public List<AsignacionServicio> buscarPorPrestadorId(UUID prestadorId) {
        return asignacionRepo.findAll().stream()
                .filter(e -> UsuarioJpaAdapter.uuidFromLong(e.getPrestadorId()).equals(prestadorId))
                .map(this::toAsignacionDomain).toList();
    }

    @Override
    public List<AsignacionServicio> buscarPorSolicitanteId(UUID solicitanteId) {
        return asignacionRepo.findAll().stream()
                .filter(e -> {
                    Optional<SolicitudServicioJpaEntity> s = solicitudRepo.findAll().stream()
                            .filter(se -> se.getId().equals(e.getSolicitudId()))
                            .findFirst();
                    return s.map(se -> UsuarioJpaAdapter.uuidFromLong(se.getSolicitanteId()).equals(solicitanteId))
                            .orElse(false);
                })
                .map(this::toAsignacionDomain).toList();
    }

    // ── CalificacionRepositoryPort ───────────────────────────

    @Override
    public Calificacion guardar(Calificacion c) {
        CalificacionJpaEntity e = toCalificacionEntity(c);
        if (e.getCreatedAt() == null) e.setCreatedAt(LocalDateTime.now());
        return toCalificacionDomain(calificacionRepo.save(e));
    }

    @Override
    public Optional<Calificacion> buscarPorId(UUID calificacionId) {
        return calificacionRepo.findAll().stream()
                .filter(e -> UsuarioJpaAdapter.uuidFromLong(e.getId()).equals(calificacionId))
                .findFirst().map(this::toCalificacionDomain);
    }

    @Override
    public Optional<Calificacion> buscarPorSolicitudId(UUID solicitudId) {
        // Calificacion no tiene solicitudId directo; lo resolvemos via asignacion
        return asignacionRepo.findAll().stream()
                .filter(a -> UsuarioJpaAdapter.uuidFromLong(a.getSolicitudId()).equals(solicitudId))
                .findFirst()
                .flatMap(a -> calificacionRepo.findByAsignacionId(a.getId()))
                .map(this::toCalificacionDomain);
    }

    @Override
    public List<Calificacion> buscarPorPrestadorId(UUID prestadorId) {
        return calificacionRepo.findAll().stream()
                .filter(e -> UsuarioJpaAdapter.uuidFromLong(e.getPrestadorId()).equals(prestadorId))
                .map(this::toCalificacionDomain).toList();
    }

    @Override
    public List<Calificacion> buscarPorSolicitanteId(UUID solicitanteId) {
        return calificacionRepo.findAll().stream()
                .filter(e -> UsuarioJpaAdapter.uuidFromLong(e.getSolicitanteId()).equals(solicitanteId))
                .map(this::toCalificacionDomain).toList();
    }

    // ── ContraofertaRepositoryPort ───────────────────────────

    @Override
    public Contraoferta guardar(Contraoferta c) {
        ContraofertaJpaEntity e = toContraofertaEntity(c);
        if (e.getCreatedAt() == null) e.setCreatedAt(LocalDateTime.now());
        e.setUpdatedAt(LocalDateTime.now());
        return toContraofertaDomain(contraofertaRepo.save(e));
    }

    @Override
    public Optional<Contraoferta> buscarPorId(UUID contraofertaId) {
        return contraofertaRepo.findById(contraofertaId).map(this::toContraofertaDomain);
    }

    @Override
    public List<Contraoferta> buscarPorDistribucionSolicitudId(UUID distribucionId) {
        return distribucionRepo.findAll().stream()
                .filter(e -> UsuarioJpaAdapter.uuidFromLong(e.getId()).equals(distribucionId))
                .findFirst()
                .map(e -> contraofertaRepo.findByDistribucionSolicitudId(e.getId())
                        .stream().map(this::toContraofertaDomain).toList())
                .orElse(List.of());
    }

    @Override
    public Optional<Contraoferta> buscarPendientePorDistribucionSolicitudId(UUID distribucionId) {
        return distribucionRepo.findAll().stream()
                .filter(e -> UsuarioJpaAdapter.uuidFromLong(e.getId()).equals(distribucionId))
                .findFirst()
                .flatMap(e -> contraofertaRepo.findByDistribucionSolicitudIdAndEstado(e.getId(), "pendiente"))
                .map(this::toContraofertaDomain);
    }

    @Override
    public List<Contraoferta> buscarPorPrestadorId(UUID prestadorId) {
        return contraofertaRepo.findAll().stream()
                .filter(e -> UsuarioJpaAdapter.uuidFromLong(e.getPrestadorId()).equals(prestadorId))
                .map(this::toContraofertaDomain).toList();
    }

    // ── ConfirmacionFinalizacionRepositoryPort ───────────────

    @Override
    public ConfirmacionFinalizacion guardar(ConfirmacionFinalizacion c) {
        ConfirmacionFinalizacionJpaEntity e = toConfirmacionEntity(c);
        if (e.getCreatedAt() == null) e.setCreatedAt(LocalDateTime.now());
        e.setUpdatedAt(LocalDateTime.now());
        return toConfirmacionDomain(confirmacionRepo.save(e));
    }

    @Override
    public Optional<ConfirmacionFinalizacion> buscarPorId(UUID confirmacionId) {
        return confirmacionRepo.findById(confirmacionId).map(this::toConfirmacionDomain);
    }

    @Override
    public List<ConfirmacionFinalizacion> buscarPorSolicitudId(UUID solicitudId) {
        return solicitudRepo.findAll().stream()
                .filter(e -> UsuarioJpaAdapter.uuidFromLong(e.getId()).equals(solicitudId))
                .findFirst()
                .map(e -> confirmacionRepo.findBySolicitudId(e.getId())
                        .stream().map(this::toConfirmacionDomain).toList())
                .orElse(List.of());
    }

    @Override
    public List<ConfirmacionFinalizacion> buscarPorAsignacionServicioId(UUID asignacionId) {
        return asignacionRepo.findAll().stream()
                .filter(e -> UsuarioJpaAdapter.uuidFromLong(e.getId()).equals(asignacionId))
                .findFirst()
                .map(e -> confirmacionRepo.findByAsignacionServicioId(e.getId())
                        .stream().map(this::toConfirmacionDomain).toList())
                .orElse(List.of());
    }

    @Override
    public Optional<ConfirmacionFinalizacion> buscarPorAsignacionServicioIdYRolConfirmante(
            UUID asignacionId, RolConfirmante rolConfirmante) {
        return asignacionRepo.findAll().stream()
                .filter(e -> UsuarioJpaAdapter.uuidFromLong(e.getId()).equals(asignacionId))
                .findFirst()
                .flatMap(e -> confirmacionRepo.findByAsignacionServicioIdAndRolConfirmante(
                        e.getId(), rolConfirmante.name().toLowerCase()))
                .map(this::toConfirmacionDomain);
    }

    @Override
    public List<ConfirmacionFinalizacion> buscarPorConfirmanteId(UUID confirmanteId) {
        return confirmacionRepo.findAll().stream()
                .filter(e -> UsuarioJpaAdapter.uuidFromLong(e.getConfirmanteId()).equals(confirmanteId))
                .map(this::toConfirmacionDomain).toList();
    }

    // ── Mapeos ───────────────────────────────────────────────

    private SolicitudServicioJpaEntity toSolicitudEntity(SolicitudServicio s) {
        SolicitudServicioJpaEntity e = new SolicitudServicioJpaEntity();
        if (s.getId() != null) {
            solicitudRepo.findAll().stream()
                    .filter(ex -> UsuarioJpaAdapter.uuidFromLong(ex.getId()).equals(s.getId()))
                    .findFirst().ifPresent(ex -> e.setId(ex.getId()));
        }
        e.setSolicitanteId(longFromUuid(s.getSolicitanteId()));
        e.setCategoriaId(longFromUuid(s.getCategoriaServicioId()));
        e.setDescripcion(s.getDescripcionNecesidad());
        e.setModalidad(modalidadToDb(s.getModalidadServicio()));
        if (s.getUbicacion() != null) {
            e.setPais(s.getUbicacion().getPais()); e.setProvincia(s.getUbicacion().getProvincia());
            e.setCiudad(s.getUbicacion().getCiudad()); e.setLocalidad(s.getUbicacion().getLocalidad());
            e.setCalle(s.getUbicacion().getCalle()); e.setAltura(s.getUbicacion().getAltura());
            e.setReferencia(s.getUbicacion().getReferencia());
            e.setLatitud(s.getUbicacion().getLatitud()); e.setLongitud(s.getUbicacion().getLongitud());
        }
        if (s.getDisponibilidadRequerida() != null) {
            DisponibilidadHoraria d = s.getDisponibilidadRequerida();
            e.setDisponibilidadDia(d.getDiaSemana() != null ? d.getDiaSemana().name().toLowerCase() : null);
            e.setDisponibilidadHoraDesde(d.getHoraDesde());
            e.setDisponibilidadHoraHasta(d.getHoraHasta());
        }
        e.setPrecioReferencia(s.getPrecioReferencia());
        e.setEstado(s.getEstado() != null ? s.getEstado().name().toLowerCase() : null);
        e.setFechaSolicitud(s.getFechaSolicitud());
        return e;
    }

    private SolicitudServicio toSolicitudDomain(SolicitudServicioJpaEntity e) {
        Ubicacion ub = new Ubicacion(e.getPais(), e.getProvincia(), e.getCiudad(), e.getLocalidad(),
                e.getCalle(), e.getAltura(), e.getReferencia(), e.getLatitud(), e.getLongitud());
        DisponibilidadHoraria disp = e.getDisponibilidadDia() != null
                ? new DisponibilidadHoraria(DayOfWeek.valueOf(e.getDisponibilidadDia().toUpperCase()),
                e.getDisponibilidadHoraDesde(), e.getDisponibilidadHoraHasta()) : null;
        SolicitudServicio s = new SolicitudServicio(
                UsuarioJpaAdapter.uuidFromLong(e.getId()),
                UsuarioJpaAdapter.uuidFromLong(e.getSolicitanteId()),
                UsuarioJpaAdapter.uuidFromLong(e.getCategoriaId()),
                modalidadFromDb(e.getModalidad()), ub, disp,
                e.getDescripcion(), e.getPrecioReferencia(),
                EstadoSolicitud.valueOf(e.getEstado().toUpperCase()),
                e.getFechaSolicitud());
        if (e.getCreatedAt() != null) s.marcarCreacion(e.getCreatedAt());
        if (e.getUpdatedAt() != null) s.marcarModificacion(e.getUpdatedAt());
        return s;
    }

    private DistribucionSolicitudJpaEntity toDistribucionEntity(DistribucionSolicitud d) {
        DistribucionSolicitudJpaEntity e = new DistribucionSolicitudJpaEntity();
        if (d.getId() != null) {
            distribucionRepo.findAll().stream()
                    .filter(ex -> UsuarioJpaAdapter.uuidFromLong(ex.getId()).equals(d.getId()))
                    .findFirst().ifPresent(ex -> e.setId(ex.getId()));
        }
        e.setSolicitudId(longFromUuid(d.getSolicitudId()));
        e.setPublicacionId(longFromUuid(d.getPublicacionServicioId()));
        e.setPrestadorId(longFromUuid(d.getPrestadorId()));
        e.setEstado(d.getEstado() != null ? d.getEstado().name().toLowerCase() : null);
        e.setRondaDistribucion(d.getRondaDistribucion());
        e.setFechaEnvio(d.getFechaEnvio());
        e.setFechaRespuesta(d.getFechaRespuesta());
        e.setFechaExpiracion(d.getFechaExpiracion());
        return e;
    }

    private DistribucionSolicitud toDistribucionDomain(DistribucionSolicitudJpaEntity e) {
        DistribucionSolicitud d = new DistribucionSolicitud(
                UsuarioJpaAdapter.uuidFromLong(e.getId()),
                UsuarioJpaAdapter.uuidFromLong(e.getSolicitudId()),
                UsuarioJpaAdapter.uuidFromLong(e.getPublicacionId()),
                UsuarioJpaAdapter.uuidFromLong(e.getPrestadorId()),
                EstadoDistribucion.valueOf(e.getEstado().toUpperCase()),
                e.getRondaDistribucion(), e.getFechaEnvio(), e.getFechaRespuesta(), e.getFechaExpiracion());
        if (e.getCreatedAt() != null) d.marcarCreacion(e.getCreatedAt());
        return d;
    }

    private AsignacionServicioJpaEntity toAsignacionEntity(AsignacionServicio a) {
        AsignacionServicioJpaEntity e = new AsignacionServicioJpaEntity();
        if (a.getId() != null) {
            asignacionRepo.findAll().stream()
                    .filter(ex -> UsuarioJpaAdapter.uuidFromLong(ex.getId()).equals(a.getId()))
                    .findFirst().ifPresent(ex -> e.setId(ex.getId()));
        }
        e.setSolicitudId(longFromUuid(a.getSolicitudId()));
        e.setDistribucionId(longFromUuid(a.getDistribucionSolicitudId()));
        e.setPrestadorId(longFromUuid(a.getPrestadorId()));
        e.setPublicacionId(longFromUuid(a.getPublicacionServicioId()));
        e.setPrecioAcordado(a.getPrecioAcordado());
        e.setEstado(a.getEstado() != null ? a.getEstado().name().toLowerCase() : null);
        e.setFechaAsignacion(a.getFechaAsignacion());
        e.setFechaFinalizacion(a.getFechaFinalizacion());
        return e;
    }

    private AsignacionServicio toAsignacionDomain(AsignacionServicioJpaEntity e) {
        AsignacionServicio a = new AsignacionServicio(
                UsuarioJpaAdapter.uuidFromLong(e.getId()),
                UsuarioJpaAdapter.uuidFromLong(e.getSolicitudId()),
                UsuarioJpaAdapter.uuidFromLong(e.getDistribucionId()),
                UsuarioJpaAdapter.uuidFromLong(e.getPrestadorId()),
                UsuarioJpaAdapter.uuidFromLong(e.getPublicacionId()),
                e.getPrecioAcordado(),
                EstadoAsignacion.valueOf(e.getEstado().toUpperCase()),
                e.getFechaAsignacion(), e.getFechaFinalizacion());
        if (e.getCreatedAt() != null) a.marcarCreacion(e.getCreatedAt());
        if (e.getUpdatedAt() != null) a.marcarModificacion(e.getUpdatedAt());
        return a;
    }

    private CalificacionJpaEntity toCalificacionEntity(Calificacion c) {
        CalificacionJpaEntity e = new CalificacionJpaEntity();
        if (c.getId() != null) {
            calificacionRepo.findAll().stream()
                    .filter(ex -> UsuarioJpaAdapter.uuidFromLong(ex.getId()).equals(c.getId()))
                    .findFirst().ifPresent(ex -> e.setId(ex.getId()));
        }
        e.setAsignacionId(longFromUuid(c.getAsignacionServicioId()));
        e.setSolicitanteId(longFromUuid(c.getSolicitanteId()));
        e.setPrestadorId(longFromUuid(c.getPrestadorId()));
        e.setPuntaje(c.getPuntaje());
        return e;
    }

    private Calificacion toCalificacionDomain(CalificacionJpaEntity e) {
        // Resolvemos solicitudId desde asignacion
        UUID solicitudId = asignacionRepo.findById(e.getAsignacionId())
                .map(a -> UsuarioJpaAdapter.uuidFromLong(a.getSolicitudId())).orElse(null);
        Calificacion c = new Calificacion(
                UsuarioJpaAdapter.uuidFromLong(e.getId()),
                solicitudId,
                UsuarioJpaAdapter.uuidFromLong(e.getAsignacionId()),
                UsuarioJpaAdapter.uuidFromLong(e.getSolicitanteId()),
                UsuarioJpaAdapter.uuidFromLong(e.getPrestadorId()),
                e.getPuntaje(),
                e.getCreatedAt());
        if (e.getCreatedAt() != null) c.marcarCreacion(e.getCreatedAt());
        return c;
    }

    private ContraofertaJpaEntity toContraofertaEntity(Contraoferta c) {
        ContraofertaJpaEntity e = new ContraofertaJpaEntity();
        e.setId(c.getId());
        e.setDistribucionSolicitudId(longFromUuid(c.getDistribucionSolicitudId()));
        e.setPrestadorId(longFromUuid(c.getPrestadorId()));
        e.setPrecioOriginal(c.getPrecioOriginal());
        e.setPrecioPropuesto(c.getPrecioPropuesto());
        e.setMensaje(c.getMensaje());
        e.setEstado(c.getEstado() != null ? c.getEstado().name().toLowerCase() : null);
        e.setFechaEmision(c.getFechaEmision());
        e.setFechaResolucion(c.getFechaResolucion());
        return e;
    }

    private Contraoferta toContraofertaDomain(ContraofertaJpaEntity e) {
        Contraoferta c = new Contraoferta(
                e.getId(),
                UsuarioJpaAdapter.uuidFromLong(e.getDistribucionSolicitudId()),
                UsuarioJpaAdapter.uuidFromLong(e.getPrestadorId()),
                e.getPrecioOriginal(), e.getPrecioPropuesto(), e.getMensaje(),
                EstadoContraoferta.valueOf(e.getEstado().toUpperCase()),
                e.getFechaEmision(), e.getFechaResolucion());
        if (e.getCreatedAt() != null) c.marcarCreacion(e.getCreatedAt());
        if (e.getUpdatedAt() != null) c.marcarModificacion(e.getUpdatedAt());
        return c;
    }

    private ConfirmacionFinalizacionJpaEntity toConfirmacionEntity(ConfirmacionFinalizacion c) {
        ConfirmacionFinalizacionJpaEntity e = new ConfirmacionFinalizacionJpaEntity();
        e.setId(c.getId());
        e.setSolicitudId(longFromUuid(c.getSolicitudId()));
        e.setAsignacionServicioId(longFromUuid(c.getAsignacionServicioId()));
        e.setConfirmanteId(longFromUuid(c.getConfirmanteId()));
        e.setRolConfirmante(c.getRolConfirmante() != null ? c.getRolConfirmante().name().toLowerCase() : null);
        e.setConfirmada(c.getConfirmada());
        e.setFechaConfirmacion(c.getFechaConfirmacion());
        e.setObservacion(c.getObservacion());
        return e;
    }

    private ConfirmacionFinalizacion toConfirmacionDomain(ConfirmacionFinalizacionJpaEntity e) {
        ConfirmacionFinalizacion c = new ConfirmacionFinalizacion(
                e.getId(),
                UsuarioJpaAdapter.uuidFromLong(e.getSolicitudId()),
                UsuarioJpaAdapter.uuidFromLong(e.getAsignacionServicioId()),
                UsuarioJpaAdapter.uuidFromLong(e.getConfirmanteId()),
                RolConfirmante.valueOf(e.getRolConfirmante().toUpperCase()),
                e.getConfirmada(), e.getFechaConfirmacion(), e.getObservacion());
        if (e.getCreatedAt() != null) c.marcarCreacion(e.getCreatedAt());
        if (e.getUpdatedAt() != null) c.marcarModificacion(e.getUpdatedAt());
        return c;
    }

    private String modalidadToDb(ModalidadServicio m) {
        if (m == null) return null;
        return switch (m) {
            case PRESENCIAL -> "presencial";
            case VIRTUAL -> "virtual";
            case MIXTA -> "mixta";
        };
    }

    private ModalidadServicio modalidadFromDb(String s) {
        if (s == null) return null;
        return switch (s.toLowerCase()) {
            case "presencial" -> ModalidadServicio.PRESENCIAL;
            case "virtual" -> ModalidadServicio.VIRTUAL;
            case "mixta", "ambas" -> ModalidadServicio.MIXTA;
            default -> throw new IllegalArgumentException("Modalidad desconocida: " + s);
        };
    }

    private Long longFromUuid(UUID uuid) {
        if (uuid == null) return null;
        return uuid.getLeastSignificantBits();
    }
}
