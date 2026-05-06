package com.servify.solicitudes.domain.service;

import com.servify.solicitudes.domain.model.DistribucionSolicitud;

import java.time.LocalDateTime;
import java.util.List;

public class PoliticaExpiracionDistribucion {

    public boolean debeExpirar(DistribucionSolicitud distribucion,
                               LocalDateTime fechaActual) {
        // TODO implementar validación de expiración de una distribución.
        // Debe verificar, como mínimo:
        // - que la distribución exista
        // - que la distribución continúe en un estado que admita expiración
        // - que tenga fecha de expiración definida
        // - que la fecha actual haya superado o alcanzado la fecha de expiración
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean existenDistribucionesExpirables(List<DistribucionSolicitud> distribuciones,
                                                   LocalDateTime fechaActual) {
        // TODO implementar detección de distribuciones expirables.
        // Debe recorrer la lista de distribuciones y detectar si alguna
        // ya cumple las condiciones para ser marcada como expirada.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }

    public boolean puedeSeguirEsperandoRespuesta(DistribucionSolicitud distribucion,
                                                 LocalDateTime fechaActual) {
        // TODO implementar validación de espera activa.
        // Debe devolver true cuando la distribución todavía no haya expirado
        // y continúe disponible para ser respondida por el prestador.
        throw new UnsupportedOperationException("Pendiente de implementación");
    }
}