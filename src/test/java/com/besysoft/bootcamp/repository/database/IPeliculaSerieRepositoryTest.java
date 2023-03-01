package com.besysoft.bootcamp.repository.database;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.domain.PeliculaSerie;
import com.besysoft.bootcamp.util.GeneroTestUtil;
import com.besysoft.bootcamp.util.PeliculaSerieTestUtil;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IPeliculaSerieRepositoryTest {

    @Autowired
    IGeneroRepository generoRepository;

    @Autowired
    IPeliculaSerieRepository peliculaSerieRepository;

    @BeforeEach
    void setUp() {
        Genero genero1 = generoRepository.save(GeneroTestUtil.genero1);
        Genero genero2 = generoRepository.save(GeneroTestUtil.genero2);
        Genero genero3 = generoRepository.save(GeneroTestUtil.genero3);
        Genero genero4 = generoRepository.save(GeneroTestUtil.genero4);

        this.peliculaSerieRepository.save(new PeliculaSerie("Chucky", LocalDate.parse("2004-10-10"), (byte) 5, genero1,null));
        this.peliculaSerieRepository.save(new PeliculaSerie("Annabelle", LocalDate.parse("2006-01-10"), (byte) 2, genero1,null));
        this.peliculaSerieRepository.save(new PeliculaSerie("Jaula", LocalDate.parse("2021-02-12"), (byte) 2, genero2,null));
        this.peliculaSerieRepository.save(new PeliculaSerie("Culpable", LocalDate.parse("2020-07-11"), (byte) 4, genero4,null));
    }

    @AfterEach
    void tearDown() {
        this.peliculaSerieRepository.deleteAll();
    }

    @Test
    void findAll() {
        //GIVEN
        // Ya el metodo setUp() me genera el contexto necesario para este test.

        //WHEN
        List<PeliculaSerie> peliculasSeries = this.peliculaSerieRepository.findAll();

        //THEN
        assertFalse(peliculasSeries.isEmpty());
        assertEquals(4, peliculasSeries.size());
    }

    @Test
    void save() {
        //GIVEN
        Genero genero = this.generoRepository.save(GeneroTestUtil.genero6);
        PeliculaSerie esperado = PeliculaSerieTestUtil.peliculaSerie5;
        esperado.setGenero(genero);

        //WHEN
        PeliculaSerie actual = this.peliculaSerieRepository.save(esperado);

        //THEN
        assertEquals(esperado.getTitulo(), actual.getTitulo());
        assertEquals(esperado.getFechaDeCreacion(), actual.getFechaDeCreacion());
        assertEquals(esperado.getCalificacion(), actual.getCalificacion());
        assertEquals(esperado.getGenero(), actual.getGenero());
    }

    @Test
    void update() {
        //GIVEN
        Genero genero = this.generoRepository.save(GeneroTestUtil.genero6);
        PeliculaSerie peliculaSerie = this.peliculaSerieRepository.save(
                new PeliculaSerie
                        ("Tiburon", LocalDate.parse("2023-01-01"), (byte) 4, genero, null)
        );
        Long id = peliculaSerie.getId();
        PeliculaSerie esperado = PeliculaSerieTestUtil.peliculaSerie6;
        esperado.setGenero(genero);
        esperado.setId(id);

        //WHEN
        PeliculaSerie actual = this.peliculaSerieRepository.save(esperado);

        //THEN
        assertEquals(esperado.getId(), actual.getId());
        assertEquals(esperado.getTitulo(), actual.getTitulo());
        assertEquals(esperado.getFechaDeCreacion(), actual.getFechaDeCreacion());
        assertEquals(esperado.getCalificacion(), actual.getCalificacion());
        assertEquals(esperado.getGenero(), actual.getGenero());
    }

    @Test
    void existsById() {
        //GIVEN
        Genero genero = this.generoRepository.save(GeneroTestUtil.genero6);
        PeliculaSerie peliculaSerie = PeliculaSerieTestUtil.peliculaSerie6;
        peliculaSerie.setGenero(genero);
        PeliculaSerie esperado = this.peliculaSerieRepository.save(peliculaSerie);
        Long id = esperado.getId();

        //WHEN
        boolean existePorId = this.peliculaSerieRepository.existsById(id);

        //THEN
        assertTrue(existePorId);
    }

    @Test
    void findAllByGenero() {
        //GIVEN
        String nombreGenero = GeneroTestUtil.genero1.getNombre();
        Genero genero = this.generoRepository.findByNombre(nombreGenero).orElseThrow();
        List<PeliculaSerie> esperado = this.peliculaSerieRepository.findAll()
                .stream()
                .filter(p -> p.getGenero().equals(genero))
                .collect(Collectors.toList());

        //WHEN
        List<PeliculaSerie> actual = this.peliculaSerieRepository
                .findAllByGenero(genero);

        //THEN
        assertEquals(esperado, actual);
    }

    @Test
    void findByTitulo() {
        //GIVEN
        String titulo = PeliculaSerieTestUtil.peliculaSerie3.getTitulo();
        PeliculaSerie esperado = this.peliculaSerieRepository
                .findAll()
                .stream()
                .filter(p -> p.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElseThrow();

        //WHEN
        Optional<PeliculaSerie> actual = this.peliculaSerieRepository.findByTitulo(titulo);

        //THEN
        assertTrue(actual.isPresent());
        assertEquals(esperado.getId(), actual.get().getId());
        assertEquals(esperado.getTitulo(), actual.get().getTitulo());
        assertEquals(esperado.getCalificacion(), actual.get().getCalificacion());
        assertEquals(esperado.getGenero(), actual.get().getGenero());
        assertEquals(esperado.getFechaDeCreacion(), actual.get().getFechaDeCreacion());
    }

    @Test
    void findAllByTituloAndGenero() {
        //GIVEN
        String titulo = PeliculaSerieTestUtil.peliculaSerie3.getTitulo();;
        String nombreGenero = GeneroTestUtil.genero2.getNombre();
        Genero genero = this.generoRepository.findByNombre(nombreGenero).orElseThrow();
        List<PeliculaSerie> esperado = this.peliculaSerieRepository
                .findAll()
                .stream().filter(p -> p.getTitulo().equalsIgnoreCase(titulo) && p.getGenero().equals(genero))
                .collect(Collectors.toList());

        //WHEN
        List<PeliculaSerie> actual = this.peliculaSerieRepository
                .findAllByTituloAndGenero(titulo, genero);

        //THEN
        assertFalse(actual.isEmpty());
        assertEquals(esperado, actual);
    }

    @Test
    void findAllByFechaDeCreacionBetween() {
        //GIVEN
        LocalDate desde = PeliculaSerieTestUtil.DESDE_LOCAL_DATE;
        LocalDate hasta = PeliculaSerieTestUtil.HASTA_LOCAL_DATE;
        List<PeliculaSerie> esperado = this.peliculaSerieRepository
                .findAll()
                .stream()
                .filter(ps -> ps.getFechaDeCreacion().isAfter(desde.minusDays(1)) && ps.getFechaDeCreacion().isBefore(hasta.plusDays(1)))
                .collect(Collectors.toList());

        //WHEN
        List<PeliculaSerie> actual = this.peliculaSerieRepository
                .findAllByFechaDeCreacionBetween(desde, hasta);

        //THEN
        assertFalse(actual.isEmpty());
        assertEquals(esperado, actual);
    }

    @Test
    void findAllByCalificacionBetween() {
        //GIVEN
        Byte desde = PeliculaSerieTestUtil.DESDE_BYTE;
        Byte hasta = PeliculaSerieTestUtil.HASTA_BYTE;
        List<PeliculaSerie> esperado = this.peliculaSerieRepository
                .findAll()
                .stream()
                .filter(ps -> ps.getCalificacion() >= desde && ps.getCalificacion() <= hasta)
                .collect(Collectors.toList());

        //WHEN
        List<PeliculaSerie> actual = this.peliculaSerieRepository
                .findAllByCalificacionBetween(desde, hasta);

        //THEN
        assertFalse(actual.isEmpty());
        assertEquals(esperado, actual);
    }

    @Test
    void existsByTitulo() {
        //GIVEN
        Genero genero = this.generoRepository.save(GeneroTestUtil.genero6);
        PeliculaSerie peliculaSerie = PeliculaSerieTestUtil.peliculaSerie6;
        peliculaSerie.setGenero(genero);
        PeliculaSerie esperado = this.peliculaSerieRepository.save(peliculaSerie);
        String titulo = esperado.getTitulo();

        //WHEN
        boolean existePorTitulo = this.peliculaSerieRepository.existsByTitulo(titulo);

        //THEN
        assertTrue(existePorTitulo);
    }

}