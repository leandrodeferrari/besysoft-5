package com.besysoft.bootcamp.dto.request;

import lombok.Data;

@Data
public class PeliculaSerieInDto {

    private String titulo;
    private String fechaDeCreacion;
    private Byte calificacion;
    private Long generoId;

}
