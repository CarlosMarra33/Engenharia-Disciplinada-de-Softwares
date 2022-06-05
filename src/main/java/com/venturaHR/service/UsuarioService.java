package com.venturaHR.service;

import com.venturaHR.controller.dto.UserLoginDTO;
import com.venturaHR.controller.dto.UsuarioDTO;
import com.venturaHR.controller.dto.VagaDTO;
import com.venturaHR.entity.Vaga;

import java.util.List;

public interface UsuarioService {
    void criacaoDeConta(UsuarioDTO usuario) throws Exception;
    UserLoginDTO checarUsuarioLogin(String username, String password) throws Exception;
    void desativarConta(String email) throws Exception;
    List<VagaDTO> getAllVagasPeloTipo(String email);
}
