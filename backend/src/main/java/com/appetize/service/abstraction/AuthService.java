package com.appetize.service.abstraction;


import com.appetize.model.dto.request.auth.LoginRequest;
import com.appetize.model.dto.request.auth.RegisterRequest;

public interface AuthService {
    void register (RegisterRequest request);
    String login (LoginRequest request);
}
