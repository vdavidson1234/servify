package com.servify.usuarios.application.port.in;

import com.servify.usuarios.application.dto.UsuarioResult;
import com.servify.usuarios.domain.enumtype.EstadoUsuario;
import java.util.List;

public interface ListarUsuariosUseCase {

    List<UsuarioResult> listarPorEstado(EstadoUsuario estado);
}
