package com.appetize.service.abstraction;

import com.appetize.model.dto.request.compra.CompraRequest;
import com.appetize.model.dto.response.CompraResponse;
import org.springframework.data.domain.Page;


import java.time.LocalDateTime;

public interface CompraService {
    void createCompra(CompraRequest request);
    CompraResponse getCompraById(String id);
    CompraResponse getCompraByIdExterno(Long idExterno);
    Page<CompraResponse> getAllComprasPaged(LocalDateTime desde, LocalDateTime hasta, int page, int size);
}
