package com.appetize.model.mapper;

import com.appetize.model.dto.request.restaurante.RestauranteRequest;
import com.appetize.model.dto.response.RestauranteResponse;
import com.appetize.model.entity.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class RestauranteMapper {

    public Restaurante requestToEntity(RestauranteRequest request){
        return Restaurante.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .direccion(request.getDireccion())
                .telefono(request.getTelefono())
                .build();
    }

    public RestauranteResponse entityToDto(Restaurante entity){
        return RestauranteResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .email(entity.getEmail())
                .direccion(entity.getDireccion())
                .telefono(entity.getTelefono())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
