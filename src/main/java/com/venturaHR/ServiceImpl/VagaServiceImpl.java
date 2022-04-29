package com.venturaHR.ServiceImpl;

import com.venturaHR.controllers.dto.RequestVagaDTO;
import com.venturaHR.models.Criterio;
import com.venturaHR.models.Empresa;
import com.venturaHR.models.Usuario;
import com.venturaHR.models.Vaga;
import com.venturaHR.repository.ICriterioRepositorio;
import com.venturaHR.repository.IUsuarioRepositorio;
import com.venturaHR.repository.IVagaRepositorio;
import com.venturaHR.service.VagaService;
import org.hibernate.type.LocalDateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class VagaServiceImpl implements VagaService {

    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;
    @Autowired
    private IVagaRepositorio vagaRepositorio;
    @Autowired
    private ICriterioRepositorio criterioRepositorio;

    @Override
    public void criacaoDeVaga(RequestVagaDTO vagaDTO) {
        try {
            Empresa usuarioEmpresa = (Empresa) usuarioRepositorio.findByEmail(vagaDTO.getEmail());
            Vaga vaga = new Vaga();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            dtf.format(LocalDateTime.now());
            long miliseconds = System.currentTimeMillis();
            Date date = new Date(miliseconds);
            vaga.setDataDeCriacao(date);
            vaga.setTitulo(vagaDTO.getTitulo());
            vaga.setCargo(vagaDTO.getCargo());
            vaga.setEmpresa(usuarioEmpresa);
            vagaRepositorio.save(vaga);
            criacaoCriterio(vaga, vagaDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void criacaoCriterio(Vaga vaga, RequestVagaDTO vagaDTO){
        for (String i : vagaDTO.getCriterios()){
            Criterio criterio = new Criterio();
            criterio.setSkillNome(i);
            criterio.setPeso(vagaDTO.getPesos().get(vagaDTO.getCriterios().indexOf(i)));
            criterio.setVagaId(vaga);
            criterioRepositorio.save(criterio);
        }
    }
}
