package com.servify.autenticacion.application.dto;

import com.servify.autenticacion.domain.enumtype.ProveedorIdentidadExterna;
import java.time.Instant;
import java.util.List;

public class IdentidadExternaVerificadaResult {

    private final ProveedorIdentidadExterna proveedor;
    private final String issuer;
    private final List<String> audience;
    private final String subject;
    private final String email;
    private final Boolean emailVerificado;
    private final String nombreMostrado;
    private final String fotoUrl;
    private final Instant expiracion;

    public IdentidadExternaVerificadaResult(ProveedorIdentidadExterna proveedor,
                                            String issuer,
                                            List<String> audience,
                                            String subject,
                                            String email,
                                            Boolean emailVerificado,
                                            String nombreMostrado,
                                            String fotoUrl,
                                            Instant expiracion) {
        this.proveedor = proveedor;
        this.issuer = issuer;
        this.audience = audience == null ? List.of() : List.copyOf(audience);
        this.subject = subject;
        this.email = email;
        this.emailVerificado = emailVerificado;
        this.nombreMostrado = nombreMostrado;
        this.fotoUrl = fotoUrl;
        this.expiracion = expiracion;
    }

    public ProveedorIdentidadExterna getProveedor() {
        return proveedor;
    }

    public String getIssuer() {
        return issuer;
    }

    public List<String> getAudience() {
        return audience;
    }

    public String getSubject() {
        return subject;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getEmailVerificado() {
        return emailVerificado;
    }

    public String getNombreMostrado() {
        return nombreMostrado;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public Instant getExpiracion() {
        return expiracion;
    }
}
