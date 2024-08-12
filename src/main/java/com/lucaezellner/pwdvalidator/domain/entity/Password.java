package com.lucaezellner.pwdvalidator.domain.entity;

import com.lucaezellner.pwdvalidator.domain.enums.ValidationStatus;
import com.lucaezellner.pwdvalidator.domain.util.RegexUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Password {
    private String value;
    private List<ValidationStatus> validationErrors = new ArrayList<>();

    public Password(String value) {
        this.value = value;
        this.validate();
    }

    public boolean isValid() {
        return validationErrors.isEmpty();
    }

    private void validate() {
        if (this.value.length() < 9) {
            validationErrors.add(ValidationStatus.TOO_SHORT);
        }
        if (!RegexUtil.hasDigit(this.value)) {
            validationErrors.add(ValidationStatus.NO_DIGITS);
        }
        if (!RegexUtil.hasLowerCase(this.value)) {
            validationErrors.add(ValidationStatus.NO_LOWER_CASE);
        }
        if (!RegexUtil.hasUpperCase(this.value)) {
            validationErrors.add(ValidationStatus.NO_UPPER_CASE);
        }
        if (!RegexUtil.hasSpecialCharacter(this.value)) {
            validationErrors.add(ValidationStatus.NO_SPECIAL_CHARACTERS);
        }
        if (RegexUtil.hasRepeatedCharacters(this.value)) {
            validationErrors.add(ValidationStatus.REPEATED_CHARACTERS);
        }
    }
}
