package br.com.barbeariaFuraoJDBC.resource.model;

import com.fasterxml.jackson.annotation.JsonProperty;



public class AgendamentoResource {
	
	private String id;
	
	@JsonProperty("data_agendamento")
	private String dataAgendamento;
	
	@JsonProperty("horario")
	private String horario;
	
	@JsonProperty("funcionario")
	private String funcionario;
	
	@JsonProperty("id_cliente")
	private String idCliente;
	
	@JsonProperty("id_servico")
	private String idServico;

	
	
	public AgendamentoResource() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AgendamentoResource(String id,String dataAgendamento, String horario, String funcionario, String idCliente,
			String idServico) {
		super();
		this.id = id;
		this.dataAgendamento = dataAgendamento;
		this.horario = horario;
		this.funcionario = funcionario;
		this.idCliente = idCliente;
		this.idServico = idServico;
	}

	public String getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(String dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdServico() {
		return idServico;
	}

	public void setIdServico(String idServico) {
		this.idServico = idServico;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AgendamentoResource [id=" + id + ", dataAgendamento=" + dataAgendamento + ", horario=" + horario
				+ ", funcionario=" + funcionario + ", idCliente=" + idCliente + ", idServico=" + idServico + "]";
	}
	
	
	

}
