package com.CleitonH.triagem_hospitalar.controller;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.CleitonH.triagem_hospitalar.repository.PacienteRepository;

@WebMvcTest(PacienteController.class)
public class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PacienteRepository pacienteRepository;

    @Test
    public void testGetTodosPacientes_DeveRetornarStatus200() throws Exception {
        // Simula que o repositório retorna uma lista vazia
        when(pacienteRepository.findAll()).thenReturn(Collections.emptyList());

        // Realiza a requisição e verifica o status
        mockMvc.perform(get("/api/pacientes"))
                .andExpect(status().isOk());
    }
}
