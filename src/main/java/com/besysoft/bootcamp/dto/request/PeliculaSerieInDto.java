package com.besysoft.bootcamp.dto.request;

import lombok.Data;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class PeliculaSerieInDto {

    @Length(message = "El título no puede tener más de 50 carácteres.", max = 50)
    @NotBlank(message = "El título no puede ser nulo o vacío.")
    private String titulo;

    @NotNull(message = "La fecha de creación no puede ser nula.")
    private String fechaDeCreacion;

    @Min(message = "La calificación no puede ser menor a 1.", value = 1)
    @Max(message = "La calificación no puede ser mayor a 5.", value = 5)
    @NotNull(message = "La calificación no puede ser nula.")
    private Byte calificacion;

    @Min(message = "El genero ID no puede ser menor a 1.", value = 1)
    @NotNull(message = "El genero ID no puede ser nulo.")
    private Long generoId;

}
