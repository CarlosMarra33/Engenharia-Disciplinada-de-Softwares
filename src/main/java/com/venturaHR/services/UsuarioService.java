package com.venturaHR.services;

import com.venturaHR.controllers.dto.UsuarioDTO;
import com.venturaHR.models.Usuario;

public interface UsuarioService {
    void criacaoDeConta(UsuarioDTO usuario) throws Exception;

    Usuario checarUsuarioLogin(String username, String password) throws Exception;
}
