package com.ebank.exception;

public class DataCreationException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataCreationException(String message) {
		super(message);
	}
	
	public DataCreationException(String message, Throwable cause) {
        super(message, cause);
    }
	
}
