package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.service.IGeneroService;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(GeneroController.class)
class GeneroControllerTest {

    @MockBean
    private IGeneroService generoService;

    @Test
    void obtenerTodos() {
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