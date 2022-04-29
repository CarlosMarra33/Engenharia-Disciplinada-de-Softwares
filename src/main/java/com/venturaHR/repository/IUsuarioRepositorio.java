package com.venturaHR.repository;

import com.venturaHR.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email) throws Exception;
}