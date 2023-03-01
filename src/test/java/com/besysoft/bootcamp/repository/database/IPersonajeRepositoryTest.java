package com.besysoft.bootcamp.repository.database;

import com.besysoft.bootcamp.domain.Personaje;

import com.besysoft.bootcamp.util.PersonajeTestUtil;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IPersonajeRepositoryTest {

    @Autowired
    IPersonajeRepository personajeRepository;

    @BeforeEach
    void setUp() {
        this.personajeRepository.save(PersonajeTestUtil.personaje1);
        this.personajeRepository.save(PersonajeTestUtil.personaje2);
        this.personajeRepository.save(PersonajeTestUtil.personaje3);
        this.personajeRepository.save(PersonajeTestUtil.personaje4);
    }

    @AfterEach
    void tearDown() {
        this.personajeRepository.deleteAll();
    }

    @Test
    void findAll() {
        //GIVEN
        // Ya el metodo setUp() me genera el contexto necesario para este test.

        // WHEN
        List<Personaje> personajes = this.personajeRepository.findAll();

        //THEN
        assertFalse(personajes.isEmpty());
        assertEquals(4, personajes.size());
    }

    @Test
    void save() {
        //GIVEN
        Personaje esperado = PersonajeTestUtil.personaje5;

        // WHEN
        Personaje actual = this.personajeRepository.save(esperado);

        //THEN
        assertEquals(esperado.getNombre(), actual.getNombre());
        assertEquals(esperado.getEdad(), actual.getEdad());
        assertEquals(esperado.getPeso(), actual.getPeso());
        assertEquals(esperado.getHistoria(), actual.getHistoria());
    }

    @Test
    void update() {
        //GIVEN
        Personaje personaje = this.personajeRepository.save(PersonajeTestUtil.personaje5);
        Long id = personaje.getId();
        Personaje esperado = PersonajeTestUtil.personaje6;
        esperado.setId(id);

        // WHEN
        Personaje actual = this.personajeRepository.save(esperado);

        //THEN
        assertEquals(esperado.getId(), actual.getId());
        assertEquals(esperado.getNombre(), actual.getNombre());
        assertEquals(esperado.getEdad(), actual.getEdad());
        assertEquals(esperado.getPeso(), actual.getPeso());
        assertEquals(esperado.getHistoria(), actual.getHistoria());
    }

    @Test
    void existsById() {
        //GIVEN
        Personaje esperado = this.personajeRepository.save(PersonajeTestUtil.personaje5);
        Long id = esperado.getId();

        // WHEN
        boolean existePorId = this.personajeRepository.existsById(id);

        //THEN
        assertTrue(existePorId);
    }

    @Test
    void findAllByNombre() {
        //GIVEN
        String nombre = PersonajeTestUtil.NOMBRE2;
        List<Personaje> esperado = this.personajeRepository.findAll()
                .stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());

        // WHEN
        List<Personaje> actual = this.personajeRepository.findAllByNombre(nombre);

        //THEN
        assertEquals(esperado, actual);
    }

    @Test
    void findAllByEdad() {
        //GIVEN
        Byte edad = PersonajeTestUtil.EDAD;
        List<Personaje> esperado = this.personajeRepository.findAll()
                .stream()
                .filter(p -> p.getEdad().equals(edad))
                .collect(Collectors.toList());

        // WHEN
        List<Personaje> actual = this.personajeRepository.findAllByEdad(edad);

        //THEN
        assertEquals(esperado, actual);
    }

    @Test
    void findAllByNombreAndEdad() {
        //GIVEN
        String nombre = PersonajeTestUtil.NOMBRE1;
        Byte edad = PersonajeTestUtil.EDAD;
        List<Personaje> esperado = this.personajeRepository.findAll()
                .stream()
                .filter(p -> p.getEdad().equals(edad) && p.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());

        // WHEN
        List<Personaje> actual = this.personajeRepository
                .findAllByNombreAndEdad(nombre, edad);

        //THEN
        assertEquals(esperado, actual);
    }

    @Test
    void findAllByEdadBetween() {
        //GIVEN
        Byte desde = PersonajeTestUtil.DESDE;
        Byte hasta = PersonajeTestUtil.HASTA;
        List<Personaje> esperado = this.personajeRepository.findAll()
                .stream()
                .filter(p -> p.getEdad() >= desde && p.getEdad() <= hasta)
                .collect(Collectors.toList());

        // WHEN
        List<Personaje> actual = this.personajeRepository.findAllByEdadBetween(desde, hasta);

        //THEN
        assertEquals(esperado, actual);
    }

}