package com.venturaHR.models;

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
public class Criterio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "criterio_id", nullable = false)
    private Long criterioId;

    private String skillNome;
    private int peso;

    @ManyToOne
    @JoinColumn(name = "vaga_id")
    private Vaga vagaId;
}
