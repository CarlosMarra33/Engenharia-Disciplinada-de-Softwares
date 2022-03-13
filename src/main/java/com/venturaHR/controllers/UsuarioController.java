/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.venturaHR.controllers;


import com.venturaHR.models.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.sql.SQLException;

import com.venturaHR.dto.UsuarioDTO;
import com.venturaHR.services.UsuarioService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/")
public class UsuarioController {
    
    private UsuarioService usuarioService;
    @Autowired
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("")
    public String Login(){
        return "oi";
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastro(@RequestBody UsuarioDTO payload){
        try {
            usuarioService.criacaoDeConta(payload);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString() ,HttpStatus.CREATED);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object>fazerLogin(@RequestBody UsuarioDTO payload) throws SQLException {

        try {
            UsuarioDTO usuario = usuarioService.checarUsuarioLogin(payload.getEmail(), payload.getPassword());
            if(usuario == null){
                return ResponseEntity.ok(usuario);
            }else{
                return new ResponseEntity<>(usuario, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
           return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
