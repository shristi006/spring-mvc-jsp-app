package com.example.springmvc.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice//it acts as controller interceptor
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> handleEmployeeNotFoundException(
    		EmployeeNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
	
	//jakarta.validation.ConstraintViolationException
		@ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
		ResponseEntity<Object> handleRuntimeException(jakarta.validation.ConstraintViolationException e)
		{
			  Map<String, Object> body = new LinkedHashMap<>();
		        body.put("timestamp", LocalDateTime.now());
		        body.put("message","bean validation exception:"+ e.getMessage());
	 
		        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
		}
		
		@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(
				MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request)
		{
			Map<String, String> responseMap = new HashMap<>();
			
			responseMap.put("message", ex.getMessage() + " from rest ");
			return new ResponseEntity<>(responseMap, HttpStatus.NOT_EXTENDED);
		}
}
