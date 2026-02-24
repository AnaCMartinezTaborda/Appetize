package com.appetize.model.mapper;

import com.appetize.model.dto.response.IngredienteResponse;
import com.appetize.model.entity.Ingrediente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IngredienteMapper {

    private final RestauranteMapper restauranteMapper;
    private final CategoriaIngredienteMapper categoriaIngredienteMapper;
    private final InventarioMapper inventarioMapper;

    public IngredienteResponse entityToDto(Ingrediente entity){
        return IngredienteResponse.builder()
                .id(entity.getId())
                .idExterno(entity.getIdExterno())
                .nombre(entity.getNombre())
                .cantidad(entity.getCantidad())
                .unidadMedida(entity.getUnidadMedida())
                .necesario(entity.getNecesario())
                .categoria(categoriaIngredienteMapper.entityToDto(entity.getCategoria()))
                .inventario(inventarioMapper.entityToDto(entity.getInventario()))
                .restaurante(restauranteMapper.entityToDto(entity.getRestaurante()))
                .build();
    }
}
