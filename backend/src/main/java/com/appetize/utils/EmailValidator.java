package com.appetize.utils;

import java.util.regex.Pattern;

public class EmailValidator {

    private EmailValidator() {} // Evita instanciación

    public static boolean isEmailValid(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,7}$";

        return email != null && Pattern.matches(emailRegex, email);
    }
}

