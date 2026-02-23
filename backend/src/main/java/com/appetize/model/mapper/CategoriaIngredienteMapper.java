package com.appetize.model.mapper;

import com.appetize.model.dto.response.CategoriaIngredienteResponse;
import com.appetize.model.entity.CategoriaIngrediente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoriaIngredienteMapper {

    private final RestauranteMapper restauranteMapper;

    public CategoriaIngredienteResponse entityToDto(CategoriaIngrediente entity){
        return CategoriaIngredienteResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .restaurante(restauranteMapper.entityToDto(entity.getRestaurante()))
                .build();
    }
}
