package com.appetize.service.abstraction;

import com.appetize.model.dto.request.ingrediente.IngredienteRequest;
import com.appetize.model.dto.response.IngredienteResponse;

import java.util.List;

public interface IngredienteService {
    IngredienteResponse createIngrediente(IngredienteRequest request);
    IngredienteResponse getIngredienteById(Long id);
    List<IngredienteResponse> getIngredientesByNombre(String nombre);
}
