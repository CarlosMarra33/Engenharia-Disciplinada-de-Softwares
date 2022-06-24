package com.venturaHR.service;

import com.venturaHR.controller.dto.CandidatoDTO;
import com.venturaHR.domain.entity.RespostaVaga;

import java.util.List;

public interface RespostaVagaService {
    void salvarResposta(RespostaVaga respostaVaga);

    List<CandidatoDTO> listarCandidatos(long vagaId);
}
