package com.appetize.service.abstraction;


import com.appetize.model.dto.request.auth.LoginRequest;
import com.appetize.model.dto.request.auth.RegisterRequest;
import com.appetize.model.dto.response.LoginResponse;

public interface AuthService {
    void register (RegisterRequest request);
    LoginResponse login (LoginRequest request);
}
