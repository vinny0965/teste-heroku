package br.com.barbeariaFuraoJDBC.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.barbeariaFuraoJDBC.datasource.model.Agendamento;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.resource.model.AgendamentoResource;
import br.com.barbeariaFuraoJDBC.service.BuscarAgendamentoServiceImpl;
import br.com.barbeariaFuraoJDBC.service.CadastrarAgendamentoServiceImpl;

@RestController
@RequestMapping("/api")
public class AgendamentoController {
	
	@Autowired
	private BuscarAgendamentoServiceImpl buscarAgendamentoServiceImpl;
	
	@Autowired
	private CadastrarAgendamentoServiceImpl cadastrarAgendamentoServiceImpl;
	
	@GetMapping(path = "/agendamentos")
	public List<AgendamentoResource> listarAgendamentos() throws ResourceExeption{
		return buscarAgendamentoServiceImpl.listarAgendamentos();
	}
	
	@GetMapping(path = "/agendamento")
	public List<AgendamentoResource> listarAgendamentosPorCliente(@RequestParam(value = "cliente",defaultValue = "0")int id) throws ResourceExeption{
		return buscarAgendamentoServiceImpl.listarAgendamentosPorId(id);
	}
	
//	@GetMapping(path = "/agendamento/id/{id}")
//	public Agendamento buscarAgendamentoPorId(@PathVariable(name = "id",required = true)int id) throws NotFoundException {
//		return buscarAgendamentoServiceImpl.buscarAgendamento(id);
//	}
//	
	@PostMapping(path = "/agendamento/save")
	public ResponseEntity<Agendamento> CadastrarAgendamento(@RequestBody AgendamentoResource agendamentoResource,@RequestParam(value = "idCliente",defaultValue = "0")int idClinte,@RequestParam(value = "idServico",defaultValue = "0")int idServico) throws ResourceExeption, NotFoundException {
		return cadastrarAgendamentoServiceImpl.cadastrarAgendamento(agendamentoResource,idClinte,idServico);
	}
	
//	@PutMapping(path = "/agendamento/update/id/{id}")
//	public ResponseEntity<Void> atualizarAgendamento(@PathVariable(name = "id",required = true)int id, @RequestBody AgendamentoResource agendamentoResource) throws ResourceExeption {
//		return buscarAgendamentoServiceImpl.atualizarAgendamento(agendamentoResource, id);
//	}
//	
//	@DeleteMapping(path = "/agendamento/delete/id/{id}")
//	public ResponseEntity<Void> deletarAgendamento(@PathVariable(name = "id",required = true)int id) throws NotFoundException {
//		return buscarAgendamentoServiceImpl.deletarAgendamentoPorId(id);
//	}
	
	
	

}
