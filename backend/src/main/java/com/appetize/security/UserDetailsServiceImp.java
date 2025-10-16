package com.appetize.security;

import com.appetize.model.entity.Empleado;
import com.appetize.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private EmpleadoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String cedula) throws UsernameNotFoundException {

        Empleado empleado = repository.findByCedula(cedula)
                .orElseThrow(() -> new NoSuchElementException("Cedula o contraseña incorrectos"));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(empleado.getTipo().toString())));


        return new User(empleado.getCedula(),
                empleado.getContraseña(),
                authorityList
        );
    }
}
