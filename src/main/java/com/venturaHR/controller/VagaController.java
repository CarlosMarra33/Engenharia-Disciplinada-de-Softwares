package com.venturaHR.controller;

//import com.venturaHR.ServiceImpl.security.SegurancaServiceImpl;
import com.venturaHR.controller.dto.VagaDTO;
import com.venturaHR.exception.UnauthorizedException;
import com.venturaHR.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaga")
public class VagaController {

    @Autowired
    private VagaService vagaService;

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
        List<VagaDTO> listVagas = vagaService.pesquisarVagaPorCargo(cargo);
        return null;
    }

}
