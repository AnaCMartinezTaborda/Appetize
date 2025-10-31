package com.appetize.service;

import com.appetize.model.dto.request.inventario.InventarioRequest;
import com.appetize.model.dto.response.InventarioResponse;
import com.appetize.model.entity.Empleado;
import com.appetize.model.entity.Inventario;
import com.appetize.model.mapper.InventarioMapper;
import com.appetize.repository.EmpleadoRepository;
import com.appetize.repository.InventarioRepository;
import com.appetize.service.abstraction.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class InventarioServiceImp implements InventarioService {

    private final InventarioRepository inventarioRepository;
    private final EmpleadoRepository empleadoRepository;
    private final InventarioMapper inventarioMapper;

    @Override
    @Transactional
    public void createInventario(InventarioRequest request) {
        if (request.getNombre() == null || request.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del inventario es obligatorio.");
        }
        if (request.getTipoInventario() == null) {
            throw new IllegalArgumentException("El tipo de inventario es obligatorio.");
        }
        if (request.getUnidadMedida() == null) {
            throw new IllegalArgumentException("La unidad de medida es obligatoria.");
        }

        String cedula = SecurityContextHolder.getContext().getAuthentication().getName();
        Empleado empleado = empleadoRepository.findByCedula(cedula)
                .orElseThrow(() -> new NoSuchElementException("El empleado no existe."));

        if (empleado.getRestaurante() == null) {
            throw new IllegalStateException("El empleado no tiene un restaurante asociado.");
        }

        boolean existe = inventarioRepository.existsByNombreIgnoreCaseAndRestauranteId(
                request.getNombre().trim(),
                empleado.getRestaurante().getId()
        );

        if (existe) {
            throw new IllegalArgumentException("Ya existe un inventario con ese nombre en este restaurante.");
        }

        Inventario inventario = inventarioMapper.dtoToEntity(request);
        inventario.setRestaurante(empleado.getRestaurante());
        inventarioRepository.save(inventario);
    }

    public List<InventarioResponse> getInventarioByNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo ni vacío");
        }

        String cedula = SecurityContextHolder.getContext().getAuthentication().getName();
        Empleado empleado = empleadoRepository.findByCedula(cedula)
                .orElseThrow(() -> new NoSuchElementException("El empleado no existe"));

        List<Inventario> inventarios = inventarioRepository
                .findByRestauranteIdAndNombreContainingIgnoreCase(
                        empleado.getRestaurante().getId(), nombre
                );

        return inventarios.stream()
                .map(inventarioMapper::entityToDto)
                .toList();
    }

}
