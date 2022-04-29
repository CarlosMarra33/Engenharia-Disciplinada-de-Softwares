package com.venturaHR.controllers.dto;

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
    private String TipoConta;
    @JsonProperty
    private String token;
    @JsonProperty
    private String tipoToken;
    @JsonProperty
    private String cnpj;
    @JsonProperty
    private String cpf;
}
