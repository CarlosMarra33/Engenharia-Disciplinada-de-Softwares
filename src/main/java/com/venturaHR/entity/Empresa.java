package com.venturaHR.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Empresa extends Usuario {

    @Column(unique=false, nullable = true)
    private String cpnj;

    public Empresa(String nome, String email, String password, int statusConta, String cnpj) {
        super(nome, email, password, statusConta);
        this.cpnj = cpnj;
    }
}
