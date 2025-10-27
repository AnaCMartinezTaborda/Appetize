package com.appetize.controller;

import com.appetize.model.dto.request.restaurante.RestauranteRequest;
import com.appetize.model.entity.Restaurante;
import com.appetize.service.abstraction.RestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurante")
@RequiredArgsConstructor
public class RestauranteController {

    private final RestauranteService service;

    @Operation(summary = "Crear un nuevo restaurante")
    @PostMapping
    public ResponseEntity<String> createRestaurante(@Valid @RequestBody RestauranteRequest request){
        service.createRestaurante(request);
        return ResponseEntity.ok("Restaurante creado satisfactoriamente");
    }

    @Operation(summary = "El Administrador obtiene el restaurante")
    @GetMapping
    public ResponseEntity<Restaurante> getCurrentRestaurante(){
        return ResponseEntity.ok(service.getCurrentRestaurante());
    }

    @Operation(summary = "El Administrador puede actualizar su restaurante")
    @PatchMapping("/update")
    public ResponseEntity<String> updateRestaurante(@Valid @RequestBody RestauranteRequest request){
        service.updateRestaurante(request);
        return ResponseEntity.ok("Restaurante actualizado correctamente");
    }
}
