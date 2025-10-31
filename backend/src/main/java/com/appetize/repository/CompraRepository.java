package com.appetize.repository;

import com.appetize.model.entity.Compra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    @Query("SELECT COALESCE(MAX(c.idExterno), 0) FROM compras c WHERE c.restaurante.id = :restauranteId")
    Optional<Long> findMaxIdExternoByRestaurante(@Param("restauranteId") String restauranteId);

    @Query("SELECT c FROM compras c WHERE c.restaurante.id = :restauranteId AND c.fechaCompra BETWEEN :inicio AND :fin ORDER BY c.fechaCompra DESC")
    Page<Compra> findByFechaCompraBetweenAndRestaurante(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin,
            @Param("restauranteId") String restauranteId,
            Pageable pageable
    );

    Optional<Compra> findByIdExternoAndRestauranteId(Long idExterno, String restauranteId);

    Page<Compra> findAllByRestauranteId(String restauranteId, Pageable pageable);
}