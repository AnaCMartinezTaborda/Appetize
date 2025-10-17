package com.appetize.controller;

import com.appetize.model.dto.request.restaurante.RestauranteRequest;
import com.appetize.service.abstraction.RestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurante")
@RequiredArgsConstructor
public class RestauranteController {

    private final RestauranteService service;

    @PostMapping
    public ResponseEntity<String> createRestaurante(@RequestBody RestauranteRequest request){
        service.createRestaurante(request);
        return ResponseEntity.ok("Restaurante creado satisfactoriamente");
    }
}
