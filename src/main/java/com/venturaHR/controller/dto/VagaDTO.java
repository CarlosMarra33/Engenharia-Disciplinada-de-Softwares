package com.venturaHR.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VagaDTO {
    @JsonProperty
    private Long vagaId;
    @JsonProperty
    private String email;
    @JsonProperty
    private String titulo;
    @JsonProperty
    private String cargo;
    @JsonProperty
    private String descricao;
    @JsonProperty
    private String dataCriacao;
    @JsonProperty
    private List<CriterioDTO> criterios;
    @JsonProperty
    private int status;
}
