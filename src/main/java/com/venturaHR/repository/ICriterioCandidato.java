package com.venturaHR.repository;

import com.venturaHR.domain.entity.CriterioCandidato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICriterioCandidato extends JpaRepository<CriterioCandidato, Long> {
}
