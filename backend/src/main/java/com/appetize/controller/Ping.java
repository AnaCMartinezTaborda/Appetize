package com.appetize.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class Ping {

    @Operation(summary = "endpoint para mantener vivo el deploy")
    @GetMapping
    public ResponseEntity<?> ping(){
        System.out.println("Ping Recibido");
        return ResponseEntity.ok().build();
    }
}
