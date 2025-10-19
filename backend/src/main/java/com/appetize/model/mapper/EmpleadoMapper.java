package com.appetize.model.mapper;

import com.appetize.model.dto.response.EmpleadoResponse;
import com.appetize.model.entity.Empleado;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoMapper {

    public EmpleadoResponse entityToDto(Empleado entity){
        return EmpleadoResponse.builder()
                .id(entity.getId())
                .tipo(entity.getTipo())
                .cedula(entity.getCedula())
                .nombre(entity.getNombre())
                .restaurante(entity.getRestaurante())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .lastSession(entity.getLastSession())
                .build();
    }
}
