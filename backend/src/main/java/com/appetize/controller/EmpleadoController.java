package com.appetize.controller;

import com.appetize.model.dto.request.empleado.EmpleadoRequest;
import com.appetize.model.dto.request.empleado.UpdatePasswordRequest;
import com.appetize.model.dto.response.EmpleadoResponse;
import com.appetize.service.abstraction.EmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "Obtiene el empleado de la sesión actual")
    @GetMapping("/actual")
    public ResponseEntity<EmpleadoResponse> getPropioEmpleado(){
        return ResponseEntity.ok(service.getPropioEmpleado());
    }

    @Operation(summary = "Obtiene todos los empleados del restaurante")
    @GetMapping
    public ResponseEntity<List<EmpleadoResponse>> getAllEmpleadoByRestaurante(){
        return ResponseEntity.ok(service.getAllEmpleadosByRestaurante());
    }

    @Operation(summary = "El administrador obtiene un empleado por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoResponse> getEmpleadoById(@PathVariable String id){
        return ResponseEntity.ok(service.getEmpleadoById(id));
    }

    @Operation(summary = "El empleado puede actualizar su propia contraseña")
    @PatchMapping("/updatePasswordActual")
    public ResponseEntity<String> updatePasswordEmpleadoPropio(@Valid @RequestBody UpdatePasswordRequest request){
        service.updatePasswordPropia(request);
        return ResponseEntity.ok("Contraseña cambiada correctamente");
    }

    @Operation(summary = "El administrador puede actualizar empleados")
    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateEmpleado(@Valid @RequestBody EmpleadoRequest request, @PathVariable String id){
        service.updateEmpleado(request, id);
        return ResponseEntity.ok("Empleado actualizado correctamente");
    }
}
