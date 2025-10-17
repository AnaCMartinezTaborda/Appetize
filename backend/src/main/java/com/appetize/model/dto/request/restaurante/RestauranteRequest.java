package com.appetize.model.dto.request.restaurante;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestauranteRequest {
    private String nombre;
    private String email;
    private String direccion;
    private String telefono;
}
