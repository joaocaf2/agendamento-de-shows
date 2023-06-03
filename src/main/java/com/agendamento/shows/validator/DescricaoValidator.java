package com.agendamento.shows.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DescricaoValidator implements ConstraintValidator<DescricaoTamanho, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		String valor = value;
		// Se o usuário não informou, não preciso validar pois não é obrigatório
		if (valor.isEmpty()) {
			return true;
		}
		// Entretanto, se ele informou, a descrição deve ter no mínimo 50 caracteres
		if (valor.length() < 50) {
			return false;
		}
		return true;
	}

}
