package com.stanzaliving.userv2.annotations.validator;

import com.stanzaliving.userv2.annotations.Email;
import com.stanzaliving.userv2.utils.StanzaUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {

	@Override
	public void initialize(Email constraintAnnotation) {
		/**
		 * default
		 * 
		 */
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		boolean response = true;
		if (StringUtils.isNotBlank(email) && !StanzaUtils.isValidEmail(email)) {
			response = false;
		}
		return response;
	}

}