package br.com.barbeariaFuraoJDBC.exception;

import java.io.Serializable;

public class FieldMessagem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 898585609602981580L;

	private String fieldName;
	private String message;
	
	public FieldMessagem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FieldMessagem(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
