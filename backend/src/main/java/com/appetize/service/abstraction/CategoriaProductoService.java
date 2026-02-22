package com.appetize.service.abstraction;

import com.appetize.model.dto.request.categoriaproducto.CategoriaProductoRequest;
import com.appetize.model.dto.response.CategoriaProductoResponse;

import java.util.List;

public interface CategoriaProductoService {
    CategoriaProductoResponse createCategoriaProducto(CategoriaProductoRequest request);
    CategoriaProductoResponse getCategoriaProductoById(Long id);
    List<CategoriaProductoResponse> getCategoriaProductosByNombre(String nombre);
}
