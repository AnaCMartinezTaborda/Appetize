package com.appetize.service;

import com.appetize.model.dto.request.categoriaingrediente.CategoriaIngredienteRequest;
import com.appetize.model.dto.response.CategoriaIngredienteResponse;
import com.appetize.model.entity.CategoriaIngrediente;
import com.appetize.model.mapper.CategoriaIngredienteMapper;
import com.appetize.repository.CategoriaIngredienteRepository;
import com.appetize.service.abstraction.CategoriaIngredienteService;
import com.appetize.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoriaIngredienteServiceImp implements CategoriaIngredienteService {
    private final CategoriaIngredienteRepository categoriaIngredienteRepository;
    private final CategoriaIngredienteMapper mapper;
    private final SecurityUtils securityUtils;


    @Override
    @Transactional
    public CategoriaIngredienteResponse createCategoriaIngrediente(CategoriaIngredienteRequest request) {
        boolean exists = categoriaIngredienteRepository.existsByNombreIgnoreCaseAndRestauranteId(request.getNombre(), securityUtils.getRestauranteId());

        if (exists){
            throw new DuplicateKeyException("Ya existe una categoría de ingredientes con este nombre");
        }

        CategoriaIngrediente categoriaIngrediente = new CategoriaIngrediente();

        categoriaIngrediente.setNombre(request.getNombre());
        categoriaIngrediente.setRestaurante(securityUtils.getRestaurante());

        return mapper.entityToDto(categoriaIngredienteRepository.save(categoriaIngrediente));
    }

    @Override
    public List<CategoriaIngredienteResponse> getCategoriaIngredienteByNombre(String nombre) {
        List<CategoriaIngrediente> categoriasIngrediente = categoriaIngredienteRepository.findByRestauranteIdAndNombreContainingIgnoreCase(securityUtils.getRestauranteId(), nombre);

        return categoriasIngrediente.stream()
                .map(mapper::entityToDto)
                .toList();
    }

    @Override
    public CategoriaIngredienteResponse getCategoriaIngredienteById(Long id){
        CategoriaIngrediente categoriaIngrediente = categoriaIngredienteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("La categoría de ingredientes no existe"));
        return mapper.entityToDto(categoriaIngrediente);
    }
}
