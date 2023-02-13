package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.dto.request.GeneroInDto;
import com.besysoft.bootcamp.service.IGeneroService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> crear(@RequestBody GeneroInDto dto){

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
                                        @RequestBody GeneroInDto dto){

        try {
            return ResponseEntity.ok(this.generoService.actualizar(id, dto));
        } catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (RuntimeException ex){
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }

    }

}
