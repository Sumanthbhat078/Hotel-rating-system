package com.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.user.payloads.ApiResponse;

public class GlobalExceptionHandler {
	 @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex) {
	        ApiResponse response=new ApiResponse(ex.getMessage(),false);
            return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	    }
	 
	 @ExceptionHandler(ServiceDownException.class)
	 public ResponseEntity<ApiResponse> handlerServiceDownException(ServiceDownException ex) {
	        ApiResponse response=new ApiResponse(ex.getMessage(),false);
         return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	    }
	 
}
