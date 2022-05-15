package br.com.barbeariaFuraoJDBC.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.barbeariaFuraoJDBC.datasource.model.Endereco;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.resource.model.EnderecoResource;
import br.com.barbeariaFuraoJDBC.service.BuscarEnderecoServiceImpl;
import br.com.barbeariaFuraoJDBC.service.CadastrarEnderecoServiceImpl;

@RestController
@RequestMapping("/api")
public class EnderecoController {
	
	@Autowired
	private CadastrarEnderecoServiceImpl cadastrarEnderecoServiceImpl;
	
	@Autowired
	private BuscarEnderecoServiceImpl buscarEnderecoServiceImpl;
	
	@PostMapping(path = "/endereco/save")
	public ResponseEntity<Endereco> cadastrarEndereco(@Valid @RequestBody EnderecoResource enderecoResource) throws ResourceExeption {
		return cadastrarEnderecoServiceImpl.cadastrar(enderecoResource);
	}
	
	@GetMapping(path = "/enderecos")
	public ResponseEntity<List<EnderecoResource>> listarEnderecos() throws ResourceExeption{
		return ResponseEntity.ok(buscarEnderecoServiceImpl.listarEnderecos());
	}
	
	@GetMapping(path = "/endereco/id/{id}")
	public ResponseEntity<EnderecoResource> buscarEndereco(@PathVariable(name = "id",required = true)int id) throws NotFoundException, ResourceExeption {
		return ResponseEntity.ok(buscarEnderecoServiceImpl.buscarEnderecoPorId(id));
	}
	
	@PutMapping(path = "/endereco/update/id/{id}")
	public void  atualizarEndereco(@PathVariable(name = "id",required = true)int id, @RequestBody EnderecoResource enderecoResource) throws ResourceExeption {
		 buscarEnderecoServiceImpl.AtualizarEndereco(enderecoResource, id);
	}
	
	@DeleteMapping(path = "/endereco/delete/id/{id}")
	public ResponseEntity<Void> deletarEndereco(@PathVariable(name = "id", required = true)int id) throws NotFoundException {
		buscarEnderecoServiceImpl.deletarEndereco(id);
		return ResponseEntity.noContent().build();  
	}

}
