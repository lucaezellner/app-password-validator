package com.lucaezellner.pwdvalidator.application.usecase;

import com.lucaezellner.pwdvalidator.application.dto.PasswordValidationResponseDto;
import com.lucaezellner.pwdvalidator.domain.entity.Password;
import com.lucaezellner.pwdvalidator.domain.enums.ValidationStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ValidatePasswordUseCase {

    public PasswordValidationResponseDto execute(String pwdValue) {
        List<String> errors = new ArrayList<>();
        if (pwdValue != null) {
            Password password = new Password(pwdValue);
            if (!password.isValid()) {
                errors = password.getValidationErrors().stream()
                        .map(ValidationStatus::getDescription)
                        .collect(Collectors.toList());
                return new PasswordValidationResponseDto(false, ValidationStatus.GENERIC_ERROR.getDescription(), errors);
            }
            return new PasswordValidationResponseDto(true, ValidationStatus.OK.getDescription(), errors);
        }
        return new PasswordValidationResponseDto(false, ValidationStatus.NULL_PASSWORD.getDescription(), errors);
    }
}
