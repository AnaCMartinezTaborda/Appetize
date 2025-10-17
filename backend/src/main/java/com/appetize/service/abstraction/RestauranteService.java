package com.appetize.service.abstraction;

import com.appetize.model.dto.request.restaurante.RestauranteRequest;

public interface RestauranteService {
    void createRestaurante(RestauranteRequest request);
}
