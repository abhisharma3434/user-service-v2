package com.stanzaliving.userv2.annotations;

import com.stanzaliving.userv2.annotations.validator.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {

	String message() default "Invalid Email";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}