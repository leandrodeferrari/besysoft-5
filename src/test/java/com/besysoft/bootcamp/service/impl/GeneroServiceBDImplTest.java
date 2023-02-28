package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.dto.mapper.IGeneroMapper;
import com.besysoft.bootcamp.dto.response.GeneroOutDto;
import com.besysoft.bootcamp.repository.database.IGeneroRepository;
import com.besysoft.bootcamp.util.MemoriaUtil;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class GeneroServiceBDImplTest {

    @MockBean
    private IGeneroRepository generoRepository;

    @MockBean
    private IGeneroMapper generoMapper;

    @Autowired
    GeneroServiceBDImpl generoServiceBD;

    @Test
    void obtenerTodos() {
        when(this.generoRepository.findAll()).thenReturn(MemoriaUtil.generarGeneros());
        when(this.generoMapper.mapToDto(any(Genero.class))).thenReturn(any(GeneroOutDto.class));

        List<GeneroOutDto> generosDto = this.generoServiceBD.obtenerTodos();


        assertFalse(generosDto.isEmpty());

    }

    @Test
    void crear() {
    }

    @Test
    void actualizar() {
    }

    @Test
    void buscarPorNombre() {
    }

    @Test
    void buscarPorId() {
    }

    @Test
    void existePorNombre() {
    }

    @Test
    void existePorId() {
    }

}