package com.servify.administracion.domain.model;

import com.servify.shared.domain.model.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ConfiguracionGeneral extends BaseEntity {

    private Integer radioBusquedaInicialKm;
    private Integer radioBusquedaExpansionKm;
    private Integer tiempoEsperaExpansionMinutos;
    private Boolean validacionIdentidadRequerida;
    private BigDecimal precioBaseMinimoReferencia;
    private Boolean plataformaActiva;
    private LocalDateTime fechaUltimaActualizacion;

    protected ConfiguracionGeneral() {
    }

    public ConfiguracionGeneral(UUID id,
                                Integer radioBusquedaInicialKm,
                                Integer radioBusquedaExpansionKm,
                                Integer tiempoEsperaExpansionMinutos,
                                Boolean validacionIdentidadRequerida,
                                BigDecimal precioBaseMinimoReferencia,
                                Boolean plataformaActiva,
                                LocalDateTime fechaUltimaActualizacion) {
        super(id);
        this.radioBusquedaInicialKm = radioBusquedaInicialKm;
        this.radioBusquedaExpansionKm = radioBusquedaExpansionKm;
        this.tiempoEsperaExpansionMinutos = tiempoEsperaExpansionMinutos;
        this.validacionIdentidadRequerida = validacionIdentidadRequerida;
        this.precioBaseMinimoReferencia = precioBaseMinimoReferencia;
        this.plataformaActiva = plataformaActiva;
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }

    public Integer getRadioBusquedaInicialKm() {
        return radioBusquedaInicialKm;
    }

    public Integer getRadioBusquedaExpansionKm() {
        return radioBusquedaExpansionKm;
    }

    public Integer getTiempoEsperaExpansionMinutos() {
        return tiempoEsperaExpansionMinutos;
    }

    public Boolean getValidacionIdentidadRequerida() {
        return validacionIdentidadRequerida;
    }

    public BigDecimal getPrecioBaseMinimoReferencia() {
        return precioBaseMinimoReferencia;
    }

    public Boolean getPlataformaActiva() {
        return plataformaActiva;
    }

    public LocalDateTime getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }

    public boolean requiereValidacionIdentidad() {
        // TODO implementar verificación de requerimiento de validación de identidad.
        // Debe devolver true cuando la plataforma exija identidad validada
        // para permitir determinadas operaciones, como publicar servicios.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean estaActiva() {
        // TODO implementar verificación de plataforma activa.
        // Debe devolver true cuando la plataforma se encuentre habilitada
        // para operar normalmente.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean configuracionBusquedaValida() {
        // TODO implementar validación de parámetros de búsqueda.
        // Debe verificar que los radios y tiempos configurados sean coherentes,
        // positivos y útiles para la lógica de distribución progresiva.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarRadioBusquedaInicialKm(Integer radioBusquedaInicialKm) {
        // TODO implementar actualización del radio inicial de búsqueda.
        // Debe validar que el valor no sea nulo, sea positivo
        // y cumpla las reglas operativas de la plataforma.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarRadioBusquedaExpansionKm(Integer radioBusquedaExpansionKm) {
        // TODO implementar actualización del radio de expansión.
        // Debe validar que el valor no sea nulo, sea positivo
        // y sea coherente con el radio inicial de búsqueda.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarTiempoEsperaExpansionMinutos(Integer tiempoEsperaExpansionMinutos) {
        // TODO implementar actualización del tiempo de espera para expansión.
        // Debe validar que el valor no sea nulo, sea positivo
        // y resulte coherente con el flujo de distribución.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarValidacionIdentidadRequerida(Boolean validacionIdentidadRequerida) {
        // TODO implementar actualización del requerimiento de validación de identidad.
        // Debe registrar el nuevo comportamiento esperado de la plataforma
        // respecto a la exigencia de identidad validada.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void actualizarPrecioBaseMinimoReferencia(BigDecimal precioBaseMinimoReferencia) {
        // TODO implementar actualización del precio mínimo de referencia.
        // Debe validar que el valor no sea nulo, no sea negativo
        // y sea coherente con las reglas de negocio definidas.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void activarPlataforma() {
        // TODO implementar activación general de la plataforma.
        // Debe marcar la plataforma como activa y registrar la actualización.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void desactivarPlataforma() {
        // TODO implementar desactivación general de la plataforma.
        // Debe marcar la plataforma como inactiva y registrar la actualización.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public void registrarActualizacion(LocalDateTime fechaActualizacion) {
        // TODO implementar actualización de la fecha de modificación.
        // Debe registrar la fecha/hora en la que se modificó la configuración general.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}