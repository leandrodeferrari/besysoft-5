package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.domain.PeliculaSerie;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class PeliculaSerieTestUtil {

    public static PeliculaSerie peliculaSerie1 = new PeliculaSerie
            ("Chucky", LocalDate.parse("2004-10-10"), (byte) 5, null,null);
    public static PeliculaSerie peliculaSerie2 = new PeliculaSerie
            ("Annabelle", LocalDate.parse("2006-01-10"), (byte) 2, null,null);
    public static PeliculaSerie peliculaSerie3 = new PeliculaSerie
            ("Jaula", LocalDate.parse("2021-02-12"), (byte) 2, null,null);
    public static PeliculaSerie peliculaSerie4 = new PeliculaSerie
            ("Culpable", LocalDate.parse("2020-07-11"), (byte) 4, null,null);
    public static PeliculaSerie peliculaSerie5 = new PeliculaSerie
            ("Tiburon", LocalDate.parse("2023-01-01"), (byte) 4, null, null);
    public static PeliculaSerie peliculaSerie6 = new PeliculaSerie
            ("Jumanji", LocalDate.parse("2021-11-08"), (byte) 5, null, null);

    public static final List<PeliculaSerie> PELICULAS_SERIES = Arrays.asList(
            new PeliculaSerie(1L, "Chucky", LocalDate.parse("2004-10-10"), (byte) 5, new Genero(1L, "Terror"),null),
            new PeliculaSerie(2L, "Annabelle", LocalDate.parse("2006-01-10"), (byte) 2, new Genero(2L, "Suspenso"),null)
    );

    public static final int PELICULAS_SERIES_SIZE = PELICULAS_SERIES.size();

    public static final LocalDate DESDE_LOCAL_DATE = LocalDate.parse("2020-01-01");
    public static final LocalDate HASTA_LOCAL_DATE = LocalDate.parse("2023-01-01");

    public static final Byte DESDE_BYTE = (byte) 1;
    public static final Byte HASTA_BYTE = (byte) 3;

}
