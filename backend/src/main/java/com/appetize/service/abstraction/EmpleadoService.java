package com.appetize.service.abstraction;


import com.appetize.model.dto.request.auth.LoginRequest;
import com.appetize.model.dto.request.auth.RegisterRequest;

public interface EmpleadoService {
    void register (RegisterRequest request);
    String login (LoginRequest request);
}
