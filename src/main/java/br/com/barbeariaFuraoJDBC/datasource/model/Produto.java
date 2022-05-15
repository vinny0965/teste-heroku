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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4685003427664476241L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "codigo_barras")
	private String codigoBarras;
	private String descricao;
	private LocalDate validade;
	private double valor;
	
	
	@OneToMany(mappedBy = "produto")
	private List<Estoque>estoques = new ArrayList<>();
	
	
	@SuppressWarnings("unused")
	public Produto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Produto(String codigoBarras, String descricao, LocalDate validade, double valor) {
		super();
		this.codigoBarras = codigoBarras;
		this.descricao = descricao;
		this.validade = validade;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getValidade() {
		return validade;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
	
	
	
	

}
