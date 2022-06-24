package com.venturaHR.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CandidatoController.class)
@ExtendWith(SpringExtension.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void cadastro() throws Exception {
        RequestBuilder builder = MockMvcRequestBuilders.get("/candidato/pegarTodasVagas");
        MvcResult result = mvc.perform(builder).andReturn();
        int vagas =  result.getResponse().getContentLength();
        assertTrue(vagas != 0);
    }
}