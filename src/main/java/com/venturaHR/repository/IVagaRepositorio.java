package com.venturaHR.repository;

import com.venturaHR.models.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVagaRepositorio extends JpaRepository<Vaga, Long> {

}
