package com.lucaezellner.pwdvalidator.application.usecase;

import com.lucaezellner.pwdvalidator.application.dto.PasswordValidationResponseDto;
import com.lucaezellner.pwdvalidator.domain.entity.Password;
import com.lucaezellner.pwdvalidator.domain.enums.ValidationStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ValidatePasswordUseCase {
    private static final Logger logger = LoggerFactory.getLogger(ValidatePasswordUseCase.class);

    public PasswordValidationResponseDto execute(String pwdValue) {
        List<String> errors = new ArrayList<>();
        if (pwdValue != null) {
            Password password = new Password(pwdValue);
            if (!password.isValid()) {
                errors = password.getValidationErrors().stream()
                        .map(ValidationStatus::getDescription)
                        .collect(Collectors.toList());
                logger.debug("Erros encontrados na validação da senha: {}", errors.toString());
                return new PasswordValidationResponseDto(false, ValidationStatus.GENERIC_ERROR.getDescription(), errors);
            }
            logger.debug("Nenhum erro encontrado durante a validação da senha");
            return new PasswordValidationResponseDto(true, ValidationStatus.OK.getDescription(), errors);
        }
        logger.debug("A senha informada é nula. Valor inválido!");
        return new PasswordValidationResponseDto(false, ValidationStatus.NULL_PASSWORD.getDescription(), errors);
    }
}
