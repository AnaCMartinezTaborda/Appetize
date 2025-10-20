package com.appetize.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestauranteResponse {
    private String id;
    private String nombre;
    private String email;
    private String direccion;
    private String telefono;
}
