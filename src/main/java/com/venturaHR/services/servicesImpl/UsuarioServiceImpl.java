package com.venturaHR.services.servicesImpl;

import com.venturaHR.models.Empresa;
import com.venturaHR.models.Profissional;
import com.venturaHR.models.Usuario;

import com.venturaHR.services.security.CriptografiaSenha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.venturaHR.controllers.dto.UsuarioDTO;
import com.venturaHR.repositorio.IProfissionalRepositorio;
import com.venturaHR.services.UsuarioService;

@Component
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private IProfissionalRepositorio repositorio;

    private CriptografiaSenha criptografiaSenha;

    public UsuarioServiceImpl(CriptografiaSenha criptografiaSenha) {
        this.criptografiaSenha = criptografiaSenha;
    }

    @Override
    public void criacaoDeConta(UsuarioDTO usuarioDto) throws Exception {
        Usuario usuario;
        usuarioDto.setPassword(criptografiaSenha.criptografarSenha(usuarioDto.getPassword()));
        if(usuarioDto.getTipoConta() == 1){
            usuario = new Profissional(usuarioDto.getNome(),
                    usuarioDto.getEmail(),
                    usuarioDto.getPassword(),
                    usuarioDto.getCpf()
            );
        }else {
            usuario = new Empresa(
                    usuarioDto.getNome(),
                    usuarioDto.getEmail(),
                    usuarioDto.getPassword(),
                    usuarioDto.getCnpj()
            );
        }
        repositorio.save(usuario);
    }

    @Override
    public Usuario checarUsuarioLogin(String email, String password) throws Exception {
        Usuario resposta = repositorio.findByEmail(email);
        if (criptografiaSenha.checarSenha(password, resposta.getPassword())){

        }else {
            return null;
        }
        return resposta;
    }
}
