package com.lucaezellner.pwdvalidator.domain.exception;

public class PasswordHasNoSpecialCharactersException extends InvalidPasswordException {
    public PasswordHasNoSpecialCharactersException() {
        super("A senha informada precisa conter pelo menos 1 caracter especial.");
    }
}