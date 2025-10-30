package com.appetize.model.mapper;

import com.appetize.model.dto.response.InventarioResponse;
import com.appetize.model.entity.Inventario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventarioMapper {

    private final RestauranteMapper restauranteMapper;

    public InventarioResponse entityToDto(Inventario entity){
        return InventarioResponse.builder()
                .id(entity.getId())
                .idExterno(entity.getIdExterno())
                .nombre(entity.getNombre())
                .cantidad(entity.getCantidad())
                .precioUnitario(entity.getCostoUnitario())
                .tipoInventario(entity.getTipoInventario())
                .unidadMedida(entity.getUnidadMedida())
                .restaurante(restauranteMapper.entityToDto(entity.getRestaurante()))
                .build();
    }
}
