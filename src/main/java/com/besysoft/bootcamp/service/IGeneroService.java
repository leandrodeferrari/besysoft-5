package com.besysoft.bootcamp.service;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.dto.request.GeneroInDto;
import com.besysoft.bootcamp.dto.response.GeneroOutDto;

import java.util.List;
import java.util.Optional;

public interface IGeneroService {

    List<GeneroOutDto> obtenerTodos();
    GeneroOutDto crear(GeneroInDto dto);
    GeneroOutDto actualizar(Long id, GeneroInDto genero);
    Optional<Genero> buscarPorNombre(String nombre);
    boolean existePorNombre(String nombre);

}
