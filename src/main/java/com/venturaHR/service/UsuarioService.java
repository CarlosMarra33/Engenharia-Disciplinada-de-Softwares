package com.venturaHR.service;

import com.venturaHR.dto.UserLoginDTO;
import com.venturaHR.dto.UsuarioDTO;
import com.venturaHR.dto.VagaDTO;

import java.util.List;

public interface UsuarioService {
    void criacaoDeConta(UsuarioDTO usuario) throws Exception;
    UserLoginDTO checarUsuarioLogin(String username, String password) throws Exception;
    void desativarConta(String email) throws Exception;
}
