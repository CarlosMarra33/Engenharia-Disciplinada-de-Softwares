package com.venturaHR.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CandidatoDTO {
    @JsonProperty
    private String nome;
    @JsonProperty
    private String email;
    @JsonProperty
    private double pontuacao;
}
