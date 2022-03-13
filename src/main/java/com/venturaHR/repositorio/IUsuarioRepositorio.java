package com.venturaHR.repositorio;

import java.util.List;
import java.util.Optional;

import com.venturaHR.dto.UsuarioDTO;
import com.venturaHR.models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Long> {
    public Optional<List<Usuario>> findAllByEmail(String email) throws Exception;

    public UsuarioDTO findByEmailAndPassword(String email, String password) throws Exception; 
}
