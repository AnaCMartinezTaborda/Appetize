package com.appetize.service;

import com.appetize.model.dto.request.LoginRequest;
import com.appetize.model.dto.request.RegisterRequest;
import com.appetize.model.entity.Empleado;
import com.appetize.model.enums.TipoEnum;
import com.appetize.repository.EmpleadoRepository;
import com.appetize.service.abstraction.EmpleadoService;
import com.appetize.utils.EmailValidator;
import com.appetize.utils.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImp implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final PasswordEncoder encoder;

    @Override
    public void register(RegisterRequest request) {
        boolean isCedulaExist = empleadoRepository.findByCedula(request.getCedula()).isPresent();


        if (request.getCedula() == null || request.getCedula().isBlank()) {
            throw new IllegalArgumentException("La cédula no puede estar vacía");
        }
        if (request.getContraseña().isBlank()) throw new NoSuchElementException("La contraseña no puede estar vacía");
        if (request.getNombre().isBlank()) throw new NoSuchElementException("El nombre no puede estar vacío");
        if (!PasswordValidator.isPasswordValid(request.getContraseña())) throw new IllegalArgumentException(
                "La contraseña debe tener mínimo 8 caracteres, una mayúscula, una minúscula, un número y un carácter especial."
        );
        if (isCedulaExist) throw new DuplicateKeyException("Esta cédula ya se encuentra en uso");


        Empleado empleado = new Empleado();
        empleado.setTipo(TipoEnum.ADMINISTRADOR);
        empleado.setCedula(request.getCedula());
        empleado.setNombre(request.getNombre());
        empleado.setContraseña(encoder.encode(request.getContraseña()));
        empleado.setCreatedAt(LocalDateTime.now());

        empleadoRepository.save(empleado);
    }

//    @Override
//    public String login(LoginRequest request) {
//        return "";
//    }
}
