package com.appetize.service;

import com.appetize.model.dto.request.empleado.EmpleadoRequest;
import com.appetize.model.dto.request.empleado.UpdatePasswordRequest;
import com.appetize.model.dto.response.EmpleadoResponse;
import com.appetize.model.entity.Empleado;
import com.appetize.model.enums.TipoEnum;
import com.appetize.model.mapper.EmpleadoMapper;
import com.appetize.repository.EmpleadoRepository;
import com.appetize.service.abstraction.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImp implements EmpleadoService {

    private final EmpleadoRepository repository;
    private final PasswordEncoder encoder;
    private final EmpleadoMapper mapper;

    @Override
    @Transactional
    public void createEmpleado(EmpleadoRequest request) {
        if (request.getCedula() == null || request.getCedula().isBlank()) throw new IllegalArgumentException("La cédula no puede estar vacía");
        if (request.getPassword() == null || request.getPassword().isBlank()) throw new IllegalArgumentException("La contraseña no puede estar vacía");
        if (request.getNombre() == null || request.getNombre().isBlank()) throw new IllegalArgumentException("El nombre no puede estar vacío");

        String cedulaAdmin = SecurityContextHolder.getContext().getAuthentication().getName();
        Empleado empleado = repository.findByCedula(cedulaAdmin).orElseThrow(() -> new NoSuchElementException("Este administrador no existe"));

        boolean isEmpleadoExist = repository.findByCedula(request.getCedula()).isPresent();

        if (isEmpleadoExist) throw new DuplicateKeyException("Esta cédula ya está en uso");

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
    @Transactional
    public void updatePasswordPropia(UpdatePasswordRequest request){
        String cedula = SecurityContextHolder.getContext().getAuthentication().getName();
        Empleado empleado = repository.findByCedula(cedula).orElseThrow(() -> new NoSuchElementException("Este empleado no existe"));

        empleado.setPassword(encoder.encode(request.getPassword()));
        repository.save(empleado);
    }

    @Override
    public Page<EmpleadoResponse> getAllEmpleadosByRestaurantePaged(int page, int size) {
        String cedula = SecurityContextHolder.getContext().getAuthentication().getName();

        Empleado empleado = repository.findByCedula(cedula)
                .orElseThrow(() -> new NoSuchElementException("Empleado administrador no encontrado"));

        Pageable pageable = PageRequest.of(page, size, Sort.by("nombre").ascending());

        Page<Empleado> empleadosPage = repository.findByRestauranteId(empleado.getRestaurante().getId(), pageable);

        return empleadosPage.map(mapper::entityToDto);
    }


    @Override
    @Transactional
    public void updateEmpleado(EmpleadoRequest request, String id){
        Empleado empleado = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("El empleado no existe"));

        Optional.ofNullable(request.getNombre())
                .filter(nombre -> !nombre.isBlank())
                .ifPresent(empleado::setNombre);

        Optional.ofNullable(request.getCedula())
                .filter(cedula -> !cedula.isBlank())
                .ifPresent(cedula -> {
                    Empleado empleadoConCedula = repository.findByCedula(cedula).orElse(null);
                    if (empleadoConCedula != null && !empleadoConCedula.getId().equals(id)) {
                        throw new DuplicateKeyException("Esta cédula ya se encuentra en uso");
                    }
                    empleado.setCedula(cedula);
                });

        Optional.ofNullable(request.getPassword())
                .filter(p -> !p.isBlank())
                .ifPresent(p -> empleado.setPassword(encoder.encode(p)));

        empleado.setUpdatedAt(LocalDateTime.now());
        repository.save(empleado);
    }
}
