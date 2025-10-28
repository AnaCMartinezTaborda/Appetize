package com.appetize.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/ping")
public class Ping {

    private static final Logger log = LoggerFactory.getLogger(Ping.class);

    @Operation(summary = "endpoint para mantener vivo el deploy")
    @GetMapping
    public ResponseEntity<?> ping(){
        log.info("Ping recibido");
        return ResponseEntity.ok().build();
    }
}
