package br.com.barbeariaFuraoJDBC.exception;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class AplicationExceptionHandeler{

	@ExceptionHandler(ResourceExeption.class)
	public ResponseEntity<DefaultError> exceptionResource(Exception e, ServletRequest request) {
			DefaultError error = new DefaultError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
			return new ResponseEntity<DefaultError>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<DefaultError> NotFoundException(Exception e, ServletRequest request) {
		DefaultError error = new DefaultError(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<DefaultError> validationError(MethodArgumentNotValidException e, ServletRequest request){
		ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro na validação dos campos");
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			error.addErrors(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
}
