package com.lucaezellner.pwdvalidator.domain.entity;

import com.lucaezellner.pwdvalidator.domain.exception.*;
import com.lucaezellner.pwdvalidator.domain.util.RegexUtil;
import lombok.Data;

@Data
public class Password {
    private String value;
    private boolean valid;
    private Exception exception;

    public Password(String value) {
        this.value = value;
        this.validate(this.value);
    }

    private void validate(String valor) {
        if (valor == null || valor.length() < 9) {
            this.setValidationAttributes(false, new PasswordIsTooShortException());
        }
        else if (!RegexUtil.hasDigit(valor)) {
            this.setValidationAttributes(false, new PasswordHasNoDigitsException());
        }
        else if (!RegexUtil.hasLowerCase(valor)) {
            this.setValidationAttributes(false, new PasswordHasNoLowerCaseException());
        }
        else if (!RegexUtil.hasUpperCase(valor)) {
            this.setValidationAttributes(false, new PasswordHasNoUpperCaseException());
        }
        else if (!RegexUtil.hasSpecialCharacter(valor)) {
            this.setValidationAttributes(false, new PasswordHasNoSpecialCharactersException());
        }
        else if (RegexUtil.hasRepeatedCharacters(valor)) {
            this.setValidationAttributes(false, new PasswordHasRepeatedCharactersException());
        }
        else {
            this.setValidationAttributes(true, null);
        }
    }

    private void setValidationAttributes(boolean senhaValida, InvalidPasswordException exception) {
        this.valid = senhaValida;
        this.exception = exception;
    }
}
