package com.appetize.utils;

import java.util.regex.Pattern;

public class PasswordValidator {

    // Regex moderno para contraseña segura:
    // - mínimo 8 caracteres
    // - al menos una mayúscula
    // - al menos una minúscula
    // - al menos un número
    // - al menos un carácter especial
    private static final String PASSWORD_REGEX =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}:;\"',.<>?]).{8,}$";

    private PasswordValidator() {
    }

    public static boolean isPasswordValid(String password) {
        return password != null && !password.isBlank() && Pattern.matches(PASSWORD_REGEX, password);
    }
}

