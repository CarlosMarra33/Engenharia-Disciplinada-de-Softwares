package com.venturaHR.service;

import com.venturaHR.controller.dto.UserLoginDTO;
import com.venturaHR.controller.dto.UsuarioDTO;

public interface UsuarioService {
    void criacaoDeConta(UsuarioDTO usuario) throws Exception;
    UserLoginDTO checarUsuarioLogin(String username, String password) throws Exception;
    void desativarConta(String email) throws Exception;
}
