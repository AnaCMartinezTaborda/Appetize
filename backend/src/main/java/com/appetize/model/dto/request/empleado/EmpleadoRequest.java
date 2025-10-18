package com.appetize.model.dto.request.empleado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoRequest {
    private String nombre;
    private String cedula;
    private String contraseña;
}
