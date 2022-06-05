package com.venturaHR.ServiceImpl;

import com.venturaHR.ServiceImpl.security.CriptografiaSenha;
//import com.venturaHR.ServiceImpl.security.SegurancaServiceImpl;
import com.venturaHR.ServiceImpl.security.TokenUtil;
import com.venturaHR.controller.dto.UserLoginDTO;
import com.venturaHR.common.UsuarioEnum;
import com.venturaHR.controller.dto.VagaDTO;
import com.venturaHR.entity.*;
import com.venturaHR.exception.BadRequestException;
import com.venturaHR.repository.ICriterioRepositorio;
import com.venturaHR.repository.IVagaRepositorio;
import com.venturaHR.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.venturaHR.controller.dto.UsuarioDTO;
import com.venturaHR.repository.IUsuarioRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;
    @Autowired
    private IVagaRepositorio vagaRepositorio;
//    @Autowired
//    private SegurancaServiceImpl segurancaServiceImpl;
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
            if (identificarTipoConta(resposta).equals("Empresa")){
                Empresa userEmpresa = (Empresa) resposta;
                user.setCnpj(userEmpresa.getCpnj());
            }else if (identificarTipoConta(resposta).equals("Candidato")){
                Candidato userCandidato = (Candidato) resposta;
                user.setCpf(userCandidato.getCpf());
            }else if (identificarTipoConta(resposta).equals("Admin")){
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

    @Override
    public List<VagaDTO> getAllVagasPeloTipo(String email) {
        List<String> critNameTemp = new ArrayList<>();
        List<Integer> critPesoTemp = new ArrayList<>();
        Optional<Usuario> userData = usuarioRepositorio.findByEmail(email);
        Usuario usuario ;


        if (userData.isPresent()){
            usuario = userData.get();
        }else {
            throw new BadRequestException("Usuáiro não encontrado");
        }

        List<VagaDTO> respostaVagas = new ArrayList<>();
        List<Criterio> criterios = new ArrayList<>();
        List<Vaga> vagas = new ArrayList<>();
        List<Long> vagaIds = new ArrayList<>();

        if (usuario instanceof Candidato){
            Optional<List<Vaga>> optionalVagaList = vagaRepositorio.findVagaByStatus(1);
            if (optionalVagaList.isPresent()) vagas = optionalVagaList.get();
            for (Vaga v: vagas){
                vagaIds.add(v.getVagaId());
            }
            Optional<List<Criterio>> c = criterioRepositorio.findCriterioByVaga(vagas);
            if (c.isPresent()){
                criterios = c.get();
            }
        }else if (usuario instanceof Empresa){
            Empresa empresa = (Empresa) usuario;
            Optional<List<Vaga>> optionalVagaList = vagaRepositorio.findVagaByEmpresa(empresa);
            if (optionalVagaList.isPresent()) vagas = optionalVagaList.get();
            for (Vaga v: vagas){
                vagaIds.add(v.getVagaId());
            }
            Optional<List<Criterio>> c = criterioRepositorio.findCriterioByVaga(vagas);
            if (c.isPresent()){
                criterios = c.get();
            }
        }

        for (Vaga v: vagas){
            VagaDTO vaga = new VagaDTO();
            critNameTemp = new ArrayList<>();
            critPesoTemp = new ArrayList<>();
            vaga.setCargo(v.getCargo());
            vaga.setTitulo(v.getTitulo());
            vaga.setEmail(v.getEmpresa().getEmail());
            vaga.setDataCriacao(v.getDataDeCriacao().toString());
            for (Criterio c: criterios){
                if (c.getVagaId() == v){
                    critNameTemp.add(c.getSkillNome());
                    critPesoTemp.add(c.getPeso());
                }
            }
            vaga.setCriterios(critNameTemp);
            vaga.setPesos(critPesoTemp);
            respostaVagas.add(vaga);
        }
        return respostaVagas;

    }


    private String identificarTipoConta(Usuario user){
        if (user instanceof Candidato){
            return "Candidato";
        }else if (user instanceof Empresa){
            return "Empresa";
        }else if (user instanceof Admin){
            return "Admin";
        }else {
            return null;
        }
    }
}
