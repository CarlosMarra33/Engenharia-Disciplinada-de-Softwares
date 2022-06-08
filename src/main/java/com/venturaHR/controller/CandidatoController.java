package com.venturaHR.controller;

import com.venturaHR.controller.dto.UsuarioDTO;
import com.venturaHR.controller.dto.VagaDTO;
import com.venturaHR.entity.Vaga;
import com.venturaHR.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/home")
    public ResponseEntity<?> candidatoHome(@RequestBody UsuarioDTO payload){
        try {
            List<VagaDTO> listaVagas = usuarioService.getAllVagasPeloTipo(payload.getEmail());
            return ResponseEntity.ok(listaVagas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
}
