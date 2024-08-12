package com.lucaezellner.pwdvalidator.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PasswordValidationResponseDto {
    private final boolean valid;
    private final String validationMessage;
    private final List<String> errors;
}
