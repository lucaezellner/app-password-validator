package com.lucaezellner.pwdvalidator.domain.util;

public class RegexUtil {
    public static boolean hasDigit(String value) {
        return value.matches(".*[0-9].*");
    }

    public static boolean hasLowerCase(String value) {
        return value.matches(".*[a-z].*");
    }

    public static boolean hasUpperCase(String value) {
        return value.matches(".*[A-Z].*");
    }

    public static boolean hasSpecialCharacter(String value) {
        return value.matches(".*[@!#$%^&*()-+].*");
    }

    public static boolean hasRepeatedCharacters(String value) {
        return value.matches(".*(.)\\1.*");
    }
}
