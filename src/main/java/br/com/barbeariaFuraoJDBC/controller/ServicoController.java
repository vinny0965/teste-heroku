package br.com.barbeariaFuraoJDBC.controller;

import java.util.List;

import javax.validation.Valid;

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

import br.com.barbeariaFuraoJDBC.datasource.model.Servico;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.resource.model.ServicoResource;
import br.com.barbeariaFuraoJDBC.service.BuscarServicoServiceImpl;
import br.com.barbeariaFuraoJDBC.service.CadastrarServicoServiceImpl;
import lombok.val;

@RestController
@RequestMapping("/api")
public class ServicoController {
	
	@Autowired
	private BuscarServicoServiceImpl buscarServicoServiceImpl;
	
	@Autowired
	private CadastrarServicoServiceImpl cadastrarServicoServiceImpl;
	
	@GetMapping(path = "/servicos")
	public List<ServicoResource> listarServicos() throws ResourceExeption{
		return buscarServicoServiceImpl.listarServicos();
	}
	
	@GetMapping(path = "/servico")
	public List<ServicoResource> listarServicosPorAdm(@RequestParam(value = "idAdm",defaultValue = "0")int idAdm) throws ResourceExeption{
		return buscarServicoServiceImpl.listarServicosPorAdm(idAdm);
	}
	
	@GetMapping(path = "/servico/id/{id}")
	public ServicoResource busServico(@PathVariable(name = "id",required = true)int id) throws NotFoundException, ResourceExeption {
		return buscarServicoServiceImpl.buscarServicoPorId(id);
	}
	
	@GetMapping(path = "/servico/nome/{nome}")
	public ServicoResource busServicoPorTipo(@PathVariable(name = "nome",required = true)String tipo) throws NotFoundException, ResourceExeption {
		return buscarServicoServiceImpl.buscarServicoPorTipo(tipo);
	}
	
	@DeleteMapping(path = "/servico/delete/id/{id}")
	public void deletarServico(@PathVariable(name = "id",required = true)int id) throws NotFoundException {
		buscarServicoServiceImpl.deletarServico(id);
	}
	
	@PutMapping(path = "/servico/update/id/{id}")
	public void atualizarServico(@PathVariable(name = "id",required = true)int id, @RequestBody ServicoResource servicoResource,@RequestParam(value = "idAdministrador", defaultValue = "0")int idAdm) throws ResourceExeption, NotFoundException {
		buscarServicoServiceImpl.atualizarServico(servicoResource, id,idAdm);
	}
	
	@PostMapping(path = "/servico/save")
	public ResponseEntity<ServicoResource> cadastrar(@Valid @RequestBody ServicoResource servicoResource,@RequestParam(value = "idAdministrador", defaultValue = "0")int idAdm) throws ResourceExeption, NotFoundException {
		return cadastrarServicoServiceImpl.cadastrar(servicoResource,idAdm);
	}
	

}
