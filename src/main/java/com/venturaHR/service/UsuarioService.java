package com.venturaHR.service;

import com.venturaHR.controllers.dto.UserLoginDTO;
import com.venturaHR.controllers.dto.UsuarioDTO;

public interface UsuarioService {
    void criacaoDeConta(UsuarioDTO usuario) throws Exception;

    UserLoginDTO checarUsuarioLogin(String username, String password) throws Exception;
}
