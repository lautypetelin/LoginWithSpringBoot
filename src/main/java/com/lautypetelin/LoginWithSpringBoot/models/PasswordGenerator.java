package com.lautypetelin.LoginWithSpringBoot.models;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PasswordGenerator {

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_+=<>?/{}~|";
    private static final String ALL_ALLOWED = UPPERCASE + LOWERCASE + DIGITS + SYMBOLS;

    public static String generatePassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException("La longitud debe ser al menos de 8 caracteres.");
        }

        SecureRandom random = new SecureRandom();

        StringBuilder password = new StringBuilder(length);

        // Asegurar que haya al menos un carácter de cada tipo requerido
        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SYMBOLS.charAt(random.nextInt(SYMBOLS.length())));

        // Completar el resto de la contraseña con caracteres aleatorios
        for (int i = 4; i < length; i++) {
            password.append(ALL_ALLOWED.charAt(random.nextInt(ALL_ALLOWED.length())));
        }

        // Mezclar la contraseña para evitar patrones predecibles
        List<Character> passwordChars = new ArrayList<>(password.length());
        for (char c : password.toString().toCharArray()) {
            passwordChars.add(c);
        }
        Collections.shuffle(passwordChars);

        StringBuilder shuffledPassword = new StringBuilder(length);
        for (char c : passwordChars) {
            shuffledPassword.append(c);
        }

        return shuffledPassword.toString();
    }
}