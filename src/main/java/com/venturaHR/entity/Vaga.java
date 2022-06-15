package com.venturaHR.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaga_id", nullable = false)
    private Long vagaId;

    private String titulo;
    private String cargo;
    private String descricao;
    private Date dataDeCriacao;
    private int status;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
}
