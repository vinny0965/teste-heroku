package br.com.barbeariaFuraoJDBC.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends DefaultError{
	private List<FieldMessagem> errors = new ArrayList<>();

	public ValidationError(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessagem> getErrors() {
		return errors;
	}

	public void addErrors(String fieldName, String message) {
		this.errors.add(new FieldMessagem(fieldName,message));
	}

	

}
