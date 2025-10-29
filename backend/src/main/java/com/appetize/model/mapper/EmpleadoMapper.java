package com.appetize.model.mapper;

import com.appetize.model.dto.response.EmpleadoResponse;
import com.appetize.model.entity.Empleado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmpleadoMapper {

    private final RestauranteMapper restauranteMapper;

    public EmpleadoResponse entityToDto(Empleado entity){
        return EmpleadoResponse.builder()
                .id(entity.getId())
                .tipo(entity.getTipo())
                .cedula(entity.getCedula())
                .nombre(entity.getNombre())
                .restaurante(restauranteMapper.entityToDto(entity.getRestaurante()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .lastSession(entity.getLastSession())
                .build();
    }
}
