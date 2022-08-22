/**
 * 
 */
package com.stanzaliving.aclv2.exception;

/**
 * 
 * @author naveen.kumar
 *
 * @date 18-Apr-2020
 *
 */
public class RecordExistsException extends RuntimeException {

	private static final long serialVersionUID = -3368655266237942363L;

	public RecordExistsException(String message) {
		super(message);
	}

	public RecordExistsException(Throwable cause) {
		super(cause);
	}

	public RecordExistsException(String message, Throwable cause) {
		super(message, cause);
	}

}