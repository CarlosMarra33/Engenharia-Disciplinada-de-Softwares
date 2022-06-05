package com.venturaHR.repository;

import com.venturaHR.entity.Empresa;
import com.venturaHR.entity.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface IVagaRepositorio extends JpaRepository<Vaga, Long> {

    @Transactional
////    @Modifying
//    @Query(value = "SELECT * FROM Vaga v where v.status in 1")
    Optional<List<Vaga>> findVagaByStatus(int status);

    Optional<List<Vaga>> findVagaByEmpresa(Empresa empresa);

    @Modifying
    @Query(value = "SELECT v FROM Vaga AS v where v.cargo LIKE '%?1%' ")
    Optional<List<Vaga>> findVagaByCargo(String cargo);
}
