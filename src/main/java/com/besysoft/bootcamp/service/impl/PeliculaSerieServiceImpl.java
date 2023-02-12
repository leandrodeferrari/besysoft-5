package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.domain.PeliculaSerie;
import com.besysoft.bootcamp.repository.database.IPeliculaSerieRepository;
import com.besysoft.bootcamp.service.IGeneroService;
import com.besysoft.bootcamp.service.IPeliculaSerieService;
import com.besysoft.bootcamp.util.FechaUtil;
import com.besysoft.bootcamp.util.PeliculaSerieUtil;
import com.besysoft.bootcamp.util.ValidacionGeneralUtil;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "database")
@Service
public class PeliculaSerieServiceImpl implements IPeliculaSerieService {

    private final IGeneroService generoService;
    private final IPeliculaSerieRepository peliculaSerieRepository;

    public PeliculaSerieServiceImpl(IGeneroService generoService,
                                    IPeliculaSerieRepository peliculaSerieRepository) {
        this.generoService = generoService;
        this.peliculaSerieRepository = peliculaSerieRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PeliculaSerie> buscarPorFiltros(String titulo, String nombreGenero) {

        if(titulo == null && nombreGenero == null){
            return this.peliculaSerieRepository.findAll();
        }

        if(titulo != null && nombreGenero != null){

            if(this.generoService.existePorNombre(nombreGenero)){

                Genero genero = this.generoService.buscarPorNombre(nombreGenero).get();

                return peliculaSerieRepository.findAllByTituloAndGenero(titulo, genero);

            } else {

                throw new IllegalArgumentException("No existe genero con ese nombre.");

            }

        }

        if(titulo != null){

            PeliculaSerieUtil.validarTituloVacio(titulo);

            List<PeliculaSerie> peliculasSeries = new ArrayList<>();

            Optional<PeliculaSerie> optionalPeliculaSerie = this.peliculaSerieRepository.findByTitulo(titulo);

            if(optionalPeliculaSerie.isPresent()){

                peliculasSeries.add(optionalPeliculaSerie.get());

                return peliculasSeries;

            } else {

                return peliculasSeries;

            }

        } else {

            PeliculaSerieUtil.validarNombreGeneroVacio(nombreGenero);

            if(this.generoService.existePorNombre(nombreGenero)){

                Genero genero = this.generoService.buscarPorNombre(nombreGenero).get();

                return peliculaSerieRepository.findAllByGenero(genero);

            } else {

                throw new IllegalArgumentException("No existe genero con ese nombre.");

            }

        }

    }

    @Transactional(readOnly = true)
    @Override
    public List<PeliculaSerie> buscarPorFechas(String desde, String hasta) {

        LocalDate fechaInicio = FechaUtil.formatear(desde);
        LocalDate fechaFinal = FechaUtil.formatear(hasta);
        FechaUtil.validarRango(fechaInicio, fechaFinal);

        return this.peliculaSerieRepository.findAllByFechaDeCreacionBetween(fechaInicio, fechaFinal);

    }

    @Transactional(readOnly = true)
    @Override
    public List<PeliculaSerie> buscarPorCalificaciones(Byte desde, Byte hasta) {

        PeliculaSerieUtil.validarCalificacion(desde);
        PeliculaSerieUtil.validarCalificacion(hasta);
        ValidacionGeneralUtil.validarRangoDeNumeros(desde, hasta);

        return this.peliculaSerieRepository.findAllByCalificacionBetween(desde, hasta);

    }

    @Transactional(readOnly = false)
    @Override
    public PeliculaSerie crear(PeliculaSerie peliculaSerie) {

        PeliculaSerieUtil.validar(peliculaSerie);
        peliculaSerie.setId(null);

        if(this.peliculaSerieRepository.existsByTitulo(peliculaSerie.getTitulo())){
            throw new IllegalArgumentException("La pelicula/serie ya existe.");
        }

        Optional<Genero> optionalGenero = this.generoService
                .buscarPorNombre(peliculaSerie.getGenero().getNombre());

        if(optionalGenero.isPresent()){
            peliculaSerie.setGenero(optionalGenero.get());
        } else {
            throw new IllegalArgumentException("No existe genero con ese nombre.");
        }

        return this.peliculaSerieRepository.save(peliculaSerie);

    }

    @Transactional(readOnly = false)
    @Override
    public PeliculaSerie actualizar(Long id, PeliculaSerie peliculaSerie) {

        ValidacionGeneralUtil.validarId(id);
        PeliculaSerieUtil.validar(peliculaSerie);
        peliculaSerie.setId(id);

        if(this.peliculaSerieRepository.existsByTitulo(peliculaSerie.getTitulo())){
            throw new IllegalArgumentException("Ya existe una pelicula/serie con ese título.");
        }

        Optional<Genero> optionalGenero = this.generoService
                .buscarPorNombre(peliculaSerie.getGenero().getNombre());

        if(optionalGenero.isPresent()){
            peliculaSerie.setGenero(optionalGenero.get());
        } else {
            throw new IllegalArgumentException("No existe genero con ese nombre.");
        }

        if(!this.peliculaSerieRepository.existsById(id)){
            throw new IllegalArgumentException("No existe pelicula/serie con ese ID.");
        }

        return this.peliculaSerieRepository.save(peliculaSerie);

    }

}
