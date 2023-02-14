package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.dto.request.PersonajeInDto;
import com.besysoft.bootcamp.service.IPersonajeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/personajes")
public class PersonajeController {

    private final IPersonajeService personajeService;

    public PersonajeController(IPersonajeService personajeService) {
        this.personajeService = personajeService;
    }

    @GetMapping
    public ResponseEntity<?> buscarPorFiltros(@RequestParam(required = false) String nombre,
                                              @RequestParam(required = false) Byte edad){

        try {
            return ResponseEntity.ok(this.personajeService.buscarPorFiltros(nombre, edad));
        } catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch(RuntimeException ex){
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }

    }

    @GetMapping("/edades")
    public ResponseEntity<?> buscarPorEdades(@RequestParam Byte desde,
                                             @RequestParam Byte hasta){

        try {
            return ResponseEntity.ok(this.personajeService.buscarPorEdades(desde, hasta));
        } catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (RuntimeException ex){
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody PersonajeInDto dto,
                                   BindingResult result){

        if(result.hasErrors()){

            Map<String, String> validaciones = new HashMap<>();

            result.getFieldErrors().forEach(error -> {
                validaciones.put(error.getField(), error.getDefaultMessage());
            });

            return ResponseEntity.badRequest().body(validaciones);

        }

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.personajeService.crear(dto));
        } catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (RuntimeException ex){
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody PersonajeInDto dto,
                                        BindingResult result){

        if(result.hasErrors()){

            Map<String, String> validaciones = new HashMap<>();

            result.getFieldErrors().forEach(error -> {
                validaciones.put(error.getField(), error.getDefaultMessage());
            });

            return ResponseEntity.badRequest().body(validaciones);

        }

        try {
            return ResponseEntity.ok(this.personajeService.actualizar(id, dto));
        } catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (RuntimeException ex){
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }

    }

}
