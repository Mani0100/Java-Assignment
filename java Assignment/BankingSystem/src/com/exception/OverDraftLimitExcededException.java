package com.exception;

public class OverDraftLimitExcededException extends Exception{
	private static final long serialVersionUID = 1L;
	
	private String message;

	public OverDraftLimitExcededException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}