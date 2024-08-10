package com.lucaezellner.pwdvalidator.domain.entity;

import com.lucaezellner.pwdvalidator.domain.enums.ValidationStatus;
import com.lucaezellner.pwdvalidator.domain.util.RegexUtil;
import lombok.Data;

@Data
public class Password {
    private String value;
    private ValidationStatus validationStatus;

    public Password(String value) {
        this.value = value;
        this.validate(this.value);
    }

    public boolean isValid() {
        return this.validationStatus != null && this.validationStatus == ValidationStatus.OK;
    }

    private void validate(String valor) {
        if (valor == null || valor.length() < 9) {
            this.validationStatus = ValidationStatus.TOO_SHORT;
        }
        else if (!RegexUtil.hasDigit(valor)) {
            this.validationStatus = ValidationStatus.NO_DIGITS;
        }
        else if (!RegexUtil.hasLowerCase(valor)) {
            this.validationStatus = ValidationStatus.NO_LOWER_CASE;
        }
        else if (!RegexUtil.hasUpperCase(valor)) {
            this.validationStatus = ValidationStatus.NO_UPPER_CASE;
        }
        else if (!RegexUtil.hasSpecialCharacter(valor)) {
            this.validationStatus = ValidationStatus.NO_SPECIAL_CHARACTERS;
        }
        else if (RegexUtil.hasRepeatedCharacters(valor)) {
            this.validationStatus = ValidationStatus.REPEATED_CHARACTERS;
        }
        else {
            this.validationStatus = ValidationStatus.OK;
        }
    }
}
