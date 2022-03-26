package com.venturaHR.models;

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
    @Column(unique=true, nullable = false, columnDefinition = "varchar(50) default '00'")
    private String matricula;

    public Admin(String nome, String email, String password, String matricula) {
        super(nome, email, password);
        this.matricula = matricula;
    }
}
