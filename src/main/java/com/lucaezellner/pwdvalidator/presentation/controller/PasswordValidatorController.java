package com.lucaezellner.pwdvalidator.presentation.controller;

import com.lucaezellner.pwdvalidator.application.dto.PasswordValidationResponseDto;
import com.lucaezellner.pwdvalidator.application.usecase.ValidatePasswordUseCase;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@AllArgsConstructor
@RequestMapping("/passwords")
public class PasswordValidatorController {
    private final ValidatePasswordUseCase validatePasswordUseCase;
    private static final Logger logger = LoggerFactory.getLogger(PasswordValidatorController.class);

    @GetMapping("/validates")
    public ResponseEntity<PasswordValidationResponseDto> validatePassword(@RequestParam String password) {
        try {
            logger.debug("Senha recebida via API, iniciando validação");
            PasswordValidationResponseDto response = validatePasswordUseCase.execute(password);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            PasswordValidationResponseDto response = new PasswordValidationResponseDto(
                    false, "Erro interno desconhecido no processamento da API", new ArrayList<>()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
