package com.venturaHR.ServiceImpl.security;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CriptografiaSenha {

    private final String PASSCHAR = "momo";

    public String criptografarSenha(String textoOriginal){
        StandardPBEStringEncryptor encriptador = new StandardPBEStringEncryptor();
        encriptador.setPasswordCharArray(PASSCHAR.toCharArray());
        return encriptador.encrypt(textoOriginal);
    }

    public Boolean checarSenha(String senhaPayload, String senhaBanco){
//         encryptor = new StandardPBEStringEncryptor();
        StandardPBEStringEncryptor encriptador = new StandardPBEStringEncryptor();
        encriptador.setPasswordCharArray(PASSCHAR.toCharArray());
        String senhaAberta = encriptador.decrypt(senhaBanco);
        return senhaPayload.equals(senhaAberta);
    }
}
