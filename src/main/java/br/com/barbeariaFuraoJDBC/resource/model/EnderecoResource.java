package br.com.barbeariaFuraoJDBC.resource.model;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.barbeariaFuraoJDBC.datasource.model.Endereco;



public class EnderecoResource {
	
	private String id;
	
	@NotEmpty(message = "campo lograuro requerido")
	@JsonProperty("logradouro")
	private String logradouro;
	
	@NotEmpty(message = "campo bairro requerido")
	@JsonProperty("bairro")
	private String bairro;
	
	@NotEmpty(message = "campo numero requerido")
	@JsonProperty("numero")
	private String numero;
	
	@NotEmpty(message = "campo cep requerido")
	@JsonProperty("cep")
	private String cep;
	
	
	
	public EnderecoResource() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EnderecoResource(String logradouro, String bairro, String numero, String cep) {
		super();
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.numero = numero;
		this.cep = cep;
	}
	public EnderecoResource(Endereco enderecoResource) {
		super();
		this.id = String.valueOf(enderecoResource.getId());
		this.logradouro = enderecoResource.getLogradouro();
		this.bairro = enderecoResource.getBairro();
		this.numero = String.valueOf(enderecoResource.getNumero());
		this.cep = enderecoResource.getCep();
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return "EnderecoResource [logradouro=" + logradouro + ", bairro=" + bairro + ", numero=" + numero + ", cep="
				+ cep + "]";
	}
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	

}
