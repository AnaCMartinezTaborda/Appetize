package com.appetize.repository;

import com.appetize.model.entity.CategoriaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Long> {
    boolean existsByNombreIgnoreCaseAndRestauranteId(String nombre, String restauranteId);
    List<CategoriaProducto> findByRestauranteIdAndNombreContainingIgnoreCase(String restauranteId, String nombre);
}
