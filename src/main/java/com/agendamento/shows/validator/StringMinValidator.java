package com.agendamento.shows.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringMinValidator implements ConstraintValidator<StringMinimoCaracterOpcional, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		String valor = value;
		if (verificaSeUsuarioInformouUmaString(valor)) {
			return true;
		}
		if (verificaStringQtdMInima(valor, 50) == true) {
			return false;
		}
		return true;
	}

	private boolean verificaStringQtdMInima(String str, int quantidade) {
		return (str.length() < quantidade);
	}

	private boolean verificaSeUsuarioInformouUmaString(String str) {
		return str.isEmpty();
	}

}
