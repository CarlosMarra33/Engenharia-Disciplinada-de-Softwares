package com.venturaHR.services.security;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CriptografiaSenha {

    private final String PASSCHAR = "momo";

    public String criptografarSenha(String textoOriginal){
        BasicTextEncryptor encriptador = new BasicTextEncryptor();
        encriptador.setPasswordCharArray(PASSCHAR.toCharArray());
        return encriptador.encrypt(textoOriginal);
    }

    public Boolean checarSenha(String senhaPayload, String senhaBanco){
        BasicTextEncryptor encriptador = new BasicTextEncryptor();
        encriptador.setPasswordCharArray(PASSCHAR.toCharArray());
        if (senhaPayload == encriptador.decrypt(senhaBanco)){
            return true;
        }
        return false;
    }
}
