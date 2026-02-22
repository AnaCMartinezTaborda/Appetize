package com.appetize.utils;

import com.appetize.model.entity.Empleado;
import com.appetize.model.entity.Restaurante;
import com.appetize.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class SecurityUtils {

    private final EmpleadoRepository empleadoRepository;

    public Empleado getEmpleado() {
        String cedula = SecurityContextHolder.getContext().getAuthentication().getName();
        return empleadoRepository.findByCedula(cedula)
                .orElseThrow(() -> new NoSuchElementException("El empleado no existe con cédula: " + cedula));
    }

    public Restaurante getRestaurante() {
        return getEmpleado().getRestaurante();
    }

    public String getRestauranteId() {
        return getRestaurante().getId();
    }
}
