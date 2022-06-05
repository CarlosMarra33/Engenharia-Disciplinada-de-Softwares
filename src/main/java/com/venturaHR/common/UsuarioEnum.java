package com.venturaHR.common;

public enum UsuarioEnum {
    STATUS_ATIVO(1),
    STATUS_DESATIVADO(0),

    TIPO_CONTA_CANDIDATO(1),
    TIPO_CONTA_EMPRESA(2),
    TIPO_CONTA_ADMIN(3),

    ;

    private int valor;

    UsuarioEnum(int valor) {
        this.valor = valor;
    }

    public int getValor(){
        return valor;
    }
}
