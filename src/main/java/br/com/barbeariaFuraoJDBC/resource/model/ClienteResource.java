package br.com.barbeariaFuraoJDBC.resource.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClienteResource {
	
	private int id;
	
	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("cpf")
	private String cpf;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("sexo")
	private String sexo;
	
	@JsonProperty("telefone")
	private String telefone;
	
	@JsonProperty("dataNascimento")
	private String dataNascimento;
	
	@JsonProperty("id_endereco")
	private String idEndereco;

	
	
	public ClienteResource() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClienteResource(String nome, String cpf, String email, String sexo, String telefone, String dataNascimento,
			String idEndereco) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.sexo = sexo;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.idEndereco = idEndereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(String idEndereco) {
		this.idEndereco = idEndereco;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
