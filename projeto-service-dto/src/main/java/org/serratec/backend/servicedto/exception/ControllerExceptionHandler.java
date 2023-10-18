package org.serratec.backend.servicedto.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({EmailException.class, SenhaException.class})
	protected ResponseEntity<?> handleEmailException(Exception ex) {
		return ResponseEntity.unprocessableEntity().body(ex.getMessage());
	}
	
	/*
	@ExceptionHandler(SenhaException.class)
	protected ResponseEntity<?> handleSenhaException(SenhaException ex) {
		return ResponseEntity.unprocessableEntity().body(ex.getMessage());
	}
	*/
	
}
