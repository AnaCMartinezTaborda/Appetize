package com.appetize.controller;

import com.appetize.model.dto.request.auth.LoginRequest;
import com.appetize.model.dto.request.auth.RegisterRequest;
import com.appetize.service.abstraction.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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

    private final AuthService service;

    @Operation(summary = "Registrar nuevo usuario Administrador")
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request){
        service.register(request);
        return ResponseEntity.ok("Empleado registrado satisfactoriamente");
    }

    @Operation(summary = "Iniciar sesión")
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest request){
        String login = service.login(request);
        return ResponseEntity.ok(login);
    }
}
