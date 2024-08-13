package com.lucaezellner.pwdvalidator.domain.entity;

import com.lucaezellner.pwdvalidator.domain.enums.ValidationStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    void shouldFailWhenMultipleValidationErrors() {
        Password password = new Password("Abcabca");
        assertFalse(password.isValid());
        assertTrue(password.getValidationErrors().contains(ValidationStatus.NO_DIGITS));
        assertTrue(password.getValidationErrors().contains(ValidationStatus.NO_SPECIAL_CHARACTERS));
        assertTrue(password.getValidationErrors().contains(ValidationStatus.TOO_SHORT));
    }

    @Test
    void shouldFailWhenPasswordIsTooShort() {
        Password password = new Password("abc@C1");
        assertFalse(password.isValid());
        assertEquals(ValidationStatus.TOO_SHORT, password.getValidationErrors().getFirst());
    }

    @Test
    void shouldFailWhenPasswordHasNoDigits() {
        Password password = new Password("Abcabcabc@!");
        assertFalse(password.isValid());
        assertEquals(ValidationStatus.NO_DIGITS, password.getValidationErrors().getFirst());
    }

    @Test
    void shouldFailWhenPasswordHasNoLowerCase() {
        Password password = new Password("ABCABCABC@1");
        assertFalse(password.isValid());
        assertEquals(ValidationStatus.NO_LOWER_CASE, password.getValidationErrors().getFirst());
    }

    @Test
    void shouldFailWhenPasswordHasNoUpperCase() {
        Password password = new Password("abcabcabc@1!");
        assertFalse(password.isValid());
        assertEquals(ValidationStatus.NO_UPPER_CASE,password.getValidationErrors().getFirst());
    }

    @Test
    void shouldFailWhenPasswordHasNoSpecialCharacter() {
        Password password = new Password("Abcabcabc1");
        assertFalse(password.isValid());
        assertEquals(ValidationStatus.NO_SPECIAL_CHARACTERS, password.getValidationErrors().getFirst());
    }

    @Test
    void shouldFailWhenPasswordHasRepeatedCharacters() {
        Password password = new Password("AbbCAbcabc1!");
        assertFalse(password.isValid());
        assertEquals(ValidationStatus.REPEATED_CHARACTERS, password.getValidationErrors().getFirst());
    }

    @Test
    void shouldPassWhenPasswordIsValid() {
        Password password = new Password("AbcAbcabc1!");
        assertTrue(password.isValid());
        assertTrue(password.getValidationErrors().isEmpty());
    }
}
