package com.venturaHR.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AplicacaoCandidato {
    @Id
    @Column(name = "aplicacao_candidato_id", nullable = false)
    private Long aplicacaoCandidatoId;

    @OneToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;
    @OneToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

}
