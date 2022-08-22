/**
 * 
 */
package com.stanzaliving.aclv2.exception;

/**
 * 
 * @author naveen.kumar
 *
 * @date 27-Mar-2020
 *
 */
public class TransactionFailException extends RuntimeException {

	private static final long serialVersionUID = -3368655266237942363L;

	public TransactionFailException(String message) {
		super(message);
	}

	public TransactionFailException(Throwable cause) {
		super(cause);
	}

	public TransactionFailException(String message, Throwable cause) {
		super(message, cause);
	}

}