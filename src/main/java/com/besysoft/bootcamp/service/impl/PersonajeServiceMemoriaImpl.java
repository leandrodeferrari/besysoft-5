package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.Personaje;
import com.besysoft.bootcamp.dto.mapper.IPersonajeMapper;
import com.besysoft.bootcamp.dto.request.PersonajeInDto;
import com.besysoft.bootcamp.dto.response.PersonajeOutDto;
import com.besysoft.bootcamp.repository.memory.IPersonajeRepository;
import com.besysoft.bootcamp.service.IPersonajeService;
import com.besysoft.bootcamp.util.PersonajeUtil;
import com.besysoft.bootcamp.util.ValidacionGeneralUtil;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "memory")
@Service
public class PersonajeServiceMemoriaImpl implements IPersonajeService {

    private final IPersonajeMapper personajeMapper;
    private final IPersonajeRepository personajeRepository;

    public PersonajeServiceMemoriaImpl(IPersonajeMapper personajeMapper,
                                       IPersonajeRepository personajeRepository) {
        this.personajeMapper = personajeMapper;
        this.personajeRepository = personajeRepository;
    }

    @Override
    public List<PersonajeOutDto> buscarPorFiltros(String nombre, Byte edad) {
        return this.personajeRepository.buscarPorFiltros(nombre, edad)
                .stream()
                .map(this.personajeMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonajeOutDto> buscarPorEdades(Byte desde, Byte hasta) {

        PersonajeUtil.validarEdad(desde);
        PersonajeUtil.validarEdad(hasta);
        ValidacionGeneralUtil.validarRangoDeNumeros(desde, hasta);

        return this.personajeRepository.buscarPorEdades(desde, hasta)
                .stream()
                .map(this.personajeMapper::mapToDto)
                .collect(Collectors.toList());

    }

    @Override
    public PersonajeOutDto crear(PersonajeInDto dto) {

        PersonajeUtil.validarDto(dto);

        Personaje personaje = this.personajeMapper.mapToEntity(dto);

        return this.personajeMapper.mapToDto(this.personajeRepository.crear(personaje));

    }

    @Override
    public PersonajeOutDto actualizar(Long id, PersonajeInDto dto) {

        ValidacionGeneralUtil.validarId(id);
        PersonajeUtil.validarDto(dto);

        if(!this.personajeRepository.existePorId(id)){
            throw new IllegalArgumentException("No existe personaje con ese ID.");
        }

        Personaje personaje = this.personajeMapper.mapToEntity(dto);
        personaje.setId(id);

        return this.personajeMapper.mapToDto(this.personajeRepository.actualizar(id, personaje));

    }

}
