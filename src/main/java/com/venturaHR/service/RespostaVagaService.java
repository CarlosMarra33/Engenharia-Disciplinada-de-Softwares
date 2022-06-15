package com.venturaHR.service;

import com.venturaHR.dto.CandidatoDTO;
import com.venturaHR.entity.RespostaVaga;

import java.util.List;

public interface RespostaVagaService {
    void salvarResposta(RespostaVaga respostaVaga);

    List<CandidatoDTO> listarCandidatos(long vagaId);
}
