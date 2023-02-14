package com.besysoft.bootcamp.domain;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "generos")
public class Genero implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(length = 30, name = "NOMBRE", nullable = false, unique = true)
    private String nombre;

}
