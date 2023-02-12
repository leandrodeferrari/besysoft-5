package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.repository.memory.IGeneroRepository;
import com.besysoft.bootcamp.service.IGeneroService;
import com.besysoft.bootcamp.util.GeneroUtil;
import com.besysoft.bootcamp.util.ValidacionGeneralUtil;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "memory")
@Service
public class GeneroServiceMemoriaImpl implements IGeneroService {

    private final IGeneroRepository generoRepository;

    public GeneroServiceMemoriaImpl(IGeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @Override
    public List<Genero> obtenerTodos() {
        return this.generoRepository.obtenerTodos();
    }

    @Override
    public Genero crear(Genero genero) {

        GeneroUtil.validarNombre(genero.getNombre());

        if(this.generoRepository.existePorNombre(genero.getNombre())){
            throw new IllegalArgumentException("El genero ya existe.");
        }

        return this.generoRepository.crear(genero);

    }

    @Override
    public Genero actualizar(Long id, Genero genero) {

        ValidacionGeneralUtil.validarId(id);
        GeneroUtil.validarNombre(genero.getNombre());
        genero.setId(id);

        if(this.generoRepository.existePorNombre(genero.getNombre())){
            throw new IllegalArgumentException("Ya existe un genero con ese nombre.");
        }

        if(!this.generoRepository.existePorId(id)){
            throw new IllegalArgumentException("No existe genero con ese ID.");
        }

        return this.generoRepository.actualizar(id, genero);

    }

    @Override
    public Optional<Genero> buscarPorNombre(String nombre) {
        return this.generoRepository.buscarPorNombre(nombre);
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return this.generoRepository.existePorNombre(nombre);
    }

}
