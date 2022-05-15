package br.com.barbeariaFuraoJDBC.resource.model;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CupomDescontoResource {

	private int id;
	
	@NotEmpty(message = "campo codigo requerido")
	@JsonProperty("codigo")
	private String codigo;
	
	@NotEmpty(message = "campo valor requerido")
	@JsonProperty("valor")
	private String valor;
	
	@JsonProperty("id_administrador")
	private String idAdministrador;

	public CupomDescontoResource(int id,String codigo, String valor, String idAdministrador) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.valor = valor;
		this.idAdministrador = idAdministrador;
	}
	

	public CupomDescontoResource() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getIdAdministrador() {
		return idAdministrador;
	}

	public void setIdAdministrador(String idAdministrador) {
		this.idAdministrador = idAdministrador;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
