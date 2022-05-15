package br.com.barbeariaFuraoJDBC.resource.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.barbeariaFuraoJDBC.datasource.model.FluxoCaixa;

public class FluxoCaixaResource implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 600900485048408676L;

	@JsonProperty("id")
	private String id;
	@NotNull(message = "campo forma_pagamento requerido")
	@JsonProperty("forma_pagamento")
	private String formaPagamento;
	@NotNull(message = "campo total_pagamento requerido")
	@JsonProperty("total_pagamento")
	private String totalPagamento;
	
	@JsonProperty("id_agendamento")
	private String agendamento;
	@JsonProperty("id_cupom")
	private String cupomDesconto;
	
	public FluxoCaixaResource(String formaPagamento, String totalPagamento, String agendamento,
			String cupomDesconto) {
		super();
		this.formaPagamento = formaPagamento;
		this.totalPagamento = totalPagamento;
		this.agendamento = agendamento;
		this.cupomDesconto = cupomDesconto;
	}
	
	public FluxoCaixaResource(FluxoCaixa fluxoCaixa) {
		super();
		this.id = String.valueOf(fluxoCaixa.getId());
		this.formaPagamento = fluxoCaixa.getFormaPagamento();
		this.totalPagamento = String.valueOf(fluxoCaixa.getTotalPagamento());
		this.agendamento = String.valueOf(fluxoCaixa.getAgendamento().getId());
		this.cupomDesconto = String.valueOf(fluxoCaixa.getCupomDesconto().getId());
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public String getTotalPagamento() {
		return totalPagamento;
	}
	public void setTotalPagamento(String totalPagamento) {
		this.totalPagamento = totalPagamento;
	}

	public String getAgendamento() {
		return agendamento;
	}
	public void setAgendamento(String agendamento) {
		this.agendamento = agendamento;
	}
	public String getCupomDesconto() {
		return cupomDesconto;
	}
	public void setCupomDesconto(String cupomDesconto) {
		this.cupomDesconto = cupomDesconto;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
