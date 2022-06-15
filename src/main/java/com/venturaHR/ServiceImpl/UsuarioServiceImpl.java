package com.venturaHR.ServiceImpl;

import com.venturaHR.security.CriptografiaSenha;
//import com.venturaHR.ServiceImpl.security.SegurancaServiceImpl;
import com.venturaHR.security.TokenUtil;
import com.venturaHR.dto.UserLoginDTO;
import com.venturaHR.common.UsuarioEnum;
import com.venturaHR.dto.VagaDTO;
import com.venturaHR.entity.*;
import com.venturaHR.exception.BadRequestException;
import com.venturaHR.repository.ICriterioRepositorio;
import com.venturaHR.repository.IVagaRepositorio;
import com.venturaHR.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.venturaHR.dto.UsuarioDTO;
import com.venturaHR.repository.IUsuarioRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;
    @Autowired
    private ICriterioRepositorio criterioRepositorio;

    private final CriptografiaSenha criptografiaSenha;

    public UsuarioServiceImpl(CriptografiaSenha criptografiaSenha) {
        this.criptografiaSenha = criptografiaSenha;
    }

    @Override
    public void criacaoDeConta(UsuarioDTO usuarioDto) throws Exception {
        Optional<Usuario> userData = usuarioRepositorio.findByEmail(usuarioDto.getEmail());
        Usuario usuario;
        if (userData.isPresent()){
            throw new BadRequestException("Usuário já existe");
        }
        usuarioDto.setPassword(criptografiaSenha.criptografarSenha(usuarioDto.getPassword()));
        if(usuarioDto.getTipoConta() == UsuarioEnum.TIPO_CONTA_CANDIDATO.getValor()){
            Candidato candidato = new Candidato();
            candidato.setCpf(usuarioDto.getCpf());
            candidato.setStatusConta(UsuarioEnum.STATUS_ATIVO.getValor());
            candidato.setEmail(usuarioDto.getEmail());
            candidato.setNome(usuarioDto.getNome());
            candidato.setPassword(usuarioDto.getPassword());
            usuario = candidato;
        }else if (usuarioDto.getTipoConta() == UsuarioEnum.TIPO_CONTA_EMPRESA.getValor()){
            Empresa empresa = new Empresa();
            empresa.setCpnj(usuarioDto.getCnpj());
            empresa.setStatusConta(UsuarioEnum.STATUS_ATIVO.getValor());
            empresa.setEmail(usuarioDto.getEmail());
            empresa.setNome(usuarioDto.getNome());
            empresa.setPassword(usuarioDto.getPassword());
            usuario = empresa;
        }else{
         throw new BadRequestException("Tipo de conta inválida");
        }
        usuarioRepositorio.save(usuario);
    }

    @Override
    public UserLoginDTO checarUsuarioLogin(String email, String password){
        Optional<Usuario> userData = usuarioRepositorio.findByEmail(email);
        Usuario resposta;
        if (userData.isPresent()){
            resposta = userData.get();
        }else {
            throw new BadRequestException("Usuáiro não encontrado");
        }
        if (criptografiaSenha.checarSenha(password, resposta.getPassword()) && resposta.getStatusConta() == UsuarioEnum.STATUS_ATIVO.getValor()){
            UserLoginDTO user = new UserLoginDTO();
            user.setNome(resposta.getNome());
            user.setEmail(resposta.getEmail());
            user.setTipoConta(identificarTipoConta(resposta));
            user.setToken(TokenUtil.createToken(resposta.getEmail()));
            if (identificarTipoConta(resposta) == UsuarioEnum.TIPO_CONTA_EMPRESA.getValor()){
                Empresa userEmpresa = (Empresa) resposta;
                user.setCnpj(userEmpresa.getCpnj());
            }else if (identificarTipoConta(resposta) == UsuarioEnum.TIPO_CONTA_CANDIDATO.getValor()){
                Candidato userCandidato = (Candidato) resposta;
                user.setCpf(userCandidato.getCpf());
            }else if (identificarTipoConta(resposta) == UsuarioEnum.TIPO_CONTA_ADMIN.getValor()){
                Admin userAdmin = (Admin) resposta;
                user.setMatricula(userAdmin.getMatricula());
            }
            return user;
        }else {
            throw  new BadRequestException("Senha invalida");
        }
    }



    @Override
    public void desativarConta(String email) throws Exception {
        Optional<Usuario> userData = usuarioRepositorio.findByEmail(email);
        Usuario usuario ;
        if (userData.isPresent()){
            usuario = userData.get();
        }else {
            throw new BadRequestException("Usuáiro não encontrado");
        }
        usuario.setStatusConta(UsuarioEnum.STATUS_DESATIVADO.getValor());
        usuarioRepositorio.save(usuario);
    }




    private int identificarTipoConta(Usuario user){
        if (user instanceof Candidato){
            return UsuarioEnum.TIPO_CONTA_CANDIDATO.getValor();
        }else if (user instanceof Empresa){
            return UsuarioEnum.TIPO_CONTA_EMPRESA.getValor();
        }else if (user instanceof Admin){
            return UsuarioEnum.TIPO_CONTA_ADMIN.getValor();
        }else {
            return 0;
        }
    }
}
