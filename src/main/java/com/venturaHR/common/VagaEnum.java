package com.venturaHR.common;

public enum VagaEnum {
    STATUS_VAGA_ATIVO(1),
    STATUS_VAGA_CANCELADO(0),
    STATUS_VAGA_INATIVO(2)
    ;
    private int valor;

    VagaEnum(int valor) {
        this.valor = valor;
    }

    public int getValor(){
        return valor;
    }
}
