package br.com.barbeariaFuraoJDBC.datasource.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cupons_desconto")
public class CupomDesconto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8913408336265461648L;
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String codigo;
	private Double valor;
	
	@ManyToOne
	@JoinColumn(name = "id_administrador",nullable = false)
	private Administrador administrador;

	@SuppressWarnings("unused")
	public CupomDesconto() {
	}
	public CupomDesconto(String codigo, Double valor, Administrador administrador) {
		this.codigo = codigo;
		this.valor = valor;
		this.administrador = administrador;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
