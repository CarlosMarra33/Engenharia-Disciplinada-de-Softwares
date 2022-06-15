package com.venturaHR.dto;

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
public class RespostaVagaDTO {
    @JsonProperty
    private String nomeCandidato;
    @JsonProperty
    private  String emailCandidato;
    @JsonProperty
    private Long VagaId;
    @JsonProperty
    private List<CriterioDTO> criterios;
}
