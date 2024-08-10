package com.lucaezellner.pwdvalidator.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasswordValidationResponseDto {
    private final boolean valid;
    private final String errorMessage;
}
