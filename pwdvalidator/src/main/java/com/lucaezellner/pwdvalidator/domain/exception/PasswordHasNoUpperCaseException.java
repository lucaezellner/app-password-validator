package com.lucaezellner.pwdvalidator.domain.exception;

public class PasswordHasNoUpperCaseException extends InvalidPasswordException {
    public PasswordHasNoUpperCaseException() {
        super("A senha informada deve conter pelo 1 letra maiúscula.");
    }
}