package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.Personaje;

import java.util.Arrays;
import java.util.List;

public class PersonajeTestUtil {

    public static final Personaje PERSONAJE1_SIN_ID = new Personaje
            ("Leandro", (byte) 30, 80.0D, "Actor y guionista.", null);
    public static final Personaje PERSONAJE2_SIN_ID = new Personaje
            ("Roberto", (byte) 50, 67.9D, "Actor, musico y director.", null);

    public static final Byte DESDE = (byte) 40;
    public static final Byte HASTA = (byte) 90;

    public static final List<Personaje> PERSONAJES_CON_ID = Arrays.asList(
            new Personaje(1L, "Jacqueline", (byte) 26, 55.7D, "Es una actriz canadiense. Protagonizó la serie Salvation de CBS.", null),
            new Personaje(2L, "Vera", (byte) 86, 70.0D, "Supermodelo que enamoró a Coco Chanel y ahora ha conquistado a Paco Plaza.", null),
            new Personaje(3L, "Christian", (byte) 35, 79.5D, "Es actor, escritor, director, productor y músico. Trabaja en el teatro, peliculas y televisión.", null),
            new Personaje(4L, "Joel", (byte) 48, 90.2D, "Es actor, director y guionista australiano conocido por haber participado en la serie televisiva The secret life of us.", null)
    );

    public static final Personaje PERSONAJE1_CON_ID = PERSONAJES_CON_ID.get(0);

    /*La cantidad de personajes en PERSONAJES_CON_ID debe ser la misma que los inserts de personajes, en import.sql*/
    public static final int PERSONAJES_SIZE = PERSONAJES_CON_ID.size();

}
