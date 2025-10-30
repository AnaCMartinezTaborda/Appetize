package com.appetize.controller;

import com.appetize.model.dto.request.compra.CompraRequest;
import com.appetize.model.dto.response.CompraResponse;
import com.appetize.service.abstraction.CompraService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

    @Operation(summary = "El Administrador obtiene una compra por su id")
    @GetMapping("/{id}")
    public ResponseEntity<CompraResponse> getCompraById(@PathVariable Long id){
        return ResponseEntity.ok(service.getCompraById(id));
    }

    @Operation(summary = "El Administrador obtiene una compra por su id externo")
    @GetMapping("/externo/{idExterno}")
    public ResponseEntity<CompraResponse> getCompraByIdExterno(@PathVariable Long idExterno){
        return ResponseEntity.ok(service.getCompraByIdExterno(idExterno));
    }

    @Operation(summary = "El Administrador obtiene las compras con paginación y rango de fecha")
    @GetMapping("/paged")
    public ResponseEntity<Page<CompraResponse>> getAllComprasPaged(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime desde,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime hasta,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(service.getAllComprasPaged(desde, hasta, page, size));
    }
}
