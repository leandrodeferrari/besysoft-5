package com.besysoft.bootcamp.dto.request;

import lombok.Data;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PersonajeInDto {

    @Length(message = "El nombre no puede tener más de 30 carácteres.", max = 30)
    @NotBlank(message = "El nombre no puede ser nulo o vacío.")
    private String nombre;

    @Max(message = "La edad no puede ser mayor a 127.", value = 127)
    @NotNull(message = "La edad no puede ser nula.")
    private Byte edad;

    @DecimalMax(message = "El peso no puede ser mayor a 500.", value = "500.0", inclusive = true)
    @NotNull(message = "El peso no puede ser nulo.")
    private Double peso;

    @NotBlank(message = "La historia no puede ser nula o vacía.")
    private String historia;

}
