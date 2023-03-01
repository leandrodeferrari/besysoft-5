package com.besysoft.bootcamp.repository.database;

import com.besysoft.bootcamp.domain.Genero;

import com.besysoft.bootcamp.util.GeneroTestUtil;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
        generoRepository.save(GeneroTestUtil.genero1);
        generoRepository.save(GeneroTestUtil.genero2);
        generoRepository.save(GeneroTestUtil.genero3);
        generoRepository.save(GeneroTestUtil.genero4);
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
    void save() {
        //GIVEN
        Genero esperado = GeneroTestUtil.genero5;

        //WHEN
        Genero actual = this.generoRepository.save(esperado);

        //THEN
        assertEquals(esperado.getNombre(), actual.getNombre());
    }

    @Test
    void update() {
        //GIVEN
        Genero genero = this.generoRepository.save(GeneroTestUtil.genero5);
        Long id = genero.getId();
        Genero esperado = GeneroTestUtil.genero6;
        esperado.setId(id);

        //WHEN
        Genero actual = this.generoRepository.save(esperado);

        //THEN
        assertEquals(esperado.getId(), actual.getId());
        assertEquals(esperado.getNombre(), actual.getNombre());
    }

    @Test
    void findById() {
        //GIVEN
        Genero esperado = this.generoRepository.save(GeneroTestUtil.genero5);
        Long id = esperado.getId();

        //WHEN
        Optional<Genero> actual = this.generoRepository.findById(id);

        //THEN
        assertTrue(actual.isPresent());
        assertEquals(esperado.getId(), actual.get().getId());
        assertEquals(esperado.getNombre(), actual.get().getNombre());
    }

    @Test
    void existsById() {
        //GIVEN
        Genero esperado = this.generoRepository.save(GeneroTestUtil.genero5);
        Long id = esperado.getId();

        //WHEN
        boolean existePorId = this.generoRepository.existsById(id);

        //THEN
        assertTrue(existePorId);
    }

    @Test
    void findByNombre() {
        //GIVEN
        Genero esperado = this.generoRepository.save(GeneroTestUtil.genero5);
        String nombre = esperado.getNombre();

        //WHEN
        Optional<Genero> actual = this.generoRepository.findByNombre(nombre);

        //THEN
        assertTrue(actual.isPresent());
        assertEquals(esperado.getId(), actual.get().getId());
        assertEquals(esperado.getNombre(), actual.get().getNombre());
    }

    @Test
    void existsByNombre() {
        //GIVEN
        Genero genero = this.generoRepository.save(GeneroTestUtil.genero5);
        String nombre = genero.getNombre();

        //WHEN
        boolean existePorNombre = this.generoRepository.existsByNombre(nombre);

        //THEN
        assertTrue(existePorNombre);
    }

}