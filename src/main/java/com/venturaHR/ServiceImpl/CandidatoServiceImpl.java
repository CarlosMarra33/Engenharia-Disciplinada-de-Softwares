package com.venturaHR.ServiceImpl;

import com.venturaHR.dto.CriterioDTO;
import com.venturaHR.dto.RespostaVagaDTO;
import com.venturaHR.entity.Criterio;
import com.venturaHR.entity.CriterioCandidato;
import com.venturaHR.entity.RespostaVaga;
import com.venturaHR.entity.Vaga;
import com.venturaHR.exception.BadRequestException;
import com.venturaHR.repository.ICriterioRepositorio;
import com.venturaHR.service.CandidatoService;
import com.venturaHR.service.RespostaVagaService;
import com.venturaHR.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CandidatoServiceImpl implements CandidatoService {

    @Autowired
    private VagaService vagaService;
    @Autowired
    private RespostaVagaService respostaVagaService;
    @Autowired
    private ICriterioRepositorio criterioRepositorio;

    @Override
    public void resposnderVaga(RespostaVagaDTO respostaVagaDTO) {
        RespostaVaga respostaVaga = new RespostaVaga();
        List<CriterioCandidato> criteriosCandidato = new ArrayList<>();
        Vaga vaga;
        List<Criterio> criterios;


        Optional<Vaga> vagaOptional = vagaService.pesquisarVagaPorId(respostaVagaDTO.getVagaId());
        if(vagaOptional.isPresent()){
            vaga = vagaOptional.get();
        }else {
            throw new BadRequestException("Vaga não encontrada");
        }
        respostaVaga.setNomeCandidato(respostaVaga.getNomeCandidato());
        respostaVaga.setEmailCandidato(respostaVagaDTO.getEmailCandidato());


        for (CriterioDTO c : respostaVagaDTO.getCriterios()){
            CriterioCandidato criterioCandidato = new CriterioCandidato();
            criterioCandidato.setCriterio(c.getCriterios());
            criterioCandidato.setPeso(c.getPesos());
            criteriosCandidato.add(criterioCandidato);
        }
        respostaVaga.setCriteriosCandidato(criteriosCandidato);
        respostaVaga.setVaga(vaga);

        Optional<List<Criterio>> c = criterioRepositorio.findCriteriosByVaga(vaga);
        if (c.isPresent()){
            criterios = c.get();
        }else {
            throw new BadRequestException("Criterios não encontrado");
        }
        respostaVaga.setPontuacao(calcularMedias(criteriosCandidato, criterios));
        respostaVagaService.salvarResposta(respostaVaga);


    }

    private double calcularMedias(List<CriterioCandidato> criteriosCandidato, List<Criterio> criterios) {
        double resposta = 1;
        double dividendo = 1;
        for (CriterioCandidato candidato: criteriosCandidato){
            for (Criterio criterio:criterios){
                dividendo += criterio.getPeso();
                if (candidato.getCriterio().equals(criterio.getCriterioNome())){
                    resposta += (candidato.getPeso() * criterio.getPeso());
                }
            }
        }
        resposta = resposta/dividendo;

        return resposta;
    }

}
