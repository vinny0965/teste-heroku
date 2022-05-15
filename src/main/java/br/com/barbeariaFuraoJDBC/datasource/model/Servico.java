package br.com.barbeariaFuraoJDBC.datasource.model;

import java.io.Serializable;
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

@Entity
@Table(name = "servicos")
public class Servico implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4970601509127632912L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "tipo_servico")
	private String tipoServico;
	private Double valor;
	
	@ManyToOne
	@JoinColumn(name = "id_administrador",nullable = false)
	private Administrador administrador;

	@OneToMany(mappedBy = "servico")
	private List<Agendamento>agendamentos = new ArrayList<>();
	
	@SuppressWarnings("unused")
	public Servico() {
	}
	
	
	public Servico(String tipoServico, Double valor, Administrador administrador) {
		this.tipoServico = tipoServico;
		this.valor = valor;
		this.administrador = administrador;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
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
