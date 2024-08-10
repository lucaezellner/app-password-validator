package com.lucaezellner.pwdvalidator.application.usecase;

import com.lucaezellner.pwdvalidator.application.dto.PasswordValidationResponseDto;
import com.lucaezellner.pwdvalidator.domain.entity.Password;
import org.springframework.stereotype.Component;

@Component
public class ValidatePasswordUseCase {

    public PasswordValidationResponseDto execute(String password) {
        Password senha = new Password(password);
        if (!senha.isValid()) {
            return new PasswordValidationResponseDto(false, senha.getValidationStatus().getDescription());
        }
        return new PasswordValidationResponseDto(true, "");
    }
}
