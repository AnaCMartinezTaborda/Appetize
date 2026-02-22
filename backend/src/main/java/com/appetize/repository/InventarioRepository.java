package com.appetize.repository;

import com.appetize.model.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    @Query("SELECT COALESCE(MAX(c.idExterno), 0) FROM inventario c WHERE c.restaurante.id = :restauranteId")
    Long findMaxIdExternoByRestaurante(@Param("restauranteId") String restauranteId);

    List<Inventario> findByRestauranteIdAndNombreContainingIgnoreCase(String restauranteId, String nombre);
    boolean existsByNombreIgnoreCaseAndRestauranteId(String nombre, String restauranteId);
}
