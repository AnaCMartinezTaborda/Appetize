package com.appetize.model.dto.request.compra;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompraRequest {
    private String proveedor;
    private List<DetalleCompraRequest> detalles;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fechaCompra;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DetalleCompraRequest {
        private Long inventarioId;
        private BigDecimal cantidad;
        private BigDecimal costo;
    }
}
