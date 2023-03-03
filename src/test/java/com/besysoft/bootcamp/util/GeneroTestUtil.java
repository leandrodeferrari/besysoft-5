package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.Genero;

import java.util.Arrays;
import java.util.List;

public class GeneroTestUtil {

    public static Genero genero1 = new Genero("Terror");
    public static Genero genero2 = new Genero("Policial");
    public static Genero genero3 = new Genero("Suspenso");
    public static Genero genero4 = new Genero("Romance");
    public static Genero genero5 = new Genero("Comedia");
    public static Genero genero6 = new Genero("Ciencia ficcion");

    public static List<Genero> GENEROS = Arrays.asList(
            new Genero(1L, "Terror"),
            new Genero(2L, "Policial"),
            new Genero(3L, "Suspenso"),
            new Genero(4L, "Romance")
    );

    public static final int GENEROS_SIZE = GENEROS.size();

}
