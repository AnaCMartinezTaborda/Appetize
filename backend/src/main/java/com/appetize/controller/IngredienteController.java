package com.appetize.controller;

import com.appetize.model.dto.request.ingrediente.IngredienteRequest;
import com.appetize.model.dto.response.IngredienteResponse;
import com.appetize.service.abstraction.IngredienteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredientes")
@RequiredArgsConstructor
public class IngredienteController {
    private final IngredienteService service;

    @Operation(summary = "El usuario puede crear un nuevo ingrediente")
    @PostMapping("/")
    public ResponseEntity<IngredienteResponse> createIngrediente(@RequestBody IngredienteRequest request){
        return ResponseEntity.ok(service.createIngrediente(request));
    }

    @Operation(summary = "El usuario puede obtener ingredientes por coincidencia de nombre")
    @GetMapping("/search")
    public ResponseEntity<List<IngredienteResponse>> getIngredientseByNombre(@RequestParam String nombre){
        return ResponseEntity.ok(service.getIngredientesByNombre(nombre));
    }

    @Operation(summary = "El usuario puede obtener un ingrediente por id")
    @GetMapping("/{id}")
    public ResponseEntity<IngredienteResponse> getIngredienteById(@PathVariable Long id){
        return ResponseEntity.ok(service.getIngredienteById(id));
    }
}
