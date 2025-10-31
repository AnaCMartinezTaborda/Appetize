package com.appetize.model.mapper;

import com.appetize.model.dto.request.inventario.InventarioRequest;
import com.appetize.model.dto.response.InventarioResponse;
import com.appetize.model.entity.Inventario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventarioMapper {

    public InventarioResponse entityToDto(Inventario entity){
        return InventarioResponse.builder()
                .id(entity.getId())
                .idExterno(entity.getIdExterno())
                .nombre(entity.getNombre())
                .cantidad(entity.getCantidad())
                .costoUnitario(entity.getCostoUnitario())
                .tipoInventario(entity.getTipoInventario())
                .unidadMedida(entity.getUnidadMedida())
                .build();
    }

    public Inventario dtoToEntity(InventarioRequest request) {
        return Inventario.builder()
                .nombre(request.getNombre())
                .tipoInventario(request.getTipoInventario())
                .unidadMedida(request.getUnidadMedida())
                .build();
    }
}
