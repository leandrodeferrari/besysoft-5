package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.domain.PeliculaSerie;
import com.besysoft.bootcamp.dto.mapper.IPeliculaSerieMapper;
import com.besysoft.bootcamp.dto.request.PeliculaSerieInDto;
import com.besysoft.bootcamp.dto.response.PeliculaSerieOutDto;
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
import java.util.stream.Collectors;

@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "database")
@Service
public class PeliculaSerieServiceImpl implements IPeliculaSerieService {

    private final IGeneroService generoService;
    private final IPeliculaSerieMapper peliculaSerieMapper;
    private final IPeliculaSerieRepository peliculaSerieRepository;

    public PeliculaSerieServiceImpl(IGeneroService generoService,
                                    IPeliculaSerieMapper peliculaSerieMapper,
                                    IPeliculaSerieRepository peliculaSerieRepository) {
        this.generoService = generoService;
        this.peliculaSerieMapper = peliculaSerieMapper;
        this.peliculaSerieRepository = peliculaSerieRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PeliculaSerieOutDto> buscarPorFiltros(String titulo, String nombreGenero) {

        if(titulo == null && nombreGenero == null){
            return this.peliculaSerieRepository.findAll()
                    .stream()
                    .map(this.peliculaSerieMapper::mapToDto)
                    .collect(Collectors.toList());
        }

        if(titulo != null && nombreGenero != null){

            if(this.generoService.existePorNombre(nombreGenero)){

                Genero genero = this.generoService.buscarPorNombre(nombreGenero).get();

                return this.peliculaSerieRepository.findAllByTituloAndGenero(titulo, genero)
                        .stream()
                        .map(this.peliculaSerieMapper::mapToDto)
                        .collect(Collectors.toList());

            } else {

                throw new IllegalArgumentException("No existe genero con ese nombre.");

            }

        }

        if(titulo != null){

            PeliculaSerieUtil.validarTituloVacio(titulo);

            List<PeliculaSerieOutDto> peliculasSeriesDto = new ArrayList<>();

            Optional<PeliculaSerie> optionalPeliculaSerie = this.peliculaSerieRepository.findByTitulo(titulo);

            if(optionalPeliculaSerie.isPresent()){

                peliculasSeriesDto.add(this.peliculaSerieMapper.mapToDto(optionalPeliculaSerie.get()));

                return peliculasSeriesDto;

            } else {

                return peliculasSeriesDto;

            }

        } else {

            PeliculaSerieUtil.validarNombreGeneroVacio(nombreGenero);

            if(this.generoService.existePorNombre(nombreGenero)){

                Genero genero = this.generoService.buscarPorNombre(nombreGenero).get();

                return peliculaSerieRepository.findAllByGenero(genero)
                        .stream()
                        .map(this.peliculaSerieMapper::mapToDto)
                        .collect(Collectors.toList());

            } else {

                throw new IllegalArgumentException("No existe genero con ese nombre.");

            }

        }

    }

    @Transactional(readOnly = true)
    @Override
    public List<PeliculaSerieOutDto> buscarPorFechas(String desde, String hasta) {

        LocalDate fechaInicio = FechaUtil.formatear(desde);
        LocalDate fechaFinal = FechaUtil.formatear(hasta);
        FechaUtil.validarRango(fechaInicio, fechaFinal);

        return this.peliculaSerieRepository.findAllByFechaDeCreacionBetween(fechaInicio, fechaFinal)
                .stream()
                .map(this.peliculaSerieMapper::mapToDto)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    @Override
    public List<PeliculaSerieOutDto> buscarPorCalificaciones(Byte desde, Byte hasta) {

        PeliculaSerieUtil.validarCalificacion(desde);
        PeliculaSerieUtil.validarCalificacion(hasta);
        ValidacionGeneralUtil.validarRangoDeNumeros(desde, hasta);

        return this.peliculaSerieRepository.findAllByCalificacionBetween(desde, hasta)
                .stream()
                .map(this.peliculaSerieMapper::mapToDto)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = false)
    @Override
    public PeliculaSerieOutDto crear(PeliculaSerieInDto dto) {

        ValidacionGeneralUtil.validarId(dto.getGeneroId());
        PeliculaSerieUtil.validarDto(dto);

        if(this.peliculaSerieRepository.existsByTitulo(dto.getTitulo())){
            throw new IllegalArgumentException("La pelicula/serie ya existe.");
        }

        Optional<Genero> optionalGenero = this.generoService
                .buscarPorId(dto.getGeneroId());

        if(optionalGenero.isPresent()){

            PeliculaSerie peliculaSerie = this.peliculaSerieMapper.mapToEntity(dto);
            peliculaSerie.setGenero(optionalGenero.get());

            return this.peliculaSerieMapper.mapToDto(this.peliculaSerieRepository.save(peliculaSerie));

        } else {
            throw new IllegalArgumentException("No existe genero con ese nombre.");
        }

    }

    @Transactional(readOnly = false)
    @Override
    public PeliculaSerieOutDto actualizar(Long id, PeliculaSerieInDto dto) {

        ValidacionGeneralUtil.validarId(id);
        PeliculaSerieUtil.validarDto(dto);
        //peliculaSerie.setId(id);

        if(!this.peliculaSerieRepository.existsById(id)){
            throw new IllegalArgumentException("No existe pelicula/serie con ese ID.");
        }

        if(this.peliculaSerieRepository.existsByTitulo(dto.getTitulo())){
            throw new IllegalArgumentException("Ya existe una pelicula/serie con ese título.");
        }

        Optional<Genero> optionalGenero = this.generoService
                .buscarPorId(dto.getGeneroId());

        if(optionalGenero.isPresent()){

            PeliculaSerie peliculaSerie = this.peliculaSerieMapper.mapToEntity(dto);
            peliculaSerie.setGenero(optionalGenero.get());
            peliculaSerie.setId(id);

            return this.peliculaSerieMapper.mapToDto(this.peliculaSerieRepository.save(peliculaSerie));

        } else {
            throw new IllegalArgumentException("No existe genero con ese nombre.");
        }

    }

}
