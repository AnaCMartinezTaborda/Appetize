package com.appetize.model.mapper;

import com.appetize.model.dto.response.CompraResponse;
import com.appetize.model.dto.response.InventarioResponse;
import com.appetize.model.entity.Compra;
import com.appetize.model.entity.Inventario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompraMapper {

    public CompraResponse entityToDto(Compra entity){
        List<CompraResponse.DetalleCompraResponse> detalles = entity.getDetalles().stream()
                .map(detalleEntity -> {
                    Inventario inventario =  detalleEntity.getInventario();

                    InventarioResponse inventarioResponse = InventarioResponse.builder()
                            .id(inventario.getId())
                            .idExterno(inventario.getIdExterno())
                            .nombre(inventario.getNombre())
                            .cantidad(inventario.getCantidad())
                            .costoUnitario(inventario.getCostoUnitario())
                            .tipoInventario(inventario.getTipoInventario())
                            .unidadMedida(inventario.getUnidadMedida())
                            .build();

                    return CompraResponse.DetalleCompraResponse.builder()
                            .id(detalleEntity.getId())
                            .cantidad(detalleEntity.getCantidad())
                            .costo(detalleEntity.getCosto())
                            .inventario(inventarioResponse)
                            .build();
                })
                .toList();

        return CompraResponse.builder()
                .id(entity.getId())
                .idExterno(entity.getIdExterno())
                .proveedor(entity.getProveedor())
                .total(entity.getTotal())
                .detalles(detalles)
                .fechaCompra(entity.getFechaCompra())
                .build();
    }

    public List<CompraResponse> entityListToDtoList(List<Compra> entities) {
        return entities.stream()
                .map(this::entityToDto)
                .toList();
    }
}