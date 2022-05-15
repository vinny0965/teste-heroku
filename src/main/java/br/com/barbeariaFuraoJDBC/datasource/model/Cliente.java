package br.com.barbeariaFuraoJDBC.datasource.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8943697009523913991L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String cpf;
	private String email;
	private String sexo;
	private String telefone;
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;
	
	@ManyToOne
	@JoinColumn(name = "id_endereco", nullable = false)
	private Endereco endereco;
	
	@OneToMany(mappedBy = "cliente")
	private List<Agendamento>agendamentos = new ArrayList<>();
	
	@SuppressWarnings("unused")
	public Cliente() {
	}

	public Cliente(String nome, String cpf, String email, String sexo, String telefone, LocalDate dataNascimento,
			Endereco endereco) {
		super();
		this.nome = nome;
		this.cpf = cpf;
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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	
}
