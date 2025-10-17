package com.appetize.utils;

import java.util.regex.Pattern;

public class PhoneValidator {

    private PhoneValidator() {} // Evita instanciación

    public static boolean isPhoneValid(String telefono) {
        String phoneRegex = "^3\\d{9}$";
        return telefono != null && Pattern.matches(phoneRegex, telefono);
    }
}
