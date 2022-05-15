package br.com.barbeariaFuraoJDBC.conversor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.barbeariaFuraoJDBC.datasource.model.Agendamento;
import br.com.barbeariaFuraoJDBC.datasource.model.CupomDesconto;
import br.com.barbeariaFuraoJDBC.datasource.model.FluxoCaixa;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.repository.AgendamentoRepository;
import br.com.barbeariaFuraoJDBC.repository.CupomRepository;
import br.com.barbeariaFuraoJDBC.resource.model.FluxoCaixaResource;

@Component
public class FluxoCaixaConversor {
	
	@Autowired
	private CupomRepository cupomRepository;
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	
	public FluxoCaixa conversor(FluxoCaixaResource fluxoCaixaResource,int idAgendamento, int idCupom) throws NotFoundException, ResourceExeption {
		Agendamento findById = agendamentoRepository.findById(idAgendamento);
		CupomDesconto byId = cupomRepository.getById(idCupom);
		try {
			FluxoCaixa fluxoCaixa = new FluxoCaixa();
			fluxoCaixa.setAgendamento(findById);
			fluxoCaixa.setCupomDesconto(byId);
			fluxoCaixa.setFormaPagamento(fluxoCaixaResource.getFormaPagamento());
			fluxoCaixa.setTotalPagamento(Double.parseDouble(fluxoCaixaResource.getTotalPagamento()));
			return fluxoCaixa;
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResourceExeption("não foi possível converter o resource para entidade, resource: "+fluxoCaixaResource);
		}
	}

	public List<FluxoCaixaResource> conversor(List<FluxoCaixa>caixas) throws ResourceExeption{
		List<FluxoCaixaResource>caixaResources = new ArrayList<>();
		try {
			for (FluxoCaixa fluxoCaixa : caixas) {
				FluxoCaixaResource caixaResource = new FluxoCaixaResource(fluxoCaixa);
				caixaResources.add(caixaResource);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResourceExeption("não foi possível converter a entidade para resouce");

		}
		return caixaResources;
	}
	
	public FluxoCaixaResource conversor(FluxoCaixa fluxoCaixa) throws ResourceExeption {
		try {
			FluxoCaixaResource fluxoCaixaResource = new FluxoCaixaResource(fluxoCaixa);
			return fluxoCaixaResource;
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResourceExeption("não foi possível converter o fluxo caixa para resource");

		}
	}
}
