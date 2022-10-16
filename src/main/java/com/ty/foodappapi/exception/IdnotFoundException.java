package com.ty.foodappapi.exception;

public class IdnotFoundException extends RuntimeException{
	String message ="Given ID is not exist";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public IdnotFoundException(String message) {
		this.message = message;
	}

	public IdnotFoundException() {
	}
	
	
	
}
