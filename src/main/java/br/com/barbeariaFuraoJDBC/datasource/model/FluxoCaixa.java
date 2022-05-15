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
@Table(name = "fluxos_caixa")
public class FluxoCaixa implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5394125328896737481L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String formaPagamento;
	private Double totalPagamento;
	
	
	@ManyToOne
	@JoinColumn(name = "id_agendamento",nullable = false)
	private Agendamento agendamento;
	
	@ManyToOne
	@JoinColumn(name = "id_cupom",nullable = false)
	private CupomDesconto cupomDesconto;

	@SuppressWarnings("unused")
	public FluxoCaixa() {
		
	}
	
	public FluxoCaixa(String formaPagamento, Double totalPagamento, Agendamento agendamento,
			CupomDesconto cupomDesconto) {
		this.formaPagamento = formaPagamento;
		this.totalPagamento = totalPagamento;
		this.agendamento = agendamento;
		this.cupomDesconto = cupomDesconto;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Double getTotalPagamento() {
		return totalPagamento;
	}

	public void setTotalPagamento(Double totalPagamento) {
		this.totalPagamento = totalPagamento;
	}



	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public CupomDesconto getCupomDesconto() {
		return cupomDesconto;
	}

	public void setCupomDesconto(CupomDesconto cupomDesconto) {
		this.cupomDesconto = cupomDesconto;
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
