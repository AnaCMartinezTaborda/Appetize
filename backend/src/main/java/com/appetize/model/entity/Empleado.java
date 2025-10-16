package com.appetize.model.entity;

import com.appetize.model.enums.TipoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "empleados")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Debe establecer un tipo de empleado")
    private TipoEnum tipo;

    @Column(unique = true)
    @NotBlank(message = "La cédula no puede estar vacía")
    private String cedula;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @Size(min = 8, message = "La contraseña debe tener mínimo 8 caracteres")
    @NotBlank(message = "La contraseña no puede estar vacía")
    private String contraseña;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;

    @Column(nullable = false, name = "created_at")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedAt;

    @Column(name = "last_session")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime lastSession;
}
