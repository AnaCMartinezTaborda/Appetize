package com.appetize.utils;

import java.util.regex.Pattern;

public class PasswordValidator {
    private static final String PASSWORD_REGEX =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}:;\"',.<>?]).{8,}$";

    private PasswordValidator() {
    }

    public static boolean isPasswordValid(String password) {
        return password != null && !password.isBlank() && Pattern.matches(PASSWORD_REGEX, password);
    }
}

