package com.appetize.service.abstraction;

import com.appetize.model.dto.request.restaurante.RestauranteRequest;
import com.appetize.model.entity.Restaurante;

public interface RestauranteService {
    void createRestaurante(RestauranteRequest request);
    Restaurante getCurrentRestaurante();
    void updateRestaurante(RestauranteRequest request);
}
