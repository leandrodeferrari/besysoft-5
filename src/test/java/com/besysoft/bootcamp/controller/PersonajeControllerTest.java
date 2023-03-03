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
        //GIVEN


        //WHEN


        //THEN

    }

    @Test
    void buscarPorEdades() {
        //GIVEN


        //WHEN


        //THEN

    }

    @Test
    void crear() {
        //GIVEN


        //WHEN


        //THEN

    }

    @Test
    void actualizar() {
        //GIVEN


        //WHEN


        //THEN

    }

}