package com.servify.shared.domain.valueobject;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

public class DisponibilidadHoraria {

    private DayOfWeek diaSemana;
    private LocalTime horaDesde;
    private LocalTime horaHasta;

    protected DisponibilidadHoraria() {
    }

    public DisponibilidadHoraria(DayOfWeek diaSemana,
                                 LocalTime horaDesde,
                                 LocalTime horaHasta) {
        this.diaSemana = diaSemana;
        this.horaDesde = horaDesde;
        this.horaHasta = horaHasta;
    }

    public DayOfWeek getDiaSemana() {
        return diaSemana;
    }

    public LocalTime getHoraDesde() {
        return horaDesde;
    }

    public LocalTime getHoraHasta() {
        return horaHasta;
    }

    public boolean esRangoHorarioValido() {
        // TODO implementar validación del rango horario.
        // Debe verificar, como mínimo:
        // - que el día de la semana no sea nulo
        // - que horaDesde y horaHasta no sean nulas
        // - que horaDesde sea anterior a horaHasta
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean incluyeHora(LocalTime hora) {
        // TODO implementar verificación de inclusión de una hora dentro del rango.
        // Debe devolver true si la hora recibida cae dentro de la franja horaria
        // representada por este value object.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean seSuperponeCon(DisponibilidadHoraria otraDisponibilidad) {
        // TODO implementar validación de solapamiento entre disponibilidades.
        // Debe verificar si ambas disponibilidades corresponden al mismo día
        // y si sus rangos horarios se pisan parcial o totalmente.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DisponibilidadHoraria that)) {
            return false;
        }
        return diaSemana == that.diaSemana
                && Objects.equals(horaDesde, that.horaDesde)
                && Objects.equals(horaHasta, that.horaHasta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diaSemana, horaDesde, horaHasta);
    }
}