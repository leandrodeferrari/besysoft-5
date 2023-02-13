package com.besysoft.bootcamp.dto.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data
public class PersonajeInDto {

    @Max(message = "El nombre no puede tener más de 30 carácteres.", value = 30)
    @NotBlank(message = "El nombre no puede ser nulo o vacío.")
    private String nombre;

    private Byte edad;

    private Double peso;

    @NotBlank(message = "La historia no puede ser nula o vacía.")
    private String historia;

}
