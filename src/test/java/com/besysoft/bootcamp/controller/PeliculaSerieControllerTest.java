package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.dto.request.PeliculaSerieInDto;
import com.besysoft.bootcamp.dto.response.PeliculaSerieOutDto;
import com.besysoft.bootcamp.service.IPeliculaSerieService;
import com.besysoft.bootcamp.util.PeliculaSerieTestUtil;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PeliculaSerieController.class)
class PeliculaSerieControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IPeliculaSerieService peliculaSerieService;

    private ObjectMapper objectMapper;
    private String url;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
        this.url = "/peliculas-series";
    }

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
    void crear() throws Exception {
        //GIVEN
        PeliculaSerieInDto dto = PeliculaSerieTestUtil.PELICULA_SERIE_IN_DTO;
        PeliculaSerieOutDto esperado = PeliculaSerieTestUtil.PELICULA_SERIE_OUT_DTO;
        when(this.peliculaSerieService.crear(any(PeliculaSerieInDto.class))).thenReturn(esperado);

        //WHEN
        this.mvc.perform(post(this.url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(dto)))
                //THEN
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value(esperado.getTitulo()))
                .andExpect(jsonPath("$.calificacion").value(esperado.getCalificacion().toString()))
                .andExpect(jsonPath("$.nombreGenero").value(esperado.getNombreGenero()))
                .andExpect(jsonPath("$.fechaDeCreacion").value(esperado.getFechaDeCreacion()));
        verify(this.peliculaSerieService).crear(any(PeliculaSerieInDto.class));
    }

    @Test
    void actualizar() throws Exception {
        //GIVEN
        PeliculaSerieInDto dto = PeliculaSerieTestUtil.PELICULA_SERIE_IN_DTO;
        PeliculaSerieOutDto esperado = PeliculaSerieTestUtil.PELICULA_SERIE_OUT_DTO;
        when(this.peliculaSerieService.actualizar(anyLong(), any(PeliculaSerieInDto.class))).thenReturn(esperado);

        //WHEN
        this.mvc.perform(put(this.url.concat("/1"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(dto)))
                //THEN
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value(esperado.getTitulo()))
                .andExpect(jsonPath("$.calificacion").value(esperado.getCalificacion().toString()))
                .andExpect(jsonPath("$.nombreGenero").value(esperado.getNombreGenero()))
                .andExpect(jsonPath("$.fechaDeCreacion").value(esperado.getFechaDeCreacion()));
        verify(this.peliculaSerieService).actualizar(anyLong(), any(PeliculaSerieInDto.class));
    }

}