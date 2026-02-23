package com.appetize.service;

import com.appetize.model.dto.request.categoriaproducto.CategoriaProductoRequest;
import com.appetize.model.dto.response.CategoriaProductoResponse;
import com.appetize.model.entity.CategoriaProducto;
import com.appetize.model.mapper.CategoriaProductoMapper;
import com.appetize.repository.CategoriaProductoRepository;
import com.appetize.service.abstraction.CategoriaProductoService;
import com.appetize.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoriaProductoServiceImp implements CategoriaProductoService {
    private final CategoriaProductoRepository categoriaProductoRepository;
    private final CategoriaProductoMapper mapper;
    private final SecurityUtils securityUtils;


    @Override
    @Transactional
    public CategoriaProductoResponse createCategoriaProducto(CategoriaProductoRequest request) {
        boolean exists = categoriaProductoRepository.existsByNombreIgnoreCaseAndRestauranteId(request.getNombre(), securityUtils.getRestauranteId());

        if (exists){
            throw new DuplicateKeyException("Ya existe una categoría de productos con este nombre");
        }

        CategoriaProducto categoriaProducto = new CategoriaProducto();

        categoriaProducto.setNombre(request.getNombre());
        categoriaProducto.setRestaurante(securityUtils.getRestaurante());

        return mapper.entityToDto(categoriaProductoRepository.save(categoriaProducto));
    }

    @Override
    public List<CategoriaProductoResponse> getCategoriaProductosByNombre(String nombre) {
        List<CategoriaProducto> categoriasProducto = categoriaProductoRepository.findByRestauranteIdAndNombreContainingIgnoreCase(securityUtils.getRestauranteId(), nombre);

        return categoriasProducto.stream()
                .map(mapper::entityToDto)
                .toList();
    }

    @Override
    public CategoriaProductoResponse getCategoriaProductoById(Long id){
        CategoriaProducto categoriaProducto = categoriaProductoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("La categoría de producto no existe"));
        return mapper.entityToDto(categoriaProducto);
    }
}
