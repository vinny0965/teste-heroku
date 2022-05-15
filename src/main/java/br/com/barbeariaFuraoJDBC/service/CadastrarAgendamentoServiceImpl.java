package br.com.barbeariaFuraoJDBC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.barbeariaFuraoJDBC.conversor.AgendamentoConversor;
import br.com.barbeariaFuraoJDBC.datasource.model.Agendamento;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.repository.AgendamentoRepository;
import br.com.barbeariaFuraoJDBC.resource.model.AgendamentoResource;

@Service
public class CadastrarAgendamentoServiceImpl {
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;

	@Autowired
	private AgendamentoConversor agendamentoConversor;
//	
	public ResponseEntity<Agendamento> cadastrarAgendamento(AgendamentoResource agendamentoResource,int idCliente, int idServico) throws ResourceExeption, NotFoundException {
		Agendamento conversor = agendamentoConversor.conversor(agendamentoResource,idCliente,idServico);
		if(agendamentoRepository.create(conversor)==0) {
			throw new ResourceExeption("Não foi possível cadastrar o agendamento, resource: "+agendamentoResource);
		}else {
			return new ResponseEntity<>(conversor,HttpStatus.CREATED);
		}
	}

}
