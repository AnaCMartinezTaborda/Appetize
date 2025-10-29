package com.appetize.model.mapper;

import com.appetize.model.dto.response.InventarioResponse;
import com.appetize.model.dto.response.RestauranteResponse;
import com.appetize.model.entity.Inventario;
import org.springframework.stereotype.Component;

@Component
public class InventarioMapper {

    public InventarioResponse entityToDto(Inventario entity){

        RestauranteResponse restaurante = RestauranteResponse.builder()
                .id(entity.getRestaurante().getId())
                .nombre(entity.getRestaurante().getNombre())
                .email(entity.getRestaurante().getEmail())
                .direccion(entity.getRestaurante().getDireccion())
                .telefono(entity.getRestaurante().getTelefono())
                .createdAt(entity.getRestaurante().getCreatedAt())
                .updatedAt(entity.getRestaurante().getUpdatedAt())
                .build();

        return InventarioResponse.builder()
                .id(entity.getId())
                .idExterno(entity.getIdExterno())
                .nombre(entity.getNombre())
                .cantidad(entity.getCantidad())
                .precioUnitario(entity.getPrecioUnitario())
                .tipoInventario(entity.getTipoInventario())
                .unidadMedida(entity.getUnidadMedida())
                .restaurante(restaurante)
                .build();
    }
}
