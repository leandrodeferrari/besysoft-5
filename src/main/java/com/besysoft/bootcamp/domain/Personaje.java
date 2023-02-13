package com.besysoft.bootcamp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "personajes")
public class Personaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(length = 30, name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "EDAD", nullable = false)
    private Byte edad;

    @Column(name = "PESO", nullable = false)
    private Double peso;

    @Column(length = 255, name = "HISTORIA", nullable = false)
    private String historia;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "personajes")
    private List<PeliculaSerie> peliculasSeries;

    public Personaje(Long id, String nombre, Byte edad, Double peso, String historia) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.historia = historia;
    }

}