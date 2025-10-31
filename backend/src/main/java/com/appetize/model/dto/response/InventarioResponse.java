package com.appetize.model.dto.response;

import com.appetize.model.enums.TipoInventarioEnum;
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
public class InventarioResponse {
    private Long id;
    private Long idExterno;
    private String nombre;
    private BigDecimal cantidad;
    private BigDecimal costoUnitario;
    private TipoInventarioEnum tipoInventario;
    private UnidadMedidaEnum unidadMedida;
}
