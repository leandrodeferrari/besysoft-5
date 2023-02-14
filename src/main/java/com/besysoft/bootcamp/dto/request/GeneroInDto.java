package com.besysoft.bootcamp.dto.request;

import lombok.Data;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class GeneroInDto {

    @Length(message = "El nombre no puede tener más de 30 carácteres.", max = 30)
    @NotBlank(message = "El nombre no puede ser nulo o vacío.")
    private String nombre;

}
