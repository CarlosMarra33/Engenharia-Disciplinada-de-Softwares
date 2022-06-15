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
public class UserLoginDTO {

    @JsonProperty
    private String nome;
    @JsonProperty
    private String email;
    @JsonProperty
    private int TipoConta;
    @JsonProperty
    private String token;
    @JsonProperty
    private String cnpj;
    @JsonProperty
    private String cpf;
    @JsonProperty
    private String matricula;
}
