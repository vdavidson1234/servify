package com.servify.autenticacion.application.dto;

import com.servify.autenticacion.domain.enumtype.ProveedorIdentidadExterna;
import com.servify.usuarios.domain.enumtype.Rol;

public class AutenticarConIdentidadExternaCommand {

    private ProveedorIdentidadExterna proveedor;
    private String idToken;
    private String nonce;
    private Rol rolSolicitado;
    private String telefono;

    public AutenticarConIdentidadExternaCommand() {
    }

    public AutenticarConIdentidadExternaCommand(ProveedorIdentidadExterna proveedor,
                                                String idToken,
                                                String nonce,
                                                Rol rolSolicitado,
                                                String telefono) {
        this.proveedor = proveedor;
        this.idToken = idToken;
        this.nonce = nonce;
        this.rolSolicitado = rolSolicitado;
        this.telefono = telefono;
    }

    public ProveedorIdentidadExterna getProveedor() {
        return proveedor;
    }

    public String getIdToken() {
        return idToken;
    }

    public String getNonce() {
        return nonce;
    }

    public Rol getRolSolicitado() {
        return rolSolicitado;
    }

    public String getTelefono() {
        return telefono;
    }
}
