package com.appetize.controller;

import com.appetize.model.dto.request.empleado.EmpleadoRequest;
import com.appetize.service.abstraction.EmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService service;

    @Operation(summary = "Crear un nuevo empleado")
    @PostMapping
    public ResponseEntity<String> createEmpleado(@RequestBody EmpleadoRequest request){
        service.createEmpleado(request);
        return ResponseEntity.ok("Empleado creado correctamente");
    }
}
