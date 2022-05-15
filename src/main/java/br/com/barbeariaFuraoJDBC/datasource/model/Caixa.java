//package br.com.barbeariaFuraoJDBC.datasource.model;
//
//import java.io.Serializable;
//import java.time.LocalDate;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//@Entity
//@Table(name = "caixas")
//public class Caixa implements Serializable{
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -6735288673219979651L;
//	@JsonIgnore
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//	private String nome;
//	private String cpf;
//	private String matricula;
//	private String login;
//	private String senha;
//	private String email;
//	private String sexo;
//	private String telefone;
//	@Column(name = "data_nascimento")
//	private LocalDate dataNascimento;
//	
//	@ManyToOne
//	@JoinColumn(name = "id_endereco", nullable = false)
//	private Endereco endereco;
//
//	@SuppressWarnings("unused")
//	public Caixa() {
//	}
//	
//	public Caixa(String nome, String cpf, String matricula, String login, String senha, String email, String sexo,
//			String telefone, LocalDate dataNascimento, Endereco endereco) {
//		this.nome = nome;
//		this.cpf = cpf;
//		this.matricula = matricula;
//		this.login = login;
//		this.senha = senha;
//		this.email = email;
//		this.sexo = sexo;
//		this.telefone = telefone;
//		this.dataNascimento = dataNascimento;
//		this.endereco = endereco;
//	}
//
//	public String getNome() {
//		return nome;
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//
//	public String getCpf() {
//		return cpf;
//	}
//
//	public void setCpf(String cpf) {
//		this.cpf = cpf;
//	}
//
//	public String getMatricula() {
//		return matricula;
//	}
//
//	public void setMatricula(String matricula) {
//		this.matricula = matricula;
//	}
//
//	public String getLogin() {
//		return login;
//	}
//
//	public void setLogin(String login) {
//		this.login = login;
//	}
//
//	public String getSenha() {
//		return senha;
//	}
//
//	public void setSenha(String senha) {
//		this.senha = senha;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getSexo() {
//		return sexo;
//	}
//
//	public void setSexo(String sexo) {
//		this.sexo = sexo;
//	}
//
//	public String getTelefone() {
//		return telefone;
//	}
//
//	public void setTelefone(String telefone) {
//		this.telefone = telefone;
//	}
//
//	public LocalDate getDataNascimento() {
//		return dataNascimento;
//	}
//
//	public void setDataNascimento(LocalDate dataNascimento) {
//		this.dataNascimento = dataNascimento;
//	}
//
//	public Endereco getEndereco() {
//		return endereco;
//	}
//
//	public void setEndereco(Endereco endereco) {
//		this.endereco = endereco;
//	}
//
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	
//	
//}
