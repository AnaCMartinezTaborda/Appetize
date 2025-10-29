package com.appetize.utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomPasswordGenerator {

    private static final String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITOS = "0123456789";
    private static final String ESPECIALES = "!@#$%^&*()_+-={}:;\"',.<>?";

    private static final String TODOS = MAYUSCULAS + MINUSCULAS + DIGITOS + ESPECIALES;

    private static final SecureRandom random = new SecureRandom();

    public static String generar() {
        List<Character> password = new ArrayList<>();

        password.add(MAYUSCULAS.charAt(random.nextInt(MAYUSCULAS.length())));
        password.add(MINUSCULAS.charAt(random.nextInt(MINUSCULAS.length())));
        password.add(DIGITOS.charAt(random.nextInt(DIGITOS.length())));
        password.add(ESPECIALES.charAt(random.nextInt(ESPECIALES.length())));

        for (int i = password.size(); i < 8; i++) {
            password.add(TODOS.charAt(random.nextInt(TODOS.length())));
        }

        Collections.shuffle(password, random);

        StringBuilder sb = new StringBuilder();
        for (Character c : password) {
            sb.append(c);
        }

        return sb.toString();
    }
}
