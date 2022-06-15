package com.venturaHR.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RespostaVaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "respostaVaga_id", nullable = false)
    private long id;
    private String nomeCandidato;
    private String emailCandidato;
    private double pontuacao;
    @ManyToOne
    @JoinColumn(name = "vaga_id")
    private Vaga vaga;
    @OneToMany(mappedBy = "resposta",cascade = CascadeType.PERSIST)
    private List<CriterioCandidato> criteriosCandidato;
}
