package com.venturaHR.controller;

import com.venturaHR.controller.dto.UsuarioDTO;
import com.venturaHR.controller.dto.VagaDTO;
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
    public ResponseEntity<?> desativarVaga(@RequestParam Long idVaga, @RequestHeader String token) {

        try {
//            if(!segurancaService.validacao(token)) {
//                throw new UnauthorizedException("token inválido!");
//            }
            vagaService.desativarVaga(idVaga);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/cancelar_vaga")
    public ResponseEntity<?> cancelarVaga(@RequestParam Long idVaga){
        try {
            vagaService.cancelarVaga(idVaga);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
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

    @GetMapping("/home")
    public ResponseEntity home(@RequestBody UsuarioDTO payload){
        try {
            List<VagaDTO> listaVagas = usuarioService.getAllVagasPeloTipo(payload.getEmail());
            return ResponseEntity.ok(listaVagas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}