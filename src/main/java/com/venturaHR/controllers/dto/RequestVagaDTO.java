package com.venturaHR.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.venturaHR.models.Criterio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestVagaDTO {
    @JsonProperty
    private String email;
    @JsonProperty
    private String titulo;
    @JsonProperty
    private String cargo;
    @JsonProperty
    private String dataCriacao;
    @JsonProperty
    private List<String> criterios;
    @JsonProperty
    private List<Integer> pesos;
}
