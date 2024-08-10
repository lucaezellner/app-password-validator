package com.lucaezellner.pwdvalidator.domain.exception;

public class PasswordHasRepeatedCharactersException extends InvalidPasswordException {
    public PasswordHasRepeatedCharactersException() {
        super("A senha informada não pode conter caracteres repetidos.");
    }
}