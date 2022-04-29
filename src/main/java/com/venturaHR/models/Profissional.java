package com.venturaHR.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profissional extends Usuario{

    @Column(unique=false, nullable = true)
    private String cpf = "";

    public Profissional(String nome, String email, String password, String cpf) {
        super(nome, email, password);
        this.cpf = cpf;
    }
}
