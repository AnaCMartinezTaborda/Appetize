package com.appetize.model.dto.response;

import com.appetize.model.entity.Restaurante;
import com.appetize.model.enums.TipoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpleadoResponse {
    private String id;
    private TipoEnum tipo;
    private String cedula;
    private String nombre;
    private Restaurante restaurante;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastSession;
}
