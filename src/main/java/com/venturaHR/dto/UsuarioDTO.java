package com.venturaHR.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.lang.NonNull;

public class UsuarioDTO {
    
    @JsonProperty("Nome")
    private String nome;
    @NonNull
    @JsonProperty("Email")
    private  String email;
    @JsonProperty("Password")
    private String password;
    @JsonProperty("CPF")
    private String cpf;
    @JsonProperty("CNPJ")
    private String cnpj;
    
    public UsuarioDTO(String nome, String email, String password, String cpf, String cnpj) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.cnpj = cnpj;
    }
    public UsuarioDTO() {
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
