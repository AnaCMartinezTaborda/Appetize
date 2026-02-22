package com.appetize.controller;

import com.appetize.model.dto.request.categoriaingrediente.CategoriaIngredienteRequest;
import com.appetize.model.dto.response.CategoriaIngredienteResponse;
import com.appetize.service.abstraction.CategoriaIngredienteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoriaIngredientes")
@RequiredArgsConstructor
public class CategoriaIngredienteController {
    private final CategoriaIngredienteService service;

    @Operation(summary = "el usuario crea una nueva categoria de ingredientes")
    @PostMapping("/")
    public ResponseEntity<CategoriaIngredienteResponse> createCategoriaIngrediente(@RequestBody CategoriaIngredienteRequest request){
        return ResponseEntity.ok(service.createCategoriaIngrediente(request));
    }

    @Operation(summary = "El usuario obtiene categorias de ingredientes por coincidencia de nombre")
    @GetMapping("/search")
    public ResponseEntity<List<CategoriaIngredienteResponse>> getCategoriasIngredienteByNombre(@RequestParam String nombre){
        return ResponseEntity.ok(service.getCategoriaIngredienteByNombre(nombre));
    }

    @Operation(summary = "El usuario obtiene categorias de ingredientes por id")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaIngredienteResponse> getCategoriaIngredienteById(@PathVariable Long id){
        return ResponseEntity.ok(service.getCategoriaIngredienteById(id));
    }
}
