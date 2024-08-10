package com.lucaezellner.pwdvalidator.domain.util;

public class RegexUtil {
    public static boolean hasDigit(String valor) {
        return valor.matches(".*[0-9].*");
    }

    public static boolean hasLowerCase(String valor) {
        return valor.matches(".*[a-z].*");
    }

    public static boolean hasUpperCase(String valor) {
        return valor.matches(".*[A-Z].*");
    }

    public static boolean hasSpecialCharacter(String valor) {
        return valor.matches(".*[@#$%^&*()-+].*");
    }

    public static boolean hasRepeatedCharacters(String valor) {
        return valor.matches(".*(.)\\1.*");
    }
}
