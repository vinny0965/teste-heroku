package br.com.barbeariaFuraoJDBC.conversor;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.barbeariaFuraoJDBC.datasource.model.Agendamento;
import br.com.barbeariaFuraoJDBC.datasource.model.Cliente;
import br.com.barbeariaFuraoJDBC.datasource.model.Servico;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.repository.ClienteRepository;
import br.com.barbeariaFuraoJDBC.repository.ServicoRepository;
import br.com.barbeariaFuraoJDBC.resource.model.AgendamentoResource;

@Component
public class AgendamentoConversor {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	public Agendamento conversor(AgendamentoResource agendamentoResource,int idCliente,int idServico) throws ResourceExeption, NotFoundException {
		Cliente buscarClientePorId = clienteRepository.getById(idCliente);
		Servico buscarServicoPorId = servicoRepository.getById(idServico);
		try {
			Agendamento agendamento = new Agendamento();
			agendamento.setDataAgendamento(LocalDate.parse(agendamentoResource.getDataAgendamento()));
			agendamento.setHorario(Time.valueOf(agendamentoResource.getHorario()));
			agendamento.setFuncionario(agendamentoResource.getFuncionario());
			agendamento.setServico(buscarServicoPorId);
			agendamento.setCliente(buscarClientePorId);
			return agendamento;
		} catch (Exception e) {
			throw new ResourceExeption("não foi  o resouce para entidade, resouce: "+agendamentoResource);
		}
	}
	
	public List<AgendamentoResource>conversor(List<Agendamento>agendamentos) throws ResourceExeption{
		List<AgendamentoResource>agendamentoResources = new ArrayList<>();
		try {
			for (Agendamento agendamentoCurrent : agendamentos) {
				AgendamentoResource agendamentoResource = new AgendamentoResource(String.valueOf(agendamentoCurrent.getId()),
						String.valueOf(agendamentoCurrent.getDataAgendamento()), String.valueOf(agendamentoCurrent.getHorario()),
						agendamentoCurrent.getFuncionario(),String.valueOf(agendamentoCurrent.getCliente().getId()),
						String.valueOf(agendamentoCurrent.getServico().getId()));
				agendamentoResources.add(agendamentoResource);
			}
		} catch (Exception e) {
			throw new ResourceExeption("não foi possível converter o resource para entidade");
		}
		return agendamentoResources;
	}
	
	
	public AgendamentoResource conversor(Agendamento agendamento) throws NotFoundException, ResourceExeption {
		Cliente byIdCliente = clienteRepository.getById(agendamento.getCliente().getId());
		Servico byIdServico = servicoRepository.getById(agendamento.getServico().getId());
		try {
			AgendamentoResource agendamentoResource = new AgendamentoResource();
			agendamentoResource.setId(String.valueOf(agendamento.getId()));
			agendamentoResource.setDataAgendamento(String.valueOf(agendamento.getDataAgendamento()));
			agendamentoResource.setHorario(String.valueOf(agendamento.getHorario()));
			agendamentoResource.setFuncionario(agendamento.getFuncionario());
			agendamentoResource.setIdServico(String.valueOf(byIdServico.getId()));
			agendamentoResource.setIdCliente(String.valueOf(byIdCliente));
			return agendamentoResource;
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResourceExeption("não foi possível converter o resouce para entidade");
		}
	}

}
