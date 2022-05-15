package br.com.barbeariaFuraoJDBC.datasource.model;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "agendamentos")
public class Agendamento implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6446916901187561356L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate dataAgendamento;
	private Time horario;
	private String funcionario;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente",nullable = false)
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "id_servico",nullable = false)
	private Servico servico;

	@SuppressWarnings("unused")
	public Agendamento() {
	}
	public Agendamento(LocalDate dataAgendamento, Time horario, String funcionario, Cliente cliente,
			Servico servico) {
		this.dataAgendamento = dataAgendamento;
		this.horario = horario;
		this.funcionario = funcionario;
		this.cliente = cliente;
		this.servico = servico;
	}

	public LocalDate getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDate dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public Time getHorario() {
		return horario;
	}

	public void setHorario(Time horario) {
		this.horario = horario;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
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
