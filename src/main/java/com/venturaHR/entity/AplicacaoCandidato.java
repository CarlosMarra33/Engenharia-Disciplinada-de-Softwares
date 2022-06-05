package com.venturaHR.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AplicacaoCandidato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aplicacao_candidato_id", nullable = false)
    private Long aplicacaoCandidatoId;

    @OneToOne
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;
    @OneToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

}
