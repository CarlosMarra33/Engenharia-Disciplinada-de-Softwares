package com.venturaHR.ServiceImpl;

import com.venturaHR.common.VagaEnum;
import com.venturaHR.controller.dto.VagaDTO;
import com.venturaHR.entity.Criterio;
import com.venturaHR.entity.Empresa;
import com.venturaHR.entity.Usuario;
import com.venturaHR.entity.Vaga;
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
        Optional<List<Vaga>> listaVaga = vagaRepositorio.findVagaByCargo(cargo);


        return null;
    }

    public void criacaoCriterio(Vaga vaga, VagaDTO vagaDTO){
        for (String i : vagaDTO.getCriterios()){
            Criterio criterio = new Criterio();
            criterio.setSkillNome(i);
            criterio.setPeso(vagaDTO.getPesos().get(vagaDTO.getCriterios().indexOf(i)));
            criterio.setVagaId(vaga);
            criterioRepositorio.save(criterio);
        }
    }
}
