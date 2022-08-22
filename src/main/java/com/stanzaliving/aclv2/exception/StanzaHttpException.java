/**
 * 
 */
package com.stanzaliving.aclv2.exception;

import lombok.Getter;

/**
 * 
 * @author naveen
 *
 * @date 29-Sep-2019
 */
@Getter
public class StanzaHttpException extends RuntimeException {

	private static final long serialVersionUID = -3368655266237942363L;

	private int statusCode;

	public StanzaHttpException(String message) {
		super(message);
	}

	public StanzaHttpException(String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	public StanzaHttpException(Throwable cause) {
		super(cause);
	}

	public StanzaHttpException(String message, Throwable cause) {
		super(message, cause);
	}

	public StanzaHttpException(String message, int statusCode, Throwable cause) {
		super(message, cause);
		this.statusCode = statusCode;
	}
}