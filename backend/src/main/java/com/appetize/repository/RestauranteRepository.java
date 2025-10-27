package com.appetize.repository;

import com.appetize.model.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, String> {
    Optional<Restaurante> findByEmail(String email);
}
