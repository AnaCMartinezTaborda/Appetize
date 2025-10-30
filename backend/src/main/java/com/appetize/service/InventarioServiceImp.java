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
    public void createInventario(InventarioRequest request){

        String cedula = SecurityContextHolder.getContext().getAuthentication().getName();
        Empleado empleado = empleadoRepository.findByCedula(cedula).orElseThrow(() -> new NoSuchElementException("El empleado no existe"));

        Inventario inventario = new Inventario();

        inventario.setRestaurante(empleado.getRestaurante());

        Long maxIdExterno = inventarioRepository.findMaxIdExternoByRestaurante(empleado.getRestaurante().getId()).orElse(0L);
        inventario.setIdExterno(maxIdExterno + 1L);

        inventario.setTipoInventario(request.getTipoInventario());
        inventario.setUnidadMedida(request.getUnidadMedida());
        inventario.setNombre(request.getNombre());

        inventarioRepository.save(inventario);
    }

    public List<InventarioResponse> getInventarioByNombre(String nombre){
        String cedula = SecurityContextHolder.getContext().getAuthentication().getName();
        Empleado empleado = empleadoRepository.findByCedula(cedula).orElseThrow(() -> new NoSuchElementException("El empleado no existe"));
        List<Inventario> inventarios = inventarioRepository.findByRestauranteIdAndNombreContainingIgnoreCase(empleado.getRestaurante().getId(), nombre);

        return inventarios.stream()
                .map(inventarioMapper::entityToDto)
                .toList();

    }
}
