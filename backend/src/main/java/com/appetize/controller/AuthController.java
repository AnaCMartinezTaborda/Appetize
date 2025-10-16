package com.appetize.controller;

import com.appetize.model.dto.request.RegisterRequest;
import com.appetize.service.abstraction.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final EmpleadoService empleadoService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        empleadoService.register(request);
        return ResponseEntity.ok("Empleado registrado satisfactoriamente");
    }
}
