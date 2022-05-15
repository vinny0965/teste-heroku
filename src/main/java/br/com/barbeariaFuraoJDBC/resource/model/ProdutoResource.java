package br.com.barbeariaFuraoJDBC.resource.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.barbeariaFuraoJDBC.datasource.model.Produto;

public class ProdutoResource implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2798633977156955752L;

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("codigo_barras")
	private String codigoBarras;
	
	@JsonProperty("descricao")
	private String descricao;
	
	@JsonProperty("validade")
	private String validade;
	
	@JsonProperty("valor")
	private String valor;

	public ProdutoResource() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProdutoResource(Produto produto) {
		super();
		this.id = String.valueOf(produto.getId());
		this.codigoBarras = produto.getCodigoBarras();
		this.descricao = produto.getDescricao();
		this.validade = String.valueOf(produto.getValidade());
		this.valor = String.valueOf(produto.getValor());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
	
	

}
