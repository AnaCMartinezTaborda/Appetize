package com.appetize.repository;

import com.appetize.model.entity.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
    @Query("SELECT COALESCE(MAX(c.idExterno), 0) FROM ingredientes c WHERE c.restaurante.id = :restauranteId")
    Long findMaxIdExternoByRestaurante(@Param("restauranteId") String restauranteId);

    List<Ingrediente> findByRestauranteIdAndNombreContainingIgnoreCase(String restauranteId, String nombre);
    boolean existsByNombreIgnoreCaseAndRestauranteId(String nombre, String restauranteId);
}
