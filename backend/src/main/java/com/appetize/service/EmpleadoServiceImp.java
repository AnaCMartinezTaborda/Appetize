package com.appetize.service;

import com.appetize.model.dto.request.empleado.EmpleadoRequest;
import com.appetize.model.dto.response.EmpleadoResponse;
import com.appetize.model.entity.Empleado;
import com.appetize.model.enums.TipoEnum;
import com.appetize.model.mapper.EmpleadoMapper;
import com.appetize.repository.EmpleadoRepository;
import com.appetize.service.abstraction.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImp implements EmpleadoService {

    private final EmpleadoRepository repository;
    private final PasswordEncoder encoder;
    private final EmpleadoMapper mapper;

    @Override
    public void createEmpleado(EmpleadoRequest request) {
        if (request.getCedula() == null || request.getCedula().isBlank()) throw new IllegalArgumentException("La cédula no puede estar vacía");
        if (request.getPassword() == null || request.getPassword().isBlank()) throw new IllegalArgumentException("La contraseña no puede estar vacía");
        if (request.getNombre() == null || request.getNombre().isBlank()) throw new IllegalArgumentException("El nombre no puede estar vacío");

        String cedulaAdmin = SecurityContextHolder.getContext().getAuthentication().getName();
        Empleado empleado = repository.findByCedula(cedulaAdmin).orElseThrow(() -> new NoSuchElementException("Este administrador no existe"));

        boolean isEmpleadoExist = repository.findByCedula(request.getCedula()).isPresent();

        if (isEmpleadoExist) throw new DuplicateKeyException("La cédula no se encuentra disponible");

        Empleado newEmpleado = new Empleado();

        newEmpleado.setTipo(TipoEnum.COCINERO);
        newEmpleado.setCedula(request.getCedula());
        newEmpleado.setNombre(request.getNombre());
        newEmpleado.setPassword(encoder.encode(request.getPassword()));
        newEmpleado.setRestaurante(empleado.getRestaurante());
        newEmpleado.setCreatedAt(LocalDateTime.now());

        repository.save(newEmpleado);
    }

    @Override
    public EmpleadoResponse getEmpleadoById(String id) {
        Empleado empleado = repository.findById(id).orElseThrow(() -> new NoSuchElementException("El empleado no existe"));
        return mapper.entityToDto(empleado);
    }

    @Override
    public EmpleadoResponse getPropioEmpleado(){
        String cedulaAdmin = SecurityContextHolder.getContext().getAuthentication().getName();
        Empleado empleado = repository.findByCedula(cedulaAdmin).orElseThrow(() -> new NoSuchElementException("Este administrador no existe"));
        return mapper.entityToDto(empleado);
    }

    @Override
    public List<EmpleadoResponse> getAllEmpleadosByRestaurante(){
        String cedula = SecurityContextHolder.getContext().getAuthentication().getName();

        Empleado empleado = repository.findByCedula(cedula)
                .orElseThrow(() -> new NoSuchElementException("Empleado administrador no encontrado"));

        List<Empleado> empleados = repository.findByRestauranteId(empleado.getRestaurante().getId());

        return empleados.stream()
                .map(mapper::entityToDto)
                .toList();
    }
}
