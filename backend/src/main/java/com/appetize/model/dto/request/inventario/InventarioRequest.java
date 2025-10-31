package com.appetize.model.dto.request.inventario;

import com.appetize.model.enums.TipoInventarioEnum;
import com.appetize.model.enums.UnidadMedidaEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventarioRequest {

    @NotBlank(message = "El nombre del inventario no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El tipo de inventario no puede estar vacío")
    private TipoInventarioEnum tipoInventario;

    @NotBlank(message = "La unidad de medida no puede estar vacía")
    private UnidadMedidaEnum unidadMedida;
}
