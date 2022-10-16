package com.ty.foodappapi.exception;

public class InvalidCredentialsException extends RuntimeException{
	String message = "Given credentials are invalid";

	public InvalidCredentialsException(String message) {
		this.message = message;
	}

	public InvalidCredentialsException() {
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
