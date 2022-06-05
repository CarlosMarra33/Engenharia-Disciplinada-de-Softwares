
package com.venturaHR.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class  Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long usuarioId;

    private String nome;

    @Column(unique=true)
    private String email;

    private String password;

    private int statusConta;


    public Usuario(String nome, String email, String password, int statusConta) {
        this.statusConta = statusConta;
        this.nome = nome;
        this.email = email;
        this.password = password;
    }
}
