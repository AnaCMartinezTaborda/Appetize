package com.appetize.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "restaurantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @Column(unique = true)
    @Email(message = "Ingrese un correo electrónico correcto")
    private String email;

    private String direccion;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^3\\d{9}$", message = "Ingrese un numero de teléfono correcto")
    private String telefono;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
