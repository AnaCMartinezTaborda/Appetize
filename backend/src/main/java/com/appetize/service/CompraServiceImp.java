package com.appetize.service;

import com.appetize.model.dto.request.compra.CompraRequest;
import com.appetize.model.entity.Compra;
import com.appetize.model.entity.DetalleCompra;
import com.appetize.model.entity.Empleado;
import com.appetize.model.entity.Inventario;
import com.appetize.repository.CompraRepository;
import com.appetize.repository.EmpleadoRepository;
import com.appetize.repository.InventarioRepository;
import com.appetize.service.abstraction.CompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompraServiceImp implements CompraService {

    private final CompraRepository compraRepository;
    private final EmpleadoRepository empleadoRepository;
    private final InventarioRepository inventarioRepository;

    @Override
    public void createCompra(CompraRequest request) {
        String cedula = SecurityContextHolder.getContext().getAuthentication().getName();
        Empleado empleado = empleadoRepository.findByCedula(cedula).orElseThrow(() -> new NoSuchElementException("El empleado no existe"));

        Compra compra = new Compra();

        compra.setProveedor(request.getProveedor());
        Long idExternoMax = compraRepository.findMaxIdExternoByRestaurante(empleado.getRestaurante().getId()).orElse(0L);
        compra.setIdExterno(idExternoMax + 1L);

        compra.setTotal(request.getDetalles().stream()
                .map(CompraRequest.DetalleCompraRequest::getCosto)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
        );


        compra.setRestaurante(empleado.getRestaurante());

        List<DetalleCompra> detalles = request.getDetalles().stream()
                .map(detalleRequest -> {
                    DetalleCompra detalle = new DetalleCompra();
                    detalle.setCosto(detalleRequest.getCosto());

                    Inventario inventario = inventarioRepository.findById(detalleRequest.getInventarioId()).orElseThrow(() -> new NoSuchElementException("El producto de inventario no existe"));
                    inventario.setCantidad(detalleRequest.getCantidad());
                    inventario.setCantidadHistorica(inventario.getCantidadHistorica().add(detalleRequest.getCantidad()));
                    inventario.setCostoHistorico(inventario.getCostoHistorico().add(detalle.getCosto()));
                    inventario.setCostoUnitario(
                            inventario.getCostoHistorico()
                                    .divide(inventario.getCantidadHistorica(), 2, RoundingMode.HALF_UP)
                    );

                    detalle.setInventario(inventario);
                    detalle.setCompra(compra);
                    detalle.setRestaurante(empleado.getRestaurante());
                    return detalle;
                })
                .collect(Collectors.toList());

        compra.setDetalles(detalles);

        compra.setFechaCompra(request.getFechaCompra());
        compraRepository.save(compra);
    }
}
