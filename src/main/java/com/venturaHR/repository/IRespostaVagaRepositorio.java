package com.venturaHR.repository;

import com.venturaHR.domain.entity.RespostaVaga;
import com.venturaHR.domain.entity.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRespostaVagaRepositorio extends JpaRepository<RespostaVaga, Long> {

    Optional<List<RespostaVaga>> findAllRespostasByVaga(Vaga vaga);
}
