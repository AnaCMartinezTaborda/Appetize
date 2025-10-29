package com.appetize.model.mapper;

import com.appetize.model.dto.response.EmpleadoResponse;
import com.appetize.model.dto.response.RestauranteResponse;
import com.appetize.model.entity.Empleado;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoMapper {

    public EmpleadoResponse entityToDto(Empleado entity){

        RestauranteResponse restaurante = RestauranteResponse.builder()
                .id(entity.getRestaurante().getId())
                .nombre(entity.getRestaurante().getNombre())
                .email(entity.getRestaurante().getEmail())
                .direccion(entity.getRestaurante().getDireccion())
                .telefono(entity.getRestaurante().getTelefono())
                .createdAt(entity.getRestaurante().getCreatedAt())
                .updatedAt(entity.getRestaurante().getUpdatedAt())
                .build();

        return EmpleadoResponse.builder()
                .id(entity.getId())
                .tipo(entity.getTipo())
                .cedula(entity.getCedula())
                .nombre(entity.getNombre())
                .restaurante(restaurante)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .lastSession(entity.getLastSession())
                .build();
    }
}
