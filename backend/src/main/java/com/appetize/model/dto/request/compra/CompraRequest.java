package com.appetize.model.dto.request.compra;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotBlank(message = "El proveedor no puede estar vacío")
    private String proveedor;

    @NotNull(message = "Debe contener al menos un detalle de compra")
    private List<DetalleCompraRequest> detalles;

    @NotNull(message = "La fecha de compra no puede estar vacía")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fechaCompra;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DetalleCompraRequest {
        @NotNull(message = "El id del inventario no puede estar vacío")
        private String inventarioId;

        @NotNull(message = "La cantidad no puede ser nula")
        @Positive(message = "La cantidad debe ser mayor a 0")
        private BigDecimal cantidad;

        @NotNull(message = "El costo no puede ser nulo")
        @Positive(message = "El costo debe ser mayor a 0")
        private BigDecimal costo;
    }
}
