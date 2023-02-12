package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.Personaje;
import com.besysoft.bootcamp.repository.memory.IPersonajeRepository;
import com.besysoft.bootcamp.service.IPersonajeService;
import com.besysoft.bootcamp.util.PersonajeUtil;
import com.besysoft.bootcamp.util.ValidacionGeneralUtil;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "memory")
@Service
public class PersonajeServiceMemoriaImpl implements IPersonajeService {

    private final IPersonajeRepository personajeRepository;

    public PersonajeServiceMemoriaImpl(IPersonajeRepository personajeRepository) {
        this.personajeRepository = personajeRepository;
    }

    @Override
    public List<Personaje> buscarPorFiltros(String nombre, Byte edad) {
        return this.personajeRepository.buscarPorFiltros(nombre, edad);
    }

    @Override
    public List<Personaje> buscarPorEdades(Byte desde, Byte hasta) {

        PersonajeUtil.validarEdad(desde);
        PersonajeUtil.validarEdad(hasta);
        ValidacionGeneralUtil.validarRangoDeNumeros(desde, hasta);

        return this.personajeRepository.buscarPorEdades(desde, hasta);

    }

    @Override
    public Personaje crear(Personaje personaje) {

        PersonajeUtil.validar(personaje);

        return this.personajeRepository.crear(personaje);

    }

    @Override
    public Personaje actualizar(Long id, Personaje personaje) {

        ValidacionGeneralUtil.validarId(id);
        PersonajeUtil.validar(personaje);
        personaje.setId(id);

        if(!this.personajeRepository.existePorId(id)){
            throw new IllegalArgumentException("No existe personaje con ese ID.");
        }

        return this.personajeRepository.actualizar(id, personaje);

    }

}
