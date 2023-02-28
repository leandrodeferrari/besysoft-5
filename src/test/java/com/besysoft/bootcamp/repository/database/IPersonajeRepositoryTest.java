package com.besysoft.bootcamp.repository.database;

import com.besysoft.bootcamp.domain.Personaje;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
        this.personajeRepository.save(new Personaje("Jacqueline", (byte) 26, 55.7D, "Es una actriz canadiense. Protagonizó la serie Salvation de CBS.", null));
        this.personajeRepository.save(new Personaje("Vera", (byte) 86, 70.0D, "Supermodelo que enamoró a Coco Chanel y ahora ha conquistado a Paco Plaza.", null));
        this.personajeRepository.save(new Personaje("Christian", (byte) 26, 79.5D, "Es actor, escritor, director, productor y músico. Trabaja en el teatro, peliculas y televisión.", null));
        this.personajeRepository.save(new Personaje("Vera", (byte) 48, 90.2D, "Es actor, director y guionista australiano conocido por haber participado en la serie televisiva Teh secret life of us.", null));
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
    @Disabled
    void save() {
        //GIVEN
        Personaje personaje = new Personaje
                ("Leandro", (byte) 30, 80.0D, "Actor y guionista.", null);

        // WHEN
        Personaje personajeCreado = this.personajeRepository.save(personaje);

        //THEN
        assertEquals(personaje, personajeCreado);
    }

    @Test
    void update() {
        //GIVEN
        Personaje personaje = this.personajeRepository.save(new Personaje
                ("Leandro", (byte) 30, 80.0D, "Actor y guionista.", null));
        Long id = personaje.getId();
        Personaje personajeDeEntrada = new Personaje
                (id, "Roberto", (byte) 50, 67.9D, "Actor, musico y director.", null);

        // WHEN
        Personaje personajeActualizado = this.personajeRepository.save(personajeDeEntrada);

        //THEN
        assertEquals(id, personajeActualizado.getId());
        assertEquals(personajeDeEntrada.getNombre(), personajeActualizado.getNombre());
        assertEquals(personajeDeEntrada.getEdad(), personajeActualizado.getEdad());
        assertEquals(personajeDeEntrada.getPeso(), personajeActualizado.getPeso());
        assertEquals(personajeDeEntrada.getHistoria(), personajeActualizado.getHistoria());
    }

    @Test
    void existsById() {
        //GIVEN
        List<Personaje> personajes = this.personajeRepository.findAll();
        Long id = personajes.get(0).getId();

        // WHEN
        boolean existePersonajePorId = this.personajeRepository.existsById(id);

        //THEN
        assertTrue(existePersonajePorId);
    }

    @Test
    void findAllByNombre() {
        //GIVEN
        String nombre = "Vera";
        List<Personaje> personajes = this.personajeRepository.findAll()
                .stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());

        // WHEN
        List<Personaje> personajesPorNombre = this.personajeRepository.findAllByNombre(nombre);

        //THEN
        assertEquals(personajes, personajesPorNombre);
    }

    @Test
    void findAllByEdad() {
        //GIVEN
        Byte edad = (byte) 26;
        List<Personaje> personajes = this.personajeRepository.findAll()
                .stream()
                .filter(p -> p.getEdad().equals(edad))
                .collect(Collectors.toList());

        // WHEN
        List<Personaje> personajesPorEdad = this.personajeRepository.findAllByEdad(edad);

        //THEN
        assertEquals(personajes, personajesPorEdad);
    }

    @Test
    void findAllByNombreAndEdad() {
        //GIVEN
        String nombre = "Jacqueline";
        Byte edad = (byte) 26;
        List<Personaje> personajes = this.personajeRepository.findAll()
                .stream()
                .filter(p -> p.getEdad().equals(edad) && p.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());

        // WHEN
        List<Personaje> personajesPorNombreYEdad = this.personajeRepository
                .findAllByNombreAndEdad(nombre, edad);

        //THEN
        assertEquals(personajes, personajesPorNombreYEdad);
    }

    @Test
    void findAllByEdadBetween() {
        //GIVEN
        Byte desde = (byte) 40;
        Byte hasta = (byte) 90;
        List<Personaje> personajes = this.personajeRepository.findAll()
                .stream()
                .filter(p -> p.getEdad() >= desde && p.getEdad() <= hasta)
                .collect(Collectors.toList());

        // WHEN
        List<Personaje> personajesDesdeHasta = this.personajeRepository.findAllByEdadBetween(desde, hasta);

        //THEN
        assertEquals(personajes, personajesDesdeHasta);
    }

}