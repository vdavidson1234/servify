package com.servify.autenticacion.infrastructure.oauth;

import com.servify.autenticacion.application.dto.AutenticarConIdentidadExternaCommand;
import com.servify.autenticacion.application.dto.IdentidadExternaVerificadaResult;
import com.servify.autenticacion.application.port.out.ProveedorIdentidadVerifierPort;
import com.servify.autenticacion.domain.enumtype.ProveedorIdentidadExterna;
import java.time.Instant;
import java.util.List;

class FakeProveedorIdentidadVerifier implements ProveedorIdentidadVerifierPort {

    @Override
    public IdentidadExternaVerificadaResult verificar(AutenticarConIdentidadExternaCommand command) {
        if (command == null || command.getIdToken() == null || command.getIdToken().isBlank()) {
            throw new IllegalArgumentException("idToken es obligatorio");
        }
        String[] partes = command.getIdToken().split(":", 4);
        if (partes.length != 4 || !"fake".equalsIgnoreCase(partes[0])) {
            throw new IllegalArgumentException("Token fake invalido para pruebas");
        }
        ProveedorIdentidadExterna proveedor = ProveedorIdentidadExterna.desdeApiValue(partes[1]);
        if (proveedor != command.getProveedor()) {
            throw new IllegalArgumentException("El proveedor del token fake no coincide con el solicitado");
        }
        return new IdentidadExternaVerificadaResult(
                proveedor,
                "fake://" + proveedor.getApiValue(),
                List.of("servify-test-client"),
                partes[2],
                partes[3],
                true,
                "Usuario " + proveedor.getApiValue(),
                null,
                Instant.now().plusSeconds(600)
        );
    }
}
