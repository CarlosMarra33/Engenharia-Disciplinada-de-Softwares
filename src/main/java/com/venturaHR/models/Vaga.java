package com.venturaHR.models;

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
public class Vaga {
    @Id
    @Column(name = "vaga_id", nullable = false)
    private Long vagaId;

    private List<Criterio> criterios;
    private String titulo;
    private String cargo;
    @OneToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public Empresa getEmpresa() {
        return empresa;
    }

}
