package com.user.exception;

public class ServiceDownException extends RuntimeException{

	public ServiceDownException() {
		super();
	}


	public ServiceDownException(String message) {
		super(message);
	}
}
