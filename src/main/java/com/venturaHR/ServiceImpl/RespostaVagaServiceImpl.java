package com.venturaHR.ServiceImpl;

import com.venturaHR.dto.CandidatoDTO;
import com.venturaHR.entity.RespostaVaga;
import com.venturaHR.entity.Vaga;
import com.venturaHR.exception.BadRequestException;
import com.venturaHR.repository.IRespostaVagaRepositorio;
import com.venturaHR.service.RespostaVagaService;
import com.venturaHR.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RespostaVagaServiceImpl implements RespostaVagaService {

    @Autowired
    private IRespostaVagaRepositorio respostaVagaRepositorio;
    @Autowired
    private VagaService vagaService;

    @Override
    public void salvarResposta(RespostaVaga respostaVaga) {
        respostaVagaRepositorio.save(respostaVaga);
    }

    @Override
    public List<CandidatoDTO> listarCandidatos(long vagaId) {
        Vaga vaga;
        List<RespostaVaga> respostaVagas;
        List<CandidatoDTO> resposta = new ArrayList<>();

        Optional<Vaga> v = vagaService.pesquisarVagaPorId(vagaId);
        if (v.isPresent()){
            vaga = v.get();
        }else throw new BadRequestException("Id da vaga inválido");
        Optional<List<RespostaVaga>> vagasDB = respostaVagaRepositorio.findAllRespostasByVaga(vaga);
        if (vagasDB.isPresent()){
            respostaVagas = vagasDB.get();
        }else throw new BadRequestException("Id da vaga inválido");

        for (RespostaVaga r : respostaVagas){
            CandidatoDTO c = new CandidatoDTO();
            c.setEmail(r.getEmailCandidato());
            c.setNome(r.getNomeCandidato());
            c.setPontuacao(r.getPontuacao());
            resposta.add(c);
        }

        return resposta;
    }
}
