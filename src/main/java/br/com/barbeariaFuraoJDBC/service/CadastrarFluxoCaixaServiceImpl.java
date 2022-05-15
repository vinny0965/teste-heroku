package br.com.barbeariaFuraoJDBC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.barbeariaFuraoJDBC.conversor.FluxoCaixaConversor;
import br.com.barbeariaFuraoJDBC.datasource.model.Agendamento;
import br.com.barbeariaFuraoJDBC.datasource.model.CupomDesconto;
import br.com.barbeariaFuraoJDBC.datasource.model.FluxoCaixa;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.repository.AgendamentoRepository;
import br.com.barbeariaFuraoJDBC.repository.FluxoCaixaRepository;
import br.com.barbeariaFuraoJDBC.resource.model.FluxoCaixaResource;

@Service
public class CadastrarFluxoCaixaServiceImpl {
	
	@Autowired
	private FluxoCaixaRepository fluxoCaixaRepository;
	
	@Autowired
	private FluxoCaixaConversor fluxoCaixaConversor;
	
	
	public ResponseEntity<FluxoCaixaResource> cadastar(FluxoCaixaResource fluxoCaixaResource,int idAgendamento,int idCupom) throws ResourceExeption, NotFoundException {
		FluxoCaixa conversor = fluxoCaixaConversor.conversor(fluxoCaixaResource,idAgendamento,idCupom);
		int create = fluxoCaixaRepository.create(conversor);
		if(create == 0) {
			throw new ResourceExeption("não foi possível criar um fluxo de caixa, resouce: "+fluxoCaixaResource);
		}else {
			return new ResponseEntity<>(fluxoCaixaConversor.conversor(conversor),HttpStatus.CREATED);
		}
	}
	
//	public ResponseEntity<FluxoCaixaDto> cadastarFluxoCaixa(FluxoCaixaDto fluxoCaixaDto,int idAgendamento, int idCaixa, int idCupom) throws ResourceExeption, NotFoundException {
//		Agendamento buscarAgendamento = agendamentoServiceImpl.buscarAgendamento(idAgendamento);
//		Caixa buscarCaixaPorId = buscarCaixaServiceImpl.buscarCaixaPorId(idCaixa);
//		CupomDesconto buscarCupomPorId = buscarCupomServiceImpl.buscarCupomPorIds(idCupom);
//		fluxoCaixaDto.setAgendamento(buscarAgendamento);
//		fluxoCaixaDto.setCaixa(buscarCaixaPorId);
//		fluxoCaixaDto.setCupomDesconto(buscarCupomPorId);
//		FluxoCaixa map = mapper.map(fluxoCaixaDto, FluxoCaixa.class);
//		boolean create = FluxoCaixaDao.createFluxoCaixa(map,idAgendamento,idCaixa,idCupom);
//		if(create == false) {
//			throw new ResourceExeption("não foi possível criar um fluxo de caixa, resouce: "+fluxoCaixaDto);
//		}else {
//			return new ResponseEntity<>(fluxoCaixaDto,HttpStatus.CREATED);
//		}
//	}
	

}
