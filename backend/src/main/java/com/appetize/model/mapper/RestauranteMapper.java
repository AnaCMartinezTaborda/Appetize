package com.appetize.model.mapper;

import com.appetize.model.dto.request.restaurante.RestauranteRequest;
import com.appetize.model.entity.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class RestauranteMapper {

    public Restaurante requestToEntity(RestauranteRequest request){
        return Restaurante.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .telefono(request.getTelefono())
                .build();
    }
}
