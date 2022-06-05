package com.venturaHR.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin extends Usuario{
    @Column(unique=false, nullable = true)
    private String matricula;

    public Admin(String nome, String email, String password, int statusConta, String cpf) {
        super(nome, email, password, statusConta);
        this.matricula = matricula;
    }
}
