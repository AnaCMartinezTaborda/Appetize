package com.appetize.model.dto.request.restaurante;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestauranteRequest {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Email(message = "El correo electrónico no es válido")
    private String email;


    private String direccion;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(
            regexp = "^(\\+?57)?\\d{10}$",
            message = "El número de teléfono no es válido"
    )
    private String telefono;
}
