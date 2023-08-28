package com.hotel.entities.exception;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException() {
		super("hotel not found");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
