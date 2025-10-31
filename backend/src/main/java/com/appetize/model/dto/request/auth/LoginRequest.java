package com.appetize.model.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {

    @NotBlank(message = "La cédula no puede estar vacía")
    String cedula;

    @NotBlank(message = "La contraseña no puede estar vacía")
    String password;
}
