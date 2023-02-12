package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.repository.database.IGeneroRepository;
import com.besysoft.bootcamp.service.IGeneroService;
import com.besysoft.bootcamp.util.GeneroUtil;
import com.besysoft.bootcamp.util.ValidacionGeneralUtil;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "database")
@Service
public class GeneroServiceImpl implements IGeneroService {

    private final IGeneroRepository generoRepository;

    public GeneroServiceImpl(IGeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genero> obtenerTodos() {
        return this.generoRepository.findAll();
    }

    @Transactional(readOnly = false)
    @Override
    public Genero crear(Genero genero) {

        GeneroUtil.validarNombre(genero.getNombre());
        genero.setId(null);

        if(this.generoRepository.existsByNombre(genero.getNombre())){
            throw new IllegalArgumentException("El genero ya existe.");
        }

        return this.generoRepository.save(genero);

    }

    @Transactional(readOnly = false)
    @Override
    public Genero actualizar(Long id, Genero genero) {

        ValidacionGeneralUtil.validarId(id);
        GeneroUtil.validarNombre(genero.getNombre());
        genero.setId(id);

        if(this.generoRepository.existsByNombre(genero.getNombre())){
            throw new IllegalArgumentException("Ya existe un genero con ese nombre.");
        }

        if(!this.generoRepository.existsById(id)){
            throw new IllegalArgumentException("No existe genero con ese ID.");
        }

        return this.generoRepository.save(genero);

    }

    @Transactional(readOnly = true)
    @Override
    public  Optional<Genero> buscarPorNombre(String nombre) {
        return this.generoRepository.findByNombre(nombre);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existePorNombre(String nombre) {
        return this.generoRepository.existsByNombre(nombre);
    }

}
