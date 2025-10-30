package com.appetize.service.abstraction;

import com.appetize.model.dto.request.compra.CompraRequest;
import com.appetize.model.dto.response.CompraResponse;
import java.time.LocalDateTime;
import java.util.List;

public interface CompraService {
    void createCompra(CompraRequest request);
    CompraResponse getCompraById(Long id);
    CompraResponse getCompraByIdExterno(Long idExterno);
    List<CompraResponse> getAllCompras(LocalDateTime desde, LocalDateTime hasta);
}
