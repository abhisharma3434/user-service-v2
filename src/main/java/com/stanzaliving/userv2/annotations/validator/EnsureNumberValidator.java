package com.stanzaliving.userv2.annotations.validator;


import com.stanzaliving.userv2.annotations.EnsureNumber;
import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnsureNumberValidator implements ConstraintValidator<EnsureNumber, String> {

	@Override
	public void initialize(EnsureNumber constraint) {
	}

	@Override
	public boolean isValid(String obj, ConstraintValidatorContext context) {
		return NumberUtils.isDigits(obj);
	}

}