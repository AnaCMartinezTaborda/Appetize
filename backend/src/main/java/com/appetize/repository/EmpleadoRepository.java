package com.appetize.repository;

import com.appetize.model.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, String> {
    Optional<Empleado> findByNombre(String name);
}
