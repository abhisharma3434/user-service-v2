package com.stanzaliving.userv2.annotations;

import com.stanzaliving.userv2.annotations.validator.EnsureNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation checks if a String is number using NumberUtils from apache commons
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.TYPE_USE })
@Constraint(validatedBy = EnsureNumberValidator.class)
public @interface EnsureNumber {

	String message() default "${validatedValue} for {fieldName} is not a valid Number";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String fieldName();
}