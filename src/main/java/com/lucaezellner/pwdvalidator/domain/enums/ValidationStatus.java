package com.lucaezellner.pwdvalidator.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ValidationStatus {
    OK("Senha válida"),
    NO_DIGITS("A senha informada precisa conter pelo menos 1 dígito"),
    NO_LOWER_CASE("A senha informada deve conter pelo 1 letra minúscula"),
    NO_UPPER_CASE("A senha informada deve conter pelo 1 letra maiúscula"),
    NO_SPECIAL_CHARACTERS("A senha informada precisa conter pelo menos 1 caracter especial"),
    REPEATED_CHARACTERS("A senha informada não pode conter caracteres repetidos"),
    TOO_SHORT("A senha informada deve conter pelo menos 9 caracteres");

    private final String description;
}