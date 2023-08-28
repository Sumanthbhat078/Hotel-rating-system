package com.hotel.entities.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hotel.payload.ApiResponse;
@RestControllerAdvice
public class GlobalExceptionHandler {
      public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
      {
    	  ApiResponse response=new ApiResponse(ex.getMessage(),false);
    	  return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
      }
}
