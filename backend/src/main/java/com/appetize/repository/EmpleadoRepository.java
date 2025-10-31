package com.appetize.repository;

import com.appetize.model.entity.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, String> {
    Optional<Empleado> findByCedula(String cedula);
    Optional<Empleado> findById(String id);
    Page<Empleado> findByRestauranteId(String restauranteId, Pageable pageable);
}
