package com.lucaezellner.pwdvalidator.application.usecase;

import com.lucaezellner.pwdvalidator.application.dto.PasswordValidationResponseDto;
import com.lucaezellner.pwdvalidator.domain.enums.ValidationStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatePasswordUseCaseTest {

    private final ValidatePasswordUseCase validatePasswordUseCase = new ValidatePasswordUseCase();

    @Test
    void shouldReturnNotValidWhenPasswordIsTooShort() {
        PasswordValidationResponseDto response = validatePasswordUseCase.execute("abc");
        assertFalse(response.isValid());
        assertEquals(ValidationStatus.TOO_SHORT.getDescription(), response.getErrorMessage());
    }

    @Test
    void shouldReturnNotValidWhenPasswordHasNoDigits() {
        PasswordValidationResponseDto response = validatePasswordUseCase.execute("Abcabcabc@!");
        assertFalse(response.isValid());
        assertEquals(ValidationStatus.NO_DIGITS.getDescription(), response.getErrorMessage());
    }

    @Test
    void shouldReturnNotValidWhenPasswordHasNoLowerCase() {
        PasswordValidationResponseDto response = validatePasswordUseCase.execute("ABCABCABC@1");
        assertFalse(response.isValid());
        assertEquals(ValidationStatus.NO_LOWER_CASE.getDescription(), response.getErrorMessage());
    }

    @Test
    void shouldReturnNotValidWhenPasswordHasNoUpperCase() {
        PasswordValidationResponseDto response = validatePasswordUseCase.execute("abcabcabc@1!");
        assertFalse(response.isValid());
        assertEquals(ValidationStatus.NO_UPPER_CASE.getDescription(), response.getErrorMessage());
    }

    @Test
    void shouldReturnNotValidWhenPasswordHasNoSpecialCharacter() {
        PasswordValidationResponseDto response = validatePasswordUseCase.execute("Abcabcabc1");
        assertFalse(response.isValid());
        assertEquals(ValidationStatus.NO_SPECIAL_CHARACTERS.getDescription(), response.getErrorMessage());
    }

    @Test
    void shouldReturnNotValidWhenPasswordHasRepeatedCharacters() {
        PasswordValidationResponseDto response = validatePasswordUseCase.execute("AbbCAbcabc1!");
        assertFalse(response.isValid());
        assertEquals(ValidationStatus.REPEATED_CHARACTERS.getDescription(), response.getErrorMessage());
    }

    @Test
    void shouldReturnTrueWhenPasswordIsValid() {
        PasswordValidationResponseDto response = validatePasswordUseCase.execute("AbcAbcabc1!");
        assertTrue(response.isValid());
        assertEquals("", response.getErrorMessage());
    }
}
