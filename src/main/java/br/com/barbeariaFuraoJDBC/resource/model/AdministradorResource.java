package br.com.barbeariaFuraoJDBC.resource.model;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdministradorResource {
	
	private int id;
	
	@NotEmpty(message = "campo nome requerido")
	@JsonProperty("nome")
	private String nome;
	

	@JsonProperty("cpf")
	private String cpf;
	
	@NotEmpty(message = "campo login requerido")
	@JsonProperty("login")
	private String login;
	
	@NotEmpty(message = "campo senha requerido")
	@JsonProperty("senha")
	private String senha;
	
	@NotEmpty(message = "campo email requerido")
	@JsonProperty("email")
	private String email;
	
	@NotEmpty(message = "campo sexo requerido")
	@JsonProperty("sexo")
	private String sexo;
	
	@NotEmpty(message = "campo telfone requerido")
	@JsonProperty("telefone")
	private String telefone;
	
	@NotEmpty(message = "campo data_nascimento requerido")
	@JsonProperty("data_nascimento")
	private String dataNascimento;
	
	@NotEmpty(message = "campo id_endereco requerido")
	@JsonProperty("id_endereco")
	private String endereco;

	
	public AdministradorResource() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdministradorResource(String nome, String cpf, String login, String senha, String email, String sexo,
			String telefone, String dataNascimento, String endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.sexo = sexo;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
