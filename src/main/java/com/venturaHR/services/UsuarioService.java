package com.venturaHR.services;

import com.venturaHR.dto.UsuarioDTO;

public interface UsuarioService {
    public void criacaoDeConta(UsuarioDTO usuario) throws Exception;

    public UsuarioDTO checarUsuarioLogin(String username, String password) throws Exception;
}
