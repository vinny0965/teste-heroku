package br.com.barbeariaFuraoJDBC.datasource.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "enderecos")
public class Endereco implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2650007363644065370L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int id;
	private String logradouro;

	
	private String bairro;
	private Integer numero;
	private String cep;
	
	@OneToMany(mappedBy = "endereco")
	private List<Administrador>administradores = new ArrayList<>();
	
	@OneToMany(mappedBy = "endereco")
	private List<Cliente>clientes = new ArrayList<>();
//	
//	@OneToMany(mappedBy = "endereco")
//	private List<Caixa>caixas = new ArrayList<>();
//	
	@SuppressWarnings("unused")
	public Endereco() {
	}
	
	public Endereco(String logradouro, String bairro, Integer numero, String cep) {
		super();
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.numero = numero;
		this.cep = cep;
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
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	
	
	

}
