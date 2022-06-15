package com.venturaHR.controller;

import com.venturaHR.dto.RespostaVagaDTO;
import com.venturaHR.dto.VagaDTO;
import com.venturaHR.service.CandidatoService;
import com.venturaHR.service.UsuarioService;
import com.venturaHR.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;
    @Autowired
    private VagaService vagaService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/home")
    public ResponseEntity<?> candidatoHome(@RequestParam String email){
        try {
            List<VagaDTO> listaVagas = vagaService.getAllVagasPeloTipo(email);
            return ResponseEntity.ok(listaVagas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    @GetMapping("/pesquisarVaga")
    public ResponseEntity pesquisarVaga(@RequestBody String cargo){
        try{
            List<VagaDTO> listVagas = vagaService.pesquisarVagaPorCargo(cargo);
            return ResponseEntity.ok(listVagas);
        }catch (Exception e){
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/responderVaga")
    public ResponseEntity responderVaga(@RequestBody RespostaVagaDTO respostaVagaDTO){
        try {
            candidatoService.resposnderVaga(respostaVagaDTO);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
