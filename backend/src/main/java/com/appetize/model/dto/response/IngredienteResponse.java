package com.appetize.model.dto.response;

import com.appetize.model.enums.UnidadMedidaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredienteResponse {
    private Long id;
    private Long idExterno;
    private String nombre;
    private Integer cantidad;
    private UnidadMedidaEnum unidadMedida;
    private BigDecimal necesario;
    private CategoriaIngredienteResponse categoria;
    private InventarioResponse inventario;
    private RestauranteResponse restaurante;
}
