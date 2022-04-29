package com.venturaHR.repository;

import com.venturaHR.models.Criterio;
import com.venturaHR.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICriterioRepositorio extends JpaRepository<Criterio, Long> {
}
