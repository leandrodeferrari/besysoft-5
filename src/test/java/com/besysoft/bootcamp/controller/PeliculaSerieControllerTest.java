package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.service.IPeliculaSerieService;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(PeliculaSerieController.class)
class PeliculaSerieControllerTest {

    @MockBean
    private IPeliculaSerieService peliculaSerieService;

    @Test
    void buscarPorFiltros() {
        //GIVEN


        //WHEN


        //THEN

    }

    @Test
    void buscarPorFechas() {
        //GIVEN


        //WHEN


        //THEN

    }

    @Test
    void buscarPorCalificaciones() {
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