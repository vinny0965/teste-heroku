package br.com.barbeariaFuraoJDBC.datasource.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "estoques")
public class Estoque implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2977162766417245055L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String lote;
	private int quantidade;
	
	@ManyToOne
	@JoinColumn(name = "id_produto",nullable = false)
	private Produto produto;

	@SuppressWarnings("unused")
	public Estoque() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Estoque(String lote, int quantidade, int idProduto, Produto produto) {
		super();
		this.lote = lote;
		this.quantidade = quantidade;
		this.produto = produto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}



	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	
	


}
