package com.besysoft.bootcamp.repository.database;

import com.besysoft.bootcamp.domain.Genero;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IGeneroRepositoryTest {

    @Autowired
    private IGeneroRepository generoRepository;

    @BeforeEach
    void setUp() {
        generoRepository.save(new Genero("Terror"));
        generoRepository.save(new Genero("Suspenso"));
        generoRepository.save(new Genero("Romance"));
        generoRepository.save(new Genero("Policial"));
    }

    @AfterEach
    void tearDown() {
        this.generoRepository.deleteAll();
    }

    @Test
    void findAll() {
        //GIVEN
        // Ya el metodo setUp() me genera el contexto necesario para este test.

        //WHEN
        List<Genero> generos = this.generoRepository.findAll();

        //THEN
        assertFalse(generos.isEmpty());
        assertEquals(4, generos.size());
    }

    @Test
    @Disabled
    void save() {
        //GIVEN
        Genero genero = new Genero("Comedia");

        //WHEN
        Genero generoCreado = this.generoRepository.save(genero);

        //THEN
        assertEquals(genero, generoCreado);
    }

    @Test
    void update() {
        //GIVEN
        Genero genero = this.generoRepository.save(new Genero("Comedia"));
        Long id = genero.getId();
        Genero generoDeEntrada = new Genero(id, "Ciencia ficcion");

        //WHEN
        Genero generoActualizado = this.generoRepository.save(generoDeEntrada);

        //THEN
        assertEquals(id, generoActualizado.getId());
        assertEquals(generoDeEntrada.getNombre(), generoActualizado.getNombre());
    }

    @Test
    void findById() {
        //GIVEN
        List<Genero> generos = this.generoRepository.findAll();
        Genero genero = generos.get(0);
        Long id = genero.getId();

        //WHEN
        Optional<Genero> generoEncontrado = this.generoRepository.findById(id);

        //THEN
        assertTrue(generoEncontrado.isPresent());
        assertEquals(genero.getId(), generoEncontrado.get().getId());
        assertEquals(genero.getNombre(), generoEncontrado.get().getNombre());
    }

    @Test
    void existsById() {
        //GIVEN
        List<Genero> generos = this.generoRepository.findAll();
        Long id = generos.get(0).getId();

        //WHEN
        boolean existeGeneroPorId = this.generoRepository.existsById(id);

        //THEN
        assertTrue(existeGeneroPorId);
    }

    @Test
    void findByNombre() {
        //GIVEN
        String nombre = "Terror";

        //WHEN
        Optional<Genero> optionalGenero = this.generoRepository.findByNombre(nombre);

        //THEN
        assertTrue(optionalGenero.isPresent());
        assertEquals(nombre, optionalGenero.get().getNombre());
    }

    @Test
    void existsByNombre() {
        //GIVEN
        String nombre = "Terror";

        //WHEN
        boolean existsNombre = this.generoRepository.existsByNombre(nombre);

        //THEN
        assertTrue(existsNombre);
    }

}