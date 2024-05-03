package com.exception;

public class NullPointerException extends Exception{
	private static final long serialVersionUID = 1L;
	
	private String message;

	public NullPointerException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}