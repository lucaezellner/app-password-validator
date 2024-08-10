package com.lucaezellner.pwdvalidator.domain.exception;

public class PasswordHasNoDigitsException extends InvalidPasswordException {
    public PasswordHasNoDigitsException() {
        super("A senha informada precisa conter pelo menos 1 dígito.");
    }
}