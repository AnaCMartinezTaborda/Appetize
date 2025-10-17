package com.appetize.service;

import com.appetize.model.dto.request.auth.LoginRequest;
import com.appetize.model.dto.request.auth.RegisterRequest;
import com.appetize.model.entity.Empleado;
import com.appetize.model.enums.TipoEnum;
import com.appetize.repository.EmpleadoRepository;
import com.appetize.security.JwtUtils;
import com.appetize.service.abstraction.AuthService;
import com.appetize.utils.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    private final EmpleadoRepository empleadoRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

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

    @Override
    public String login(LoginRequest request) {
        String cedula = request.getCedula();
        String contraseña = request.getContraseña();

        Empleado empleado = empleadoRepository.findByCedula(request.getCedula()).orElseThrow( () -> new NoSuchElementException("Cédula o contraseña incorrectos"));

        if (cedula.isBlank()) {
            throw new NoSuchElementException("Cédula o contraseña incorrectos");
        }
        if (contraseña.isBlank()) {
            throw new NoSuchElementException("Cédula o contraseña incorrectos");
        }
        Authentication authentication = this.authenticate(cedula, contraseña);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        empleado.setLastSession(LocalDateTime.now());
        empleadoRepository.save(empleado);

        return jwtUtils.createToken(authentication);

    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        if (userDetails == null){
            throw new IllegalArgumentException("Cédula o contraseña incorrectos");
        }
        if (!encoder.matches(password, userDetails.getPassword())){
            throw new IllegalArgumentException("Cédula o contraseña incorrectos");
        }
        return new UsernamePasswordAuthenticationToken(email, userDetails.getPassword(), userDetails.getAuthorities());
    }
}
