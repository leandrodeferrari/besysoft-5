package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.Personaje;

import java.util.Arrays;
import java.util.List;

public class PersonajeTestUtil {

    public static final String NOMBRE1 = "Jacqueline";
    public static final String NOMBRE2 = "Vera";

    public static final Byte EDAD = (byte) 26;

    public static Personaje personaje1 = new Personaje
            (NOMBRE1, EDAD, 55.7D, "Es una actriz canadiense. Protagonizó la serie Salvation de CBS.", null);
    public static Personaje personaje2 = new Personaje
            (NOMBRE2, (byte) 86, 70.0D, "Supermodelo que enamoró a Coco Chanel y ahora ha conquistado a Paco Plaza.", null);
    public static Personaje personaje3 = new Personaje
            ("Christian", EDAD, 79.5D, "Es actor, escritor, director, productor y músico. Trabaja en el teatro, peliculas y televisión.", null);
    public static Personaje personaje4 = new Personaje
            ("Vera", (byte) 48, 90.2D, "Es actor, director y guionista australiano conocido por haber participado en la serie televisiva The secret life of us.", null);
    public static Personaje personaje5 = new Personaje
            ("Leandro", (byte) 30, 80.0D, "Actor y guionista.", null);
    public static Personaje personaje6 = new Personaje
            ("Roberto", (byte) 50, 67.9D, "Actor, musico y director.", null);

    public static final Byte DESDE = (byte) 40;
    public static final Byte HASTA = (byte) 90;

    public static final List<Personaje> PERSONAJES = Arrays.asList(
            new Personaje(1L, NOMBRE1, EDAD, 55.7D, "Es una actriz canadiense. Protagonizó la serie Salvation de CBS.", null),
            new Personaje(2L, NOMBRE2, (byte) 86, 70.0D, "Supermodelo que enamoró a Coco Chanel y ahora ha conquistado a Paco Plaza.", null)
    );

    public static final int PERSONAJES_SIZE = PERSONAJES.size();

}
