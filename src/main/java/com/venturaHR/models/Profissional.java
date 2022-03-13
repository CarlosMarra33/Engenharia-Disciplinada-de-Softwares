package com.venturaHR.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("profissional")
public class Profissional extends Usuario{
    
    private String cpf;


    public Profissional() {
    }

    public Profissional(String nome, String email, String password, String cpf) {
        super(nome, email, password);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

 
}
