package com.lucaezellner.pwdvalidator.domain.entity;

import com.lucaezellner.pwdvalidator.domain.enums.ValidationStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    void shouldFailWhenPasswordIsNull() {
        Password password = new Password(null);
        assertFalse(password.isValid());
        assertEquals(ValidationStatus.TOO_SHORT, password.getValidationStatus());
    }

    @Test
    void shouldFailWhenPasswordIsTooShort() {
        Password password = new Password("abc");
        assertFalse(password.isValid());
        assertEquals(ValidationStatus.TOO_SHORT, password.getValidationStatus());
    }

    @Test
    void shouldFailWhenPasswordHasNoDigits() {
        Password password = new Password("Abcabcabc@!");
        assertFalse(password.isValid());
        assertEquals(ValidationStatus.NO_DIGITS, password.getValidationStatus());
    }

    @Test
    void shouldFailWhenPasswordHasNoLowerCase() {
        Password password = new Password("ABCABCABC@1");
        assertFalse(password.isValid());
        assertEquals(ValidationStatus.NO_LOWER_CASE, password.getValidationStatus());
    }

    @Test
    void shouldFailWhenPasswordHasNoUpperCase() {
        Password password = new Password("abcabcabc@1!");
        assertFalse(password.isValid());
        assertEquals(ValidationStatus.NO_UPPER_CASE, password.getValidationStatus());
    }

    @Test
    void shouldFailWhenPasswordHasNoSpecialCharacter() {
        Password password = new Password("Abcabcabc1");
        assertFalse(password.isValid());
        assertEquals(ValidationStatus.NO_SPECIAL_CHARACTERS, password.getValidationStatus());
    }

    @Test
    void shouldFailWhenPasswordHasRepeatedCharacters() {
        Password password = new Password("AbbCAbcabc1!");
        assertFalse(password.isValid());
        assertEquals(ValidationStatus.REPEATED_CHARACTERS, password.getValidationStatus());
    }

    @Test
    void shouldPassWhenPasswordIsValid() {
        Password password = new Password("AbcAbcabc1!");
        assertTrue(password.isValid());
        assertEquals(ValidationStatus.OK, password.getValidationStatus());
    }
}
