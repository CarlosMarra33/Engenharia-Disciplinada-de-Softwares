package com.venturaHR.repository;

import com.venturaHR.entity.Criterio;
import com.venturaHR.entity.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICriterioRepositorio extends JpaRepository<Criterio, Long> {

    @Query("SELECT c FROM Criterio AS c where c.vagaId in ?1")
    Optional<List<Criterio>> findCriterioByVaga(List<Vaga> vagas);
}
