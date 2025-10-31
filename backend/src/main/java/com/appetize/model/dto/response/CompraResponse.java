package com.appetize.model.dto.response;

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
public class CompraResponse {
    private Long id;
    private Long idExterno;
    private String proveedor;
    private BigDecimal total;
    List<DetalleCompraResponse> detalles;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fechaCompra;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DetalleCompraResponse {
        private Long id;
        private BigDecimal cantidad;
        private BigDecimal costo;
        private InventarioResponse inventario;
    }
}