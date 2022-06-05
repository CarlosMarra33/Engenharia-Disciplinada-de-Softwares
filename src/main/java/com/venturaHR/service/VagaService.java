package com.venturaHR.service;

import com.venturaHR.controller.dto.VagaDTO;

import java.util.List;

public interface VagaService {
    void criacaoDeVaga(VagaDTO vaga);
    void desativarVaga(Long idVaga);
    void cancelarVaga(Long idVaga);

    List<VagaDTO> pesquisarVagaPorCargo(String cargo);
}
