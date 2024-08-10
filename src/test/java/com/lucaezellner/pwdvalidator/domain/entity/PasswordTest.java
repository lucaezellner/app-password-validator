package com.lucaezellner.pwdvalidator.domain.entity;

import com.lucaezellner.pwdvalidator.domain.exception.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    void shouldFailWhenPasswordIsNull() {
        Password password = new Password(null);
        assertFalse(password.isValid());
        assertInstanceOf(PasswordIsTooShortException.class, password.getException());
    }

    @Test
    void shouldFailWhenPasswordIsTooShort() {
        Password password = new Password("abc");
        assertFalse(password.isValid());
        assertInstanceOf(PasswordIsTooShortException.class, password.getException());
    }

    @Test
    void shouldFailWhenPasswordHasNoDigits() {
        Password password = new Password("Abcabcabc@!");
        assertFalse(password.isValid());
        assertInstanceOf(PasswordHasNoDigitsException.class, password.getException());
    }

    @Test
    void shouldFailWhenPasswordHasNoLowerCase() {
        Password password = new Password("ABCABCABC@1");
        assertFalse(password.isValid());
        assertInstanceOf(PasswordHasNoLowerCaseException.class, password.getException());
    }

    @Test
    void shouldFailWhenPasswordHasNoUpperCase() {
        Password password = new Password("abcabcabc@1!");
        assertFalse(password.isValid());
        assertInstanceOf(PasswordHasNoUpperCaseException.class, password.getException());
    }

    @Test
    void shouldFailWhenPasswordHasNoSpecialCharacter() {
        Password password = new Password("Abcabcabc1");
        assertFalse(password.isValid());
        assertInstanceOf(PasswordHasNoSpecialCharactersException.class, password.getException());
    }

    @Test
    void shouldFailWhenPasswordHasRepeatedCharacters() {
        Password password = new Password("AbbCAbcabc1!");
        assertFalse(password.isValid());
        assertInstanceOf(PasswordHasRepeatedCharactersException.class, password.getException());
    }

    @Test
    void shouldPassWhenPasswordIsValid() {
        Password password = new Password("AbcAbcabc1!");
        assertTrue(password.isValid());
        assertNull(password.getException());
    }
}
