package com.venturaHR.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Candidato extends Usuario{

    @Column(unique=false, nullable = true)
    private String cpf = "";

    public Candidato(String nome, String email, String password, int statusConta, String cpf) {
        super(nome, email, password, statusConta);
        this.cpf = cpf;
    }
}
