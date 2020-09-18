package com.hardik.bhagvadgita.exceptions;

public class EmailAlreadyRegisteredException extends RuntimeException{

	public EmailAlreadyRegisteredException() {
		super();
	}

	public EmailAlreadyRegisteredException(String message) {
		super(message);
	}
}
