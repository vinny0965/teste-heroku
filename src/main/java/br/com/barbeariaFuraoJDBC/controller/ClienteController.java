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

import br.com.barbeariaFuraoJDBC.datasource.model.Cliente;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.resource.model.ClienteResource;
import br.com.barbeariaFuraoJDBC.service.BuscarClienteServiceImpl;
import br.com.barbeariaFuraoJDBC.service.CadastrarClienteServiceImpl;

@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	private BuscarClienteServiceImpl buscarClienteServiceImpl;
	
	@Autowired
	private CadastrarClienteServiceImpl cadastrarClienteServiceImpl;
	
	@GetMapping(path = "/clientes")
	public List<ClienteResource> listarClientes() throws ResourceExeption, NotFoundException{
		return buscarClienteServiceImpl.listarClientes();
	}
	
	@GetMapping(path = "/cliente")
	public List<Cliente> listarClientesPorId(@RequestParam(value = "endereco",defaultValue = "0")int id) throws ResourceExeption, NotFoundException{
		return buscarClienteServiceImpl.listarClietesPorEndereco(id);
	}
	
	@GetMapping(path = "/cliente/id/{id}")
	public Cliente buscarClientePorId(@PathVariable(name = "id",required = true)int id) throws NotFoundException, ResourceExeption {
		return buscarClienteServiceImpl.buscarClientePorId(id);
	}
	
	@GetMapping(path = "/cliente/cpf/{cpf}")
	public Cliente buscarClientePorCpf(@PathVariable(name = "cpf",required = true)String cpf) throws NotFoundException, ResourceExeption {
		return buscarClienteServiceImpl.buscarClientePorCpf(cpf);
	}
	
	@DeleteMapping(path = "/cliente/delete/id/{id}")
	public void deletarClientePorId(@PathVariable(name = "id",required = true)int id) throws NotFoundException {
		buscarClienteServiceImpl.deletarCliente(id);
	}
	
//	@PutMapping(path = "/cliente/update/id/{id}")
//	public void atualizarClientePorId(@PathVariable(name = "id",required = true)int id, @RequestBody ClienteResource clienteResource) throws ResourceExeption, NotFoundException {
//		buscarClienteServiceImpl.atualizarCliente(clienteResource, id);
//	}
	
	@PostMapping(path = "/cliente/save")
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody ClienteResource clienteResource,@RequestParam(value = "idEndereco",defaultValue = "0")int id) throws ResourceExeption, NotFoundException {
		return cadastrarClienteServiceImpl.cadastrar(clienteResource,id);
	}

}
