package com.venturaHR.repository;

import com.venturaHR.domain.entity.Criterio;
import com.venturaHR.domain.entity.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICriterioRepositorio extends JpaRepository<Criterio, Long> {

    @Query("SELECT c FROM Criterio AS c where c.vagaId in ?1")
    Optional<List<Criterio>> findCriterioByListVaga(List<Vaga> vagas);
    @Query("SELECT c FROM Criterio AS c where c.vagaId in ?1")
    Optional<List<Criterio>> findCriteriosByVaga(Vaga vaga);
}
