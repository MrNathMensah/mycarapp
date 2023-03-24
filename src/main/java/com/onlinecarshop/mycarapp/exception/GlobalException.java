package com.onlinecarshop.mycarapp.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Request Method Not Supported");
		details.add("Try again!!");
		ApiError errors = new ApiError(message,details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(errors);	
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Media Type Not Supported");
		details.add("Try again!!");
		ApiError errors = new ApiError(message,details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(errors);	
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Path Variable cannot be found");
		details.add("Try again!!");
		ApiError errors = new ApiError(message,details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(errors);	
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Type Mismatch");
		details.add("Try again!!");
		ApiError errors = new ApiError(message,details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(errors);	
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Wrong parameter");
		details.add("Try again!!");
		ApiError errors = new ApiError(message,details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(errors);	
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Message not readable");
		details.add("Try again!!");
		ApiError errors = new ApiError(message,details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(errors);	
	}
	
	//Handles all Customer not found exceptions
		@ExceptionHandler(CustomerNotFoundException.class)
		protected ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException ex){
			String message = ex.getMessage();
			List<String> details = new ArrayList<>();
			details.add("Customer not found");
			details.add("Try again!!");
			ApiError errors = new ApiError(message,details,HttpStatus.BAD_REQUEST,LocalDateTime.now());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		
		//Handles all Customer ID not found exceptions
		@ExceptionHandler(IdNotFoundException.class)
		protected ResponseEntity<Object> handleIdNotFoundException(IdNotFoundException ex){
			String message = ex.getMessage();
			List<String> details = new ArrayList<>();
			details.add("Id not found");
			details.add("Try again!!");
			ApiError errors = new ApiError(message,details,HttpStatus.BAD_REQUEST,LocalDateTime.now());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		
		//Error handling all other errors apart from the one we wrote
		@ExceptionHandler(Exception.class)
		protected ResponseEntity<Object> handleException(Exception ex){
			String message = ex.getMessage();
			List<String> details = new ArrayList<>();
			details.add("Customer Not found at all");
			details.add("Try again!!");
			ApiError errors = new ApiError(message,details,HttpStatus.BAD_REQUEST,LocalDateTime.now());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		
		//Handles all Customer Order not found exceptions
				@ExceptionHandler(CustomerOrderNotFoundException.class)
				protected ResponseEntity<Object> handleCustomerOrderNotFoundException(CustomerOrderNotFoundException ex){
					String message = ex.getMessage();
					List<String> details = new ArrayList<>();
					details.add("Customer order not found");
					details.add("Try again!!");
					ApiError errors = new ApiError(message,details,HttpStatus.BAD_REQUEST,LocalDateTime.now());
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
				}
				
				//Handles all Customer Payment Method not found exceptions
				@ExceptionHandler(CustomerPaymentMethodNotFoundException.class)
				protected ResponseEntity<Object> handleCustomerPaymentMethodNotFoundException(CustomerPaymentMethodNotFoundException ex){
					String message = ex.getMessage();
					List<String> details = new ArrayList<>();
					details.add("Customer payment method not found");
					details.add("Try again!!");
					ApiError errors = new ApiError(message,details,HttpStatus.BAD_REQUEST,LocalDateTime.now());
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
				}

}
