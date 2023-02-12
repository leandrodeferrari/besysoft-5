package com.besysoft.bootcamp.domain;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "generos")
public class Genero implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(length = 30, name = "NOMBRE", nullable = false, unique = true)
    private String nombre;

    public Genero() {
    }

    public Genero(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
