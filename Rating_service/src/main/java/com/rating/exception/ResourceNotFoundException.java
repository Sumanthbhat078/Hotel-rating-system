package com.rating.exception;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException() {
		super("Rating not found");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

}

