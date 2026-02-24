package com.appetize.model.dto.request.ingrediente;

import com.appetize.model.enums.UnidadMedidaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredienteRequest {
    private String nombre;
    private UnidadMedidaEnum unidadMedida;
    private BigDecimal necesario;
    private Long categoria;
    private Long inventario;
}
