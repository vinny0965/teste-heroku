package br.com.barbeariaFuraoJDBC.resource.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.barbeariaFuraoJDBC.datasource.model.Servico;

public class ServicoResource {
	
	private int id;
	
	@NotNull(message = "campo tipo_servico requerido")
	@JsonProperty("tipo_servico")
	private String tipoServico;

	@NotNull(message = "campo tipo_servico requerido")
	@JsonProperty("valor")
	private String valor;
	
	@JsonProperty("id_administrador")
	private String idAdministrador;

	public ServicoResource(String tipoServico, String valor, String idAdministrador) {
		super();
		this.tipoServico = tipoServico;
		this.valor = valor;
		this.idAdministrador = idAdministrador;
	}
	
	public ServicoResource(Servico servico) {
		super();
		this.id = servico.getId();
		this.tipoServico = servico.getTipoServico();
		this.valor = String.valueOf(servico.getValor());
		this.idAdministrador = String.valueOf(servico.getAdministrador().getId());
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	
	public int getId() {
		return id;
	}
	
	private void set() {
		// TODO Auto-generated method stub

	}

}
