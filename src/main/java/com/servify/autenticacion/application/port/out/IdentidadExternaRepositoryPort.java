package com.servify.autenticacion.application.port.out;

import com.servify.autenticacion.domain.enumtype.ProveedorIdentidadExterna;
import com.servify.autenticacion.domain.model.IdentidadExterna;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IdentidadExternaRepositoryPort {

    IdentidadExterna guardar(IdentidadExterna identidadExterna);

    Optional<IdentidadExterna> buscarPorId(UUID identidadExternaId);

    Optional<IdentidadExterna> buscarPorProveedorYSubject(ProveedorIdentidadExterna proveedor, String subject);

    Optional<IdentidadExterna> buscarPorUsuarioIdYProveedor(UUID usuarioId, ProveedorIdentidadExterna proveedor);

    List<IdentidadExterna> buscarPorUsuarioId(UUID usuarioId);
}
