package com.appetize.repository;

import com.appetize.model.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    @Query("SELECT COALESCE(MAX(c.idExterno), 0) FROM compras c WHERE c.restaurante.id = :restauranteId")
    Optional<Long> findMaxIdExternoByRestaurante(@Param("restauranteId") String restauranteId);
}
