package com.appetize.controller;

import com.appetize.model.dto.request.categoriaproducto.CategoriaProductoRequest;
import com.appetize.model.dto.response.CategoriaProductoResponse;
import com.appetize.service.abstraction.CategoriaProductoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoriaProductos")
@RequiredArgsConstructor
public class CategoriaProductoController {
    private final CategoriaProductoService service;

    @Operation(summary = "el usuario crea una nueva categoria de productos")
    @PostMapping("/")
    public ResponseEntity<CategoriaProductoResponse> createCategoriaProducto(@RequestBody CategoriaProductoRequest request){
        return ResponseEntity.ok(service.createCategoriaProducto(request));
    }

    @Operation(summary = "El usuario obtiene categorias de productos por coincidencia de nombre")
    @GetMapping("/search")
    public ResponseEntity<List<CategoriaProductoResponse>> getCategoriasProductoByNombre(@RequestParam String nombre){
        return ResponseEntity.ok(service.getCategoriaProductosByNombre(nombre));
    }

    @Operation(summary = "El usuario obtiene categorias de productos por id")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProductoResponse> getCategoriaProductoById(@PathVariable Long id){
        return ResponseEntity.ok(service.getCategoriaProductoById(id));
    }
}
