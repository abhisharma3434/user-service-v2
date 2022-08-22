package com.stanzaliving.aclv2.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class SchedulerException extends RuntimeException {
    private static final long serialVersionUID = -3368655266237942363L;

    private final String message;

    private final String errorCode;

    public SchedulerException(String message) {
        super(message);
        this.message = message;
        this.errorCode = null;
    }

    public SchedulerException(String message, String errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }
}
