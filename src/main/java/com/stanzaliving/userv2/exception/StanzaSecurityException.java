/**
 * 
 */
package com.stanzaliving.userv2.exception;

import lombok.Getter;

/**
 * 
 * @author naveen
 *
 * @date 17-Oct-2019
 */
@Getter
public class StanzaSecurityException extends RuntimeException {

	private static final long serialVersionUID = -3368655266237942363L;

	private final int statusCode;

	public StanzaSecurityException(String message) {
		super(message);
		this.statusCode = -1;
	}

	public StanzaSecurityException(Throwable cause) {
		super(cause);
		this.statusCode = -1;
	}

	public StanzaSecurityException(String message, Throwable cause) {
		super(message, cause);
		this.statusCode = -1;
	}

	public StanzaSecurityException(String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

}