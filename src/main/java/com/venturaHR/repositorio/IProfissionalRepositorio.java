package com.venturaHR.repositorio;

import com.venturaHR.controllers.dto.UsuarioDTO;

import com.venturaHR.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfissionalRepositorio extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email) throws Exception;
}
