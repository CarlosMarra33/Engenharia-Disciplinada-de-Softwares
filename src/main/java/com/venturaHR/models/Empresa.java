package com.venturaHR.models;

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

    @Column(unique=true, nullable = false, columnDefinition = "varchar(50) default '00'")
    private String cpnj = "";

    public Empresa(String nome, String email, String password, String cpnj) {
        super(nome, email, password);
        this.cpnj = cpnj;
    }
}
