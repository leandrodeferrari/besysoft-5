package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.Personaje;
import com.besysoft.bootcamp.dto.request.PersonajeInDto;

public class PersonajeUtil {

    public static void validar(Personaje personaje){

        validarNombre(personaje.getNombre());
        validarEdad(personaje.getEdad());
        validarPeso(personaje.getPeso());
        validarHistoria(personaje.getHistoria());

    }

    public static void validarDto(PersonajeInDto dto){

        validarNombre(dto.getNombre());
        validarEdad(dto.getEdad());
        validarPeso(dto.getPeso());
        validarHistoria(dto.getHistoria());

    }

    public static void validarEdad(Byte edad){

        if(edad == null){
            throw new IllegalArgumentException("La edad no puede ser nula.");
        }

        validarEdadMinima(edad);

    }

    public static void validarEdadMinima(Byte edad){

        if(edad < 0){
            throw new IllegalArgumentException("La edad no puede ser menor a 0.");
        }

    }

    public static void validarNombre(String nombre){

        if(nombre.isBlank()){
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }

    }

    public static void validarNombreVacio(String nombre){

        if(nombre.isEmpty()){
            throw new IllegalArgumentException("El nombre no puede ser vacío.");
        }

    }

    public static void validarPeso(Double peso){

        if(peso == null){
            throw new IllegalArgumentException("El peso no puede ser nulo.");
        }

        if(peso < 0){
            throw new IllegalArgumentException("El peso no puede ser menor a 0.");
        }

        if(peso > 500){
            throw new IllegalArgumentException("El peso no puede ser mayor a 500.");
        }

    }

    public static void validarHistoria(String historia){

        if(historia.isBlank()){
            throw new IllegalArgumentException("La historia no puede ser nula o vacía.");
        }

    }

}
