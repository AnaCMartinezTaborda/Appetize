package com.appetize.model.dto.request.inventario;

import com.appetize.model.enums.TipoInventarioEnum;
import com.appetize.model.enums.UnidadMedidaEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventarioRequest {
    private String nombre;
    private TipoInventarioEnum tipoInventario;
    private UnidadMedidaEnum unidadMedida;
}
