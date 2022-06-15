package com.venturaHR.ServiceImpl;

import com.venturaHR.common.VagaEnum;
import com.venturaHR.dto.CriterioDTO;
import com.venturaHR.dto.VagaDTO;
import com.venturaHR.entity.*;
import com.venturaHR.exception.BadRequestException;
import com.venturaHR.repository.ICriterioRepositorio;
import com.venturaHR.repository.IUsuarioRepositorio;
import com.venturaHR.repository.IVagaRepositorio;
import com.venturaHR.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VagaServiceImpl implements VagaService {

    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;
    @Autowired
    private IVagaRepositorio vagaRepositorio;
    @Autowired
    private ICriterioRepositorio criterioRepositorio;




    @Override
    public void criacaoDeVaga(VagaDTO vagaDTO) {
        try {
            Optional<Usuario> userData = usuarioRepositorio.findByEmail(vagaDTO.getEmail());
            Empresa usuarioEmpresa;
            if (userData.isPresent()){
                 usuarioEmpresa = (Empresa) userData.get();

            }else {
                throw new BadRequestException("usuario invalido");
            }
            Vaga vaga = new Vaga();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            dtf.format(LocalDateTime.now());
            long miliseconds = System.currentTimeMillis();
            Date date = new Date(miliseconds);
            vaga.setDataDeCriacao(date);
            vaga.setTitulo(vagaDTO.getTitulo());
            vaga.setCargo(vagaDTO.getCargo());
            vaga.setEmpresa(usuarioEmpresa);
            vaga.setStatus(VagaEnum.STATUS_VAGA_ATIVO.getValor());
            vaga.setDescricao(vagaDTO.getDescricao());
            vagaRepositorio.save(vaga);
            criacaoCriterio(vaga, vagaDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void desativarVaga(Long idVaga) {
        Optional<Vaga> respostaBD = vagaRepositorio.findById(idVaga);
        Vaga vaga = null;
        if (respostaBD.isPresent()){
             vaga = respostaBD.get();
             vaga.setStatus(VagaEnum.STATUS_VAGA_INATIVO.getValor());
             vagaRepositorio.save(vaga);
        }
    }

    @Override
    public void cancelarVaga(Long idVaga) {
        Optional<Vaga> respostaBD = vagaRepositorio.findById(idVaga);
        Vaga vaga = null;
        if (respostaBD.isPresent()){
            vaga = respostaBD.get();
            vaga.setStatus(VagaEnum.STATUS_VAGA_CANCELADO.getValor());
            vagaRepositorio.save(vaga);
        }
    }

    @Override
    public List<VagaDTO> pesquisarVagaPorCargo(String cargo) {
        List<VagaDTO> respostaVagas = new ArrayList<>();
        CriterioDTO criterioDTO;
        List<CriterioDTO> criteriosDTO;
        Optional<List<Vaga>> optionalVagaList = vagaRepositorio.findVagaByCargo(cargo);
        List<Vaga> vagas = new ArrayList<>();
        List<Criterio> criterios = new ArrayList<>();


        if (optionalVagaList.isPresent()) vagas = optionalVagaList.get();
        Optional<List<Criterio>> criteriosData = criterioRepositorio.findCriterioByListVaga(vagas);
        if (criteriosData.isPresent()){
            criterios = criteriosData.get();
        }
        for (Vaga v: vagas){
            VagaDTO vaga = new VagaDTO();
            criteriosDTO = new ArrayList<>();
            vaga.setCargo(v.getCargo());
            vaga.setTitulo(v.getTitulo());
            vaga.setEmail(v.getEmpresa().getEmail());
            vaga.setDataCriacao(v.getDataDeCriacao().toString());
            for (Criterio c: criterios){
                if (c.getVagaId() == v){
                    criterioDTO = new CriterioDTO();
                    criterioDTO.setCriterios(c.getCriterioNome());
                    criterioDTO.setPesos(c.getPeso());
                    criteriosDTO.add(criterioDTO);
                }
            }
            vaga.setCriterios(criteriosDTO);
            respostaVagas.add(vaga);
        }

        return respostaVagas;
    }

    @Override
    public void reativarVaga(Long idVaga) {
        Optional<Vaga> respostaBD = vagaRepositorio.findById(idVaga);
        Vaga vaga = null;
        if (respostaBD.isEmpty()){
            throw new BadRequestException("Vaga não encontrada");
        }
        vaga = respostaBD.get();
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        vaga.setStatus(VagaEnum.STATUS_VAGA_ATIVO.getValor());
        vaga.setDataDeCriacao(date);
        vagaRepositorio.save(vaga);
    }

    @Override
    public List<VagaDTO> getAllVagasPeloTipo(String email) {
        String critNameTemp;
        String critPesoTemp;
        Optional<Usuario> userData = usuarioRepositorio.findByEmail(email);
        Usuario usuario ;
        List<CriterioDTO> criteriosDTO;
        List<VagaDTO> respostaVagas = new ArrayList<>();
        List<Criterio> criterios = new ArrayList<>();
        List<Vaga> vagas = new ArrayList<>();
        List<Long> vagaIds = new ArrayList<>();


        if (userData.isPresent()){
            usuario = userData.get();
        }else {
            throw new BadRequestException("Usuáiro não encontrado");
        }



        if (usuario instanceof Candidato){
            Optional<List<Vaga>> optionalVagaList = vagaRepositorio.findVagaByStatus(1);
            if (optionalVagaList.isPresent()) vagas = optionalVagaList.get();
            for (Vaga v: vagas){
                vagaIds.add(v.getVagaId());
            }
            Optional<List<Criterio>> c = criterioRepositorio.findCriterioByListVaga(vagas);
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
            Optional<List<Criterio>> c = criterioRepositorio.findCriterioByListVaga(vagas);
            if (c.isPresent()){
                criterios = c.get();
            }
        }

        for (Vaga v: vagas){
            VagaDTO vaga = new VagaDTO();
            criteriosDTO = new ArrayList<>();
            vaga.setVagaId(v.getVagaId());
            vaga.setCargo(v.getCargo());
            vaga.setTitulo(v.getTitulo());
            vaga.setEmail(v.getEmpresa().getEmail());
            vaga.setDataCriacao(v.getDataDeCriacao().toString());
            for (Criterio c: criterios){
                if (c.getVagaId() == v){
                    CriterioDTO critDto = new CriterioDTO();
                    critDto.setCriterios(c.getCriterioNome());
                    critDto.setPesos(c.getPeso());
                    criteriosDTO.add(critDto);
                }
            }
            vaga.setCriterios(criteriosDTO);
//            vaga.setPesos(critPesoTemp);
            respostaVagas.add(vaga);
        }
        return respostaVagas;

    }

    @Override
    public Optional<Vaga> pesquisarVagaPorId(long id) {

        return vagaRepositorio.findById(id);
    }

    public void criacaoCriterio(Vaga vaga, VagaDTO vagaDTO){
        for (CriterioDTO i : vagaDTO.getCriterios()){
            Criterio criterio = new Criterio();
            criterio.setCriterioNome(i.getCriterios());
            criterio.setPeso(i.getPesos());
            criterio.setVagaId(vaga);
            criterioRepositorio.save(criterio);
        }
    }


}
