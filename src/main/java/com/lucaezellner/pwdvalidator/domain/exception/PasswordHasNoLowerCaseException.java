package com.lucaezellner.pwdvalidator.domain.exception;

public class PasswordHasNoLowerCaseException extends InvalidPasswordException {
    public PasswordHasNoLowerCaseException() {
        super("A senha informada deve conter pelo 1 letra minúscula.");
    }
}