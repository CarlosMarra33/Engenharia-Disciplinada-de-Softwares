/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package venturaHR.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import venturaHR.models.Profissional;
import venturaHR.models.Usuario;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 *
 * @author Carlos Marra
 */
@RestController
@RequestMapping("/")
public class UsuarioController {
    
    @GetMapping("")
    public String Login(){
        return "oi";
    }

    @PostMapping("/cadastro")
    public void cadastro(@RequestBody Usuario payload) {
       
    }
    
}
