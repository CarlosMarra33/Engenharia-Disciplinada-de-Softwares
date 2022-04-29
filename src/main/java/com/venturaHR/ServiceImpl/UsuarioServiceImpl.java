package com.venturaHR.ServiceImpl;

import com.venturaHR.ServiceImpl.security.CriptografiaSenha;
import com.venturaHR.ServiceImpl.security.SegurancaServiceImpl;
import com.venturaHR.controllers.dto.UserLoginDTO;
import com.venturaHR.service.UsuarioService;
import com.venturaHR.models.Empresa;
import com.venturaHR.models.Profissional;

import com.venturaHR.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.venturaHR.controllers.dto.UsuarioDTO;
import com.venturaHR.repository.IUsuarioRepositorio;

@Component
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private IUsuarioRepositorio repositorio;
    @Autowired
    private SegurancaServiceImpl segurancaServiceImpl;

    private final CriptografiaSenha criptografiaSenha;

    public UsuarioServiceImpl(CriptografiaSenha criptografiaSenha) {
        this.criptografiaSenha = criptografiaSenha;
    }

    @Override
    public void criacaoDeConta(UsuarioDTO usuarioDto) throws Exception {
        Usuario usuario;
        usuarioDto.setPassword(criptografiaSenha.criptografarSenha(usuarioDto.getPassword()));
        if(usuarioDto.getCpf() != null){
            usuario = new Profissional(usuarioDto.getNome(),
                    usuarioDto.getEmail(),
                    usuarioDto.getPassword(),
                    usuarioDto.getCpf()
            );
        }else{
            usuario = new Empresa(
                    usuarioDto.getNome(),
                    usuarioDto.getEmail(),
                    usuarioDto.getPassword(),
                    usuarioDto.getCnpj()
            );
        }
//        E
        repositorio.save(usuario);
    }

    @Override
    public UserLoginDTO checarUsuarioLogin(String email, String password) throws Exception {
        Usuario resposta = repositorio.findByEmail(email);
        if (criptografiaSenha.checarSenha(password, resposta.getPassword())){
            UserLoginDTO user = new UserLoginDTO();
            user.setNome(resposta.getNome());
            user.setEmail(resposta.getEmail());
            user.setTipoConta(identificarTipoConta(resposta));
            user.setToken(segurancaServiceImpl.criacaoToken(resposta));
            user.setTipoToken("Bearer");
            if (identificarTipoConta(resposta).equals("empresa")){
                Empresa userEmpresa = (Empresa) resposta;
                user.setCnpj(userEmpresa.getCpnj());
            }else if (identificarTipoConta(resposta).equals("profissional")){
                Profissional userProfissional = (Profissional) resposta;
                user.setCpf(userProfissional.getCpf());
            }
            return user;
        }else {
            return null;
        }
    }

    private String identificarTipoConta(Usuario user){
        if (user instanceof Profissional){
            Profissional usuario = new Profissional();
            usuario = (Profissional) user;
            return "profissional";
        }else if (user instanceof Empresa){
            return "empresa";
        }
        else {
            return null;
        }
    }
}
