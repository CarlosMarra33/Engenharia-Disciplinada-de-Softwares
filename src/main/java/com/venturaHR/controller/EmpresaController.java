package com.venturaHR.controller;

import com.venturaHR.dto.CandidatoDTO;
import com.venturaHR.dto.VagaDTO;
import com.venturaHR.entity.Usuario;
import com.venturaHR.service.RespostaVagaService;
import com.venturaHR.service.UsuarioService;
import com.venturaHR.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private VagaService vagaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RespostaVagaService respostaVagaService;

    @PostMapping("/criacao")
    public ResponseEntity<?> criarVaga(@RequestBody VagaDTO payload){
        try {
            vagaService.criacaoDeVaga(payload);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/desativar_vaga")
    public ResponseEntity<?> desativarVaga(@RequestBody Long idVaga) {
        try {
            vagaService.desativarVaga(idVaga);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cancelar_vaga")
    public ResponseEntity<?> cancelarVaga(@RequestBody Long idVaga){
        try {
            vagaService.cancelarVaga(idVaga);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/reativar_vaga")
    public ResponseEntity reativarVaga(@RequestBody Long idVaga){
        try{
            vagaService.reativarVaga(idVaga);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
    


    @GetMapping("/home")
    public ResponseEntity home(@RequestParam String email){
        try {
            List<VagaDTO> listaVagas = vagaService.getAllVagasPeloTipo(email);
            return ResponseEntity.ok(listaVagas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/lista-candidatos")
    public ResponseEntity listarCandidatos(@RequestParam long vagaId){
        try {
            List<CandidatoDTO> usuarioList = respostaVagaService.listarCandidatos(vagaId);
            return ResponseEntity.ok(usuarioList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
