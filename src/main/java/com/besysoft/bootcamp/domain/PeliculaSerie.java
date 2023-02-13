package com.besysoft.bootcamp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;

import java.time.LocalDate;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "peliculas_series")
public class PeliculaSerie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(length = 50, name = "TITULO", nullable = false, unique = true)
    private String titulo;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "FECHA_DE_CREACION", nullable = false)
    private LocalDate fechaDeCreacion;

    @Column( name = "CALIFICACION", nullable = false)
    private Byte calificacion;

    @ManyToOne
    @JoinColumn(name = "GENERO_ID", nullable = false)
    private Genero genero;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "personajes_peliculas_series",
            joinColumns = @JoinColumn(name = "PELICULA_SERIE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERSONAJE_ID")
    )
    private List<Personaje> personajes;

    public PeliculaSerie(Long id, String titulo, LocalDate fechaDeCreacion, Byte calificacion, Genero genero) {
        this.id = id;
        this.titulo = titulo;
        this.fechaDeCreacion = fechaDeCreacion;
        this.calificacion = calificacion;
        this.genero = genero;
    }

}
