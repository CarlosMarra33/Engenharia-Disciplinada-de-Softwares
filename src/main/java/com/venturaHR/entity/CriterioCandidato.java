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
public class CriterioCandidato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "criterio_candidato_id", nullable = false)
    private Long criterioId;

    private String criterio;
    private int peso;

    @ManyToOne
    @JoinColumn(name = "RespostaCandidato_Id")
    private RespostaVaga resposta;
}
