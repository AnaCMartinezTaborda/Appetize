package com.appetize.service;

import com.appetize.model.dto.request.restaurante.RestauranteRequest;
import com.appetize.model.entity.Empleado;
import com.appetize.model.entity.Restaurante;
import com.appetize.model.mapper.RestauranteMapper;
import com.appetize.repository.EmpleadoRepository;
import com.appetize.repository.RestauranteRepository;
import com.appetize.service.abstraction.RestauranteService;
import com.appetize.utils.EmailValidator;
import com.appetize.utils.PhoneValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RestauranteServiceImp implements RestauranteService {

    private final EmpleadoRepository empleadoRepository;
    private final RestauranteRepository restauranteRepository;
    private final RestauranteMapper restauranteMapper;

    @Override
    public void createRestaurante(RestauranteRequest request) {
        if (request.getNombre() == null || request.getNombre().isBlank()) throw new IllegalArgumentException("El nombre no puede estar vacío");
        if (request.getEmail() == null || request.getEmail().isBlank()) throw new IllegalArgumentException("El correo electrónico no puede estar vacío");
        if (request.getTelefono() == null || request.getTelefono().isBlank()) throw new IllegalArgumentException("El teléfono no puede estar vacío");

        String cedula = SecurityContextHolder.getContext().getAuthentication().getName();
        Empleado empleado = empleadoRepository.findByCedula(cedula).orElseThrow(() -> new NoSuchElementException("El empleado no existe"));

        boolean isRestauranteExist = restauranteRepository.findByEmail(request.getEmail()).isPresent();

        if (isRestauranteExist){
            throw new DuplicateKeyException("El correo electrónico no se encuentra disponible");
        }

        if (!EmailValidator.isEmailValid(request.getEmail())) throw new IllegalArgumentException(
                "Ingrese un correo electrónico válido"
        );
        if (!PhoneValidator.isPhoneValid(request.getTelefono())) throw new IllegalArgumentException(
                "Ingrese un número de teléfono válido"
        );

        if (empleado.getRestaurante() != null) {
            throw new IllegalStateException("El empleado ya tiene un restaurante asociado");
        }

        Restaurante restaurante = restauranteMapper.requestToEntity(request);
        restauranteRepository.save(restaurante);
        empleado.setRestaurante(restaurante);
        empleadoRepository.save(empleado);
    }
}
