package com.appetize.repository;

import com.appetize.model.entity.CategoriaIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaIngredienteRepository extends JpaRepository<CategoriaIngrediente, Long> {
    boolean existsByNombreIgnoreCaseAndRestauranteId(String nombre, String restauranteId);
    List<CategoriaIngrediente> findByRestauranteIdAndNombreContainingIgnoreCase(String restauranteId, String nombre);
}
