package com.venturaHR.controllers;

import com.venturaHR.controllers.dto.RequestVagaDTO;
import com.venturaHR.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vaga")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @PostMapping("/criacao")
    public ResponseEntity<?> criarVaga(@RequestBody RequestVagaDTO payload){
        try {
            vagaService.criacaoDeVaga(payload);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
//
//    @GetMapping("/listarTodas")
//    public ResponseEntity<>
}
