package com.venturaHR.services.servicesImpl;

import java.io.IOException;
import java.sql.SQLException;

import com.venturaHR.models.Profissional;
import com.venturaHR.models.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.venturaHR.dto.UsuarioDTO;
import com.venturaHR.models.Empresa;
import com.venturaHR.repositorio.IUsuarioRepositorio;
import com.venturaHR.services.UsuarioService;

@Component
public class UsuarioServiceImpl implements UsuarioService{
    
    @Autowired
    private IUsuarioRepositorio repositorio;

    @Override
    public void criacaoDeConta(UsuarioDTO usuarioDto) throws Exception{
        Usuario usuario = new Usuario();
        if (repositorio.findAllByEmail(usuarioDto.getEmail()).isPresent()) {
            throw new Exception("Usuário já existe");
        }else{
            if (usuarioDto.getCpf() == null && usuarioDto.getCnpj()!= null) {
                usuario = new Empresa(usuarioDto.getNome(), usuarioDto.getEmail(), usuarioDto.getPassword(), usuarioDto.getCnpj());
            }else if(usuarioDto.getCpf() != null && usuarioDto.getCnpj() == null){
                usuario = new Profissional(usuarioDto.getNome(), usuarioDto.getEmail(), usuarioDto.getPassword(), usuarioDto.getCnpj());
            }else{
                throw new Exception("cpf e cnpj não podem ser nulos");
            }
            try {
                repositorio.save(usuario);
            } catch (Exception e) {
                throw new SQLException(e);
            }
        }

    }

    @Override
    public UsuarioDTO checarUsuarioLogin(String email, String password) throws Exception {
        UsuarioDTO resposta = repositorio.findByEmailAndPassword(email, password);
        
        return resposta;
    }
}
