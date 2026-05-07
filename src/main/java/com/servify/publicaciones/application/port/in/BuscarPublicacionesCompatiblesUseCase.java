package com.servify.publicaciones.application.port.in;

import com.servify.publicaciones.application.dto.BuscarPublicacionesCompatiblesQuery;
import com.servify.publicaciones.application.dto.PublicacionCompatibleResult;

import java.util.List;

public interface BuscarPublicacionesCompatiblesUseCase {

    List<PublicacionCompatibleResult> buscarCompatibles(BuscarPublicacionesCompatiblesQuery query);
}
