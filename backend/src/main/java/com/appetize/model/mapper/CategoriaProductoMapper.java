package com.appetize.model.mapper;

import com.appetize.model.dto.response.CategoriaProductoResponse;
import com.appetize.model.entity.CategoriaProducto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoriaProductoMapper {

    private final RestauranteMapper restauranteMapper;

    public CategoriaProductoResponse entityToDto(CategoriaProducto entity){
        return CategoriaProductoResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .restaurante(restauranteMapper.entityToDto(entity.getRestaurante()))
                .build();
    }
}
