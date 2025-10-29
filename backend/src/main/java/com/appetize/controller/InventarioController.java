package com.appetize.controller;

import com.appetize.model.dto.request.inventario.InventarioRequest;
import com.appetize.model.dto.response.InventarioResponse;
import com.appetize.service.abstraction.InventarioService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")
@RequiredArgsConstructor
public class InventarioController {
    private final InventarioService service;

    @Operation(summary = "El Administrador crea un nuevo producto de inventario")
    @PostMapping
    public ResponseEntity<String> createInventario(@RequestBody InventarioRequest request){
        service.createInventario(request);
        return ResponseEntity.ok("Nuevo producto de inventario creado");
    }

    @Operation(summary = "El administrador obtiene productos del inventario por coincidencia de nombre")
    @GetMapping("/search")
    public ResponseEntity<List<InventarioResponse>> getInventarioByNombre(@RequestParam String nombre){
        return ResponseEntity.ok(service.getInventarioByNombre(nombre));
    }
}
