package com.besysoft.bootcamp.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GeneroUtil {

    public static final String NOMBRE_NULO_VACIO = "El nombre no puede ser nulo o vacío.";

    public static void validarNombre(String nombre){

        if(nombre.isBlank()){
            log.info("Ocurrio una validacion personalizada, en el metodo validarNombre(): " + NOMBRE_NULO_VACIO);
            throw new IllegalArgumentException(NOMBRE_NULO_VACIO);
        }

    }

}
