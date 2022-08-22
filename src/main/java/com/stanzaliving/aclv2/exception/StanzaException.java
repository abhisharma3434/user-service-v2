/**
 * 
 */
package com.stanzaliving.aclv2.exception;

/**
 * 
 * @author naveen
 *
 * @date 29-Sep-2019
 */
public class StanzaException extends RuntimeException {

	private static final long serialVersionUID = -3368655266237942363L;

	public StanzaException(String message) {
		super(message);
	}

	public StanzaException(Throwable cause) {
		super(cause);
	}

	public StanzaException(String message, Throwable cause) {
		super(message, cause);
	}

}