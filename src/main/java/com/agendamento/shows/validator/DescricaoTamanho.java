package com.agendamento.shows.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DescricaoValidator.class)
@Documented
public @interface DescricaoTamanho {
	String message() default "A descrição deve ter no mínimo 50 caracteres";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String value() default "";
}
