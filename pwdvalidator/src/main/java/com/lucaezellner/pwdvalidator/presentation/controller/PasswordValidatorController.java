package com.lucaezellner.pwdvalidator.presentation.controller;

import com.lucaezellner.pwdvalidator.application.dto.PasswordValidationResponseDto;
import com.lucaezellner.pwdvalidator.application.usecase.ValidatePasswordUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PasswordValidatorController {
    private final ValidatePasswordUseCase validatePasswordUseCase;

    @GetMapping("/validate-password")
    public ResponseEntity<PasswordValidationResponseDto> validatePassword(@RequestParam String password) {
        try {
            PasswordValidationResponseDto response = validatePasswordUseCase.execute(password);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
