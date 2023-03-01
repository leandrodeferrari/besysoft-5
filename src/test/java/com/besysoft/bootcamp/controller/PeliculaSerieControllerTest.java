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
    }

    @Test
    void buscarPorFechas() {
    }

    @Test
    void buscarPorCalificaciones() {
    }

    @Test
    void crear() {
    }

    @Test
    void actualizar() {
    }

}