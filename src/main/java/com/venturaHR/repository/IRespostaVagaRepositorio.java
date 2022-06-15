package com.venturaHR.repository;

import com.venturaHR.entity.RespostaVaga;
import com.venturaHR.entity.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IRespostaVagaRepositorio extends JpaRepository<RespostaVaga, Long> {

    Optional<List<RespostaVaga>> findAllRespostasByVaga(Vaga vaga);
}
