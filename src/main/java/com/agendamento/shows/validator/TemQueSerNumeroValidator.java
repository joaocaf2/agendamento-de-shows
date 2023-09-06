package com.agendamento.shows.validator;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TemQueSerNumeroValidator implements ConstraintValidator<TemQueSerNumero, BigDecimal> {

	@Override
	public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
		return true;
	}

}
