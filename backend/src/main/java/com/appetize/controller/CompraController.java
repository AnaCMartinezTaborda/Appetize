package com.appetize.controller;

import com.appetize.model.dto.request.compra.CompraRequest;
import com.appetize.service.abstraction.CompraService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService service;

    @Operation(summary = "El Administrador crea una compra")
    @PostMapping
    public ResponseEntity<String> createCompra(@RequestBody CompraRequest request){
        service.createCompra(request);
        return ResponseEntity.ok("Compra creada");
    }
}
