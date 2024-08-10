package com.lucaezellner.pwdvalidator.domain.exception;

public class PasswordIsTooShortException extends InvalidPasswordException {
    public PasswordIsTooShortException() {
        super("A senha informada deve conter pelo menos 9 caracteres.");
    }
}