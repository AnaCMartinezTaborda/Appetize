package com.appetize.service.abstraction;

import com.appetize.model.dto.request.categoriaingrediente.CategoriaIngredienteRequest;
import com.appetize.model.dto.response.CategoriaIngredienteResponse;

import java.util.List;

public interface CategoriaIngredienteService {
    CategoriaIngredienteResponse createCategoriaIngrediente(CategoriaIngredienteRequest request);
    CategoriaIngredienteResponse getCategoriaIngredienteById(Long id);
    List<CategoriaIngredienteResponse> getCategoriaIngredienteByNombre(String nombre);
}
