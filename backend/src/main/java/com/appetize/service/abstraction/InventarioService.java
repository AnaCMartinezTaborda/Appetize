package com.appetize.service.abstraction;

import com.appetize.model.dto.request.inventario.InventarioRequest;
import com.appetize.model.dto.response.InventarioResponse;
import com.appetize.model.entity.Inventario;

import java.util.List;

public interface InventarioService {
    void createInventario(InventarioRequest request);
    List<InventarioResponse> getInventarioByNombre(String nombre);
}
