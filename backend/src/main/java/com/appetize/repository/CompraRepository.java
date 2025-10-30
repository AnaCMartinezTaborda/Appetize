package com.appetize.repository;

import com.appetize.model.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    @Query("SELECT COALESCE(MAX(c.idExterno), 0) FROM compras c WHERE c.restaurante.id = :restauranteId")
    Optional<Long> findMaxIdExternoByRestaurante(@Param("restauranteId") String restauranteId);

    @Query("SELECT c FROM compras c WHERE c.restaurante.id = :restauranteId AND c.fechaCompra BETWEEN :inicio AND :fin ORDER BY c.fechaCompra DESC")
    List<Compra> findByFechaCompraBetweenAndRestaurante(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin,
            @Param("restauranteId") String restauranteId
    );

    Optional<Compra> findByIdExternoAndRestauranteId(Long idExterno, String restauranteId);

    List<Compra> findAllByRestauranteId(String restauranteId);

}