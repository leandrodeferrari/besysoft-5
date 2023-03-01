package com.besysoft.bootcamp.repository.database;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.domain.PeliculaSerie;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
        Genero genero1 = this.generoRepository.save(new Genero("Terror"));
        Genero genero2 = this.generoRepository.save(new Genero("Suspenso"));
        Genero genero3 = this.generoRepository.save(new Genero("Romance"));
        Genero genero4 = this.generoRepository.save(new Genero("Policial"));

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
    @Disabled
    void save() {
        //GIVEN
        Genero genero = this.generoRepository.save(new Genero("Accion"));
        PeliculaSerie peliculaSerie = new PeliculaSerie
                ("Tiburon", LocalDate.parse("2023-01-01"), (byte) 4, genero, null);

        //WHEN
        PeliculaSerie peliculaSerieCreada = this.peliculaSerieRepository.save(peliculaSerie);

        //THEN
        assertEquals(peliculaSerie, peliculaSerieCreada);
    }

    @Test
    void update() {
        //GIVEN
        Genero genero = this.generoRepository.save(new Genero("Accion"));
        PeliculaSerie peliculaSerie = this.peliculaSerieRepository.save(new PeliculaSerie
                ("Tiburon", LocalDate.parse("2023-01-01"), (byte) 4, genero, null));
        Long id = peliculaSerie.getId();
        PeliculaSerie peliculaSerieDeEntrada = new PeliculaSerie
                (id, "Jumanji", LocalDate.parse("2021-11-08"), (byte) 5, genero, null);

        //WHEN
        PeliculaSerie peliculaSerieActualizada = this.peliculaSerieRepository.save(peliculaSerieDeEntrada);

        //THEN
        assertEquals(id, peliculaSerieActualizada.getId());
        assertEquals(peliculaSerieDeEntrada.getTitulo(), peliculaSerieActualizada.getTitulo());
        assertEquals(peliculaSerieDeEntrada.getFechaDeCreacion(), peliculaSerieActualizada.getFechaDeCreacion());
        assertEquals(peliculaSerieDeEntrada.getCalificacion(), peliculaSerieActualizada.getCalificacion());
        assertEquals(peliculaSerieDeEntrada.getGenero(), peliculaSerieActualizada.getGenero());
    }

    @Test
    void existsById() {
        //GIVEN
        List<PeliculaSerie> peliculasSeries = this.peliculaSerieRepository.findAll();
        Long id = peliculasSeries.get(0).getId();

        //WHEN
        boolean existePeliculaSeriePorId = this.peliculaSerieRepository.existsById(id);

        //THEN
        assertTrue(existePeliculaSeriePorId);
    }

    @Test
    void findAllByGenero() {
        //GIVEN
        String nombreGenero = "Terror";
        Genero genero = this.generoRepository.findByNombre(nombreGenero).orElseThrow();
        List<PeliculaSerie> peliculasSeries = this.peliculaSerieRepository.findAll()
                .stream()
                .filter(p -> p.getGenero().equals(genero))
                .collect(Collectors.toList());

        //WHEN
        List<PeliculaSerie> peliculasSeriesPorNombreGenero = this.peliculaSerieRepository
                .findAllByGenero(genero);

        //THEN
        assertEquals(peliculasSeries, peliculasSeriesPorNombreGenero);
    }

    @Test
    void findByTitulo() {
        //GIVEN
        String titulo = "Jaula";
        PeliculaSerie peliculaSerie = this.peliculaSerieRepository
                .findAll()
                .stream()
                .filter(p -> p.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElseThrow();

        //WHEN
        Optional<PeliculaSerie> optionalPeliculaSerie = this.peliculaSerieRepository.findByTitulo(titulo);

        //THEN
        assertTrue(optionalPeliculaSerie.isPresent());
        assertEquals(peliculaSerie.getId(), optionalPeliculaSerie.get().getId());
        assertEquals(peliculaSerie.getTitulo(), optionalPeliculaSerie.get().getTitulo());
        assertEquals(peliculaSerie.getCalificacion(), optionalPeliculaSerie.get().getCalificacion());
        assertEquals(peliculaSerie.getGenero(), optionalPeliculaSerie.get().getGenero());
        assertEquals(peliculaSerie.getFechaDeCreacion(), optionalPeliculaSerie.get().getFechaDeCreacion());
    }

    @Test
    void findAllByTituloAndGenero() {
        //GIVEN
        String titulo = "Jaula";
        Genero genero = this.generoRepository.findByNombre("Suspenso").orElseThrow();
        List<PeliculaSerie> peliculasSeries = this.peliculaSerieRepository
                .findAll()
                .stream().filter(p -> p.getTitulo().equalsIgnoreCase(titulo) && p.getGenero().equals(genero))
                .collect(Collectors.toList());

        //WHEN
        List<PeliculaSerie> peliculasSeriesPorNombreYGenero = this.peliculaSerieRepository
                .findAllByTituloAndGenero(titulo, genero);

        //THEN
        assertFalse(peliculasSeriesPorNombreYGenero.isEmpty());
        assertEquals(peliculasSeries, peliculasSeriesPorNombreYGenero);
    }

    @Test
    void findAllByFechaDeCreacionBetween() {
        //GIVEN
        LocalDate desde = LocalDate.parse("2020-01-01");
        LocalDate hasta = LocalDate.parse("2023-01-01");
        List<PeliculaSerie> peliculasSeries = this.peliculaSerieRepository
                .findAll()
                .stream()
                .filter(ps -> ps.getFechaDeCreacion().isAfter(desde.minusDays(1)) && ps.getFechaDeCreacion().isBefore(hasta.plusDays(1)))
                .collect(Collectors.toList());

        //WHEN
        List<PeliculaSerie> peliculasSeriesRangoFechas = this.peliculaSerieRepository
                .findAllByFechaDeCreacionBetween(desde, hasta);

        //THEN
        assertFalse(peliculasSeriesRangoFechas.isEmpty());
        assertEquals(peliculasSeries, peliculasSeriesRangoFechas);
    }

    @Test
    void findAllByCalificacionBetween() {
        //GIVEN
        Byte desde = (byte) 1;
        Byte hasta = (byte) 3;
        List<PeliculaSerie> peliculasSeries = this.peliculaSerieRepository
                .findAll()
                .stream()
                .filter(ps -> ps.getCalificacion() >= desde && ps.getCalificacion() <= hasta)
                .collect(Collectors.toList());

        //WHEN
        List<PeliculaSerie> peliculasSeriesRangoCalificacion = this.peliculaSerieRepository
                .findAllByCalificacionBetween(desde, hasta);

        //THEN
        assertFalse(peliculasSeriesRangoCalificacion.isEmpty());
        assertEquals(peliculasSeries, peliculasSeriesRangoCalificacion);
    }

    @Test
    void existsByTitulo() {
        //GIVEN
        String titulo = "Chucky";

        //WHEN
        boolean existePeliculaSeriePorTitulo = this.peliculaSerieRepository.existsByTitulo(titulo);

        //THEN
        assertTrue(existePeliculaSeriePorTitulo);
    }

}