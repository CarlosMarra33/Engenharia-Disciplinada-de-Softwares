package com.venturaHR.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
public class UsuarioDTO {
    
    @JsonProperty
    private String nome;
    @NonNull
    @JsonProperty
    private  String email;
    @JsonProperty
    private String password;
    @JsonProperty
    private String cpf= null;
    @JsonProperty
    private String cnpj = null;
    @JsonProperty
    private String matricula = null;
    @JsonProperty
    private int tipoConta;
    
    public UsuarioDTO(String nome, String email, String password, String cpf, String cnpj, int tipoConta) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.tipoConta = tipoConta;
    }
    public UsuarioDTO() {
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public int getTipoConta(){
        return this.tipoConta;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

}
