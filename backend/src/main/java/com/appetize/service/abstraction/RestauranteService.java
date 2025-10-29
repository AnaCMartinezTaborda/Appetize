package com.appetize.service.abstraction;

import com.appetize.model.dto.request.restaurante.RestauranteRequest;
import com.appetize.model.dto.response.RestauranteResponse;

public interface RestauranteService {
    void createRestaurante(RestauranteRequest request);
    RestauranteResponse getCurrentRestaurante();
    void updateRestaurante(RestauranteRequest request);
}
