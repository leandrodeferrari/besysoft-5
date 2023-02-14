package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.dto.request.GeneroInDto;
import com.besysoft.bootcamp.service.IGeneroService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    private final IGeneroService generoService;

    public GeneroController(IGeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodos(){

        try {
            return ResponseEntity.ok(this.generoService.obtenerTodos());
        } catch (RuntimeException ex){
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody GeneroInDto dto,
                                   BindingResult result){

        if(result.hasErrors()){

            Map<String, String> validaciones = new HashMap<>();

            result.getFieldErrors().forEach(error -> {
                validaciones.put(error.getField(), error.getDefaultMessage());
            });

            return ResponseEntity.badRequest().body(validaciones);

        }

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.generoService.crear(dto));
        } catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (RuntimeException ex){
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody GeneroInDto dto,
                                        BindingResult result){

        if(result.hasErrors()){

            Map<String, String> validaciones = new HashMap<>();

            result.getFieldErrors().forEach(error -> {
                validaciones.put(error.getField(), error.getDefaultMessage());
            });

            return ResponseEntity.badRequest().body(validaciones);

        }

        try {
            return ResponseEntity.ok(this.generoService.actualizar(id, dto));
        } catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (RuntimeException ex){
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }

    }

}
