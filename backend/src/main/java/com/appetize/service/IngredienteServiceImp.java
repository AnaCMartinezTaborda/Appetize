package com.appetize.service;

import com.appetize.model.dto.request.ingrediente.IngredienteRequest;
import com.appetize.model.dto.response.IngredienteResponse;
import com.appetize.model.entity.Ingrediente;
import com.appetize.model.mapper.IngredienteMapper;
import com.appetize.repository.CategoriaIngredienteRepository;
import com.appetize.repository.IngredienteRepository;
import com.appetize.repository.InventarioRepository;
import com.appetize.service.abstraction.IngredienteService;
import com.appetize.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IngredienteServiceImp implements IngredienteService {
    private final IngredienteRepository ingredienteRepository;
    private final CategoriaIngredienteRepository categoriaIngredienteRepository;
    private final InventarioRepository inventarioRepository;
    private final IngredienteMapper mapper;
    private final SecurityUtils securityUtils;


    @Override
    @Transactional
    public IngredienteResponse createIngrediente(IngredienteRequest request) {
        boolean exists = ingredienteRepository.existsByNombreIgnoreCaseAndRestauranteId(request.getNombre().trim(), securityUtils.getRestauranteId());
        if (exists){
            throw new DuplicateKeyException("El ingrediente ya existe");
        }

        if (request.getNombre() == null || request.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del ingrediente es obligatorio.");
        }

        if (request.getUnidadMedida() == null || request.getUnidadMedida().toString().trim().isEmpty()){
            throw new IllegalArgumentException("La unidad de medida obligatoria.");
        }

        if (request.getNecesario() == null){
            throw new IllegalArgumentException("El necesario es obligatorio.");
        }

        if (request.getCategoria() == null){
            throw new IllegalArgumentException("La categoría es obligatoria.");
        }

        if (request.getInventario() == null){
            throw new IllegalArgumentException("El inventario es obligatorio.");
        }

        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre(request.getNombre());
        ingrediente.setIdExterno(ingredienteRepository.findMaxIdExternoByRestaurante(securityUtils.getRestauranteId()) + 1);
        ingrediente.setCantidad(5);
        ingrediente.setUnidadMedida(request.getUnidadMedida());
        ingrediente.setNecesario(BigDecimal.ZERO);
        ingrediente.setCategoria(categoriaIngredienteRepository.findById(request.getCategoria()).get());
        ingrediente.setInventario(inventarioRepository.findById(request.getInventario()).get());
        ingrediente.setRestaurante(securityUtils.getRestaurante());

        return mapper.entityToDto(ingrediente);
    }

    @Override
    public IngredienteResponse getIngredienteById(Long id) {
        Ingrediente ingrediente = ingredienteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("El ingrediente no existe"));

        return mapper.entityToDto(ingrediente);
    }

    @Override
    public List<IngredienteResponse> getIngredientesByNombre(String nombre) {
        List<Ingrediente> ingredientes = ingredienteRepository.findByRestauranteIdAndNombreContainingIgnoreCase(securityUtils.getRestauranteId(), nombre);

        return ingredientes.stream()
                .map(mapper::entityToDto)
                .toList();
    }
}
