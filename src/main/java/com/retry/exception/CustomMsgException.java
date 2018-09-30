package com.retry.exception;


public class CustomMsgException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomMsgException() {
        super();
    }

    public CustomMsgException(String message) {
        super(message);
    }

    public CustomMsgException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomMsgException(Throwable cause) {
        super(cause);
    }

}
