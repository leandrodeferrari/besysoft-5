package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.Genero;

import java.util.Arrays;
import java.util.List;

public class GeneroTestUtil {

    public static final Genero GENERO1_SIN_ID = new Genero("Comedia");
    public static final Genero GENERO2_SIN_ID = new Genero("Ciencia ficcion");

    public static final List<Genero> GENEROS_CON_ID = Arrays.asList(
            new Genero(1L, "Terror"),
            new Genero(2L, "Policial"),
            new Genero(3L, "Suspenso"),
            new Genero(4L, "Romance")
    );

    public static final Genero GENERO1_CON_ID = GENEROS_CON_ID.get(0);

    /*La cantidad de generos en GENEROS_CON_ID debe se la misma que los inserts de generos en import.sql*/
    public static final int GENEROS_SIZE = GENEROS_CON_ID.size();

}
