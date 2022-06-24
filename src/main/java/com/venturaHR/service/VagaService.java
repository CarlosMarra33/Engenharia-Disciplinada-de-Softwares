package com.venturaHR.service;

import com.venturaHR.controller.dto.VagaDTO;
import com.venturaHR.domain.entity.Vaga;

import java.util.List;
import java.util.Optional;

public interface VagaService {
    void criacaoDeVaga(VagaDTO vaga);
    void desativarVaga(Long idVaga);
    void cancelarVaga(Long idVaga);

    List<VagaDTO> pesquisarVagaPorCargo(String cargo);

    void reativarVaga(Long idVaga);
    List<VagaDTO> getAllVagasPeloTipo(String email);

    Optional<Vaga> pesquisarVagaPorId(long id);

    List<Vaga> pegarTodas();
}
