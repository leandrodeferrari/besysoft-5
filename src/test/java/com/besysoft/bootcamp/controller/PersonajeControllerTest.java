package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.service.IPersonajeService;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(PersonajeController.class)
class PersonajeControllerTest {

    @MockBean
    private IPersonajeService personajeService;

    @Test
    void buscarPorFiltros() {
    }

    @Test
    void buscarPorEdades() {
    }

    @Test
    void crear() {
    }

    @Test
    void actualizar() {
    }

}