/**
 * 
 */
package com.stanzaliving.userv2.exception;

/**
 * 
 * @author naveen.kumar
 *
 * @date 1 Jul, 2020
 *
 */
public class RequestAlreadyRejectedException extends RuntimeException {

	private static final long serialVersionUID = -3368655266237942363L;

	public RequestAlreadyRejectedException(String message) {
		super(message);
	}

	public RequestAlreadyRejectedException(Throwable cause) {
		super(cause);
	}

	public RequestAlreadyRejectedException(String message, Throwable cause) {
		super(message, cause);
	}

}