package com.ebank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ebank.dtos.ErrorResponse;

/**
 * @author Veeru
 * 
 * 
 */
@RestControllerAdvice
public class GlobalExceptionhandler {

	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ResourceNotfoundException.class)
	public ResponseEntity<ErrorResponse> handleNotfound(ResourceNotfoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage()));

	}

	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage()));

	}

	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
		return ResponseEntity.badRequest().body(
				new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getBindingResult().getFieldError().getDefaultMessage(), "VALIDATATION ERROR"));

	}

	/**
	 * 
	 * @param ex
	 * @return
	 */

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleServerException(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
						HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage()));
	}

	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(DataCreationException.class)
	public ResponseEntity<ErrorResponse> handleDataCreationException(DataCreationException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
						HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage()));
	}

}
