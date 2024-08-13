package com.lucaezellner.pwdvalidator.presentation.controller;

import com.lucaezellner.pwdvalidator.application.dto.PasswordValidationResponseDto;
import com.lucaezellner.pwdvalidator.application.usecase.ValidatePasswordUseCase;
import com.lucaezellner.pwdvalidator.domain.enums.ValidationStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PasswordValidatorControllerTest {

    @Mock
    private ValidatePasswordUseCase validatePasswordUseCase;

    @InjectMocks
    private PasswordValidatorController passwordValidatorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnOkWhenPasswordIsValid() {
        String password = "SenhaValida1!";
        PasswordValidationResponseDto responseDto = new PasswordValidationResponseDto(true, ValidationStatus.OK.getDescription(), new ArrayList<>());
        when(validatePasswordUseCase.execute(password)).thenReturn(responseDto);

        ResponseEntity<PasswordValidationResponseDto> response = passwordValidatorController.validatePassword(password);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).isValid());
        assertEquals(ValidationStatus.OK.getDescription(), response.getBody().getValidationMessage());
    }

    @Test
    void shouldReturnOkWhenPasswordIsInvalid() {
        String password = "abC1@";
        PasswordValidationResponseDto responseDto = new PasswordValidationResponseDto(false,
                ValidationStatus.GENERIC_ERROR.getDescription(),
                List.of(ValidationStatus.TOO_SHORT.getDescription())
        );
        when(validatePasswordUseCase.execute(password)).thenReturn(responseDto);

        ResponseEntity<PasswordValidationResponseDto> response = passwordValidatorController.validatePassword(password);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(Objects.requireNonNull(response.getBody()).isValid());
        assertEquals(ValidationStatus.GENERIC_ERROR.getDescription(), response.getBody().getValidationMessage());
    }

    @Test
    void shouldReturnInternalServerErrorWhenExceptionOccurs() {
        String password = "SenhaErroInterno";
        when(validatePasswordUseCase.execute(password)).thenThrow(new RuntimeException("Erro desconhecido"));

        ResponseEntity<PasswordValidationResponseDto> response = passwordValidatorController.validatePassword(password);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro interno desconhecido no processamento da API", response.getBody().getValidationMessage());
    }
}
