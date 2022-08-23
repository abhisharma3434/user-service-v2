package com.stanzaliving.userv2.exception;

/**
 * @author piyush.srivastava "piyush.srivastava@stanzaliving.com"
 *
 * @since 22-Nov-2020
 *
 * @version 1.0
 */

public class TransactionValidationException extends RuntimeException {

	private static final long serialVersionUID = -168055266237897883L;

	public TransactionValidationException(String message) {
		super(message);
	}

	public TransactionValidationException(Throwable cause) {
		super(cause);
	}

	public TransactionValidationException(String message, Throwable cause) {
		super(message, cause);
	}

}