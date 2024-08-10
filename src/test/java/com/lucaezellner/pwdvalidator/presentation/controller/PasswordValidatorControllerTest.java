package com.lucaezellner.pwdvalidator.presentation.controller;

import com.lucaezellner.pwdvalidator.application.dto.PasswordValidationResponseDto;
import com.lucaezellner.pwdvalidator.application.usecase.ValidatePasswordUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
        PasswordValidationResponseDto responseDto = new PasswordValidationResponseDto(true, "");
        when(validatePasswordUseCase.execute(password)).thenReturn(responseDto);

        ResponseEntity<PasswordValidationResponseDto> response = passwordValidatorController.validatePassword(password);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).isValid());
        assertEquals("", response.getBody().getErrorMessage());
    }

    @Test
    void shouldReturnOkWhenPasswordIsInvalid() {
        String password = "abc";
        PasswordValidationResponseDto responseDto = new PasswordValidationResponseDto(false, "A senha informada deve conter pelo menos 9 caracteres.");
        when(validatePasswordUseCase.execute(password)).thenReturn(responseDto);

        ResponseEntity<PasswordValidationResponseDto> response = passwordValidatorController.validatePassword(password);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(Objects.requireNonNull(response.getBody()).isValid());
        assertEquals("A senha informada deve conter pelo menos 9 caracteres.", response.getBody().getErrorMessage());
    }

    @Test
    void shouldReturnInternalServerErrorWhenExceptionOccurs() {
        String password = "SenhaErroInterno";
        when(validatePasswordUseCase.execute(password)).thenThrow(new RuntimeException("Erro desconhecido"));

        ResponseEntity<PasswordValidationResponseDto> response = passwordValidatorController.validatePassword(password);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }
}
