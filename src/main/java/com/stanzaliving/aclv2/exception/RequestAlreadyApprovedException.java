/**
 * 
 */
package com.stanzaliving.aclv2.exception;

/**
 * 
 * @author naveen.kumar
 *
 * @date 1 Jul, 2020
 *
 */
public class RequestAlreadyApprovedException extends RuntimeException {

	private static final long serialVersionUID = -3368655266237942363L;

	public RequestAlreadyApprovedException(String message) {
		super(message);
	}

	public RequestAlreadyApprovedException(Throwable cause) {
		super(cause);
	}

	public RequestAlreadyApprovedException(String message, Throwable cause) {
		super(message, cause);
	}

}