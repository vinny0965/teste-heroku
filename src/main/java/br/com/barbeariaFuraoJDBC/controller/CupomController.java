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

import br.com.barbeariaFuraoJDBC.datasource.model.CupomDesconto;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.resource.model.CupomDescontoResource;
import br.com.barbeariaFuraoJDBC.service.BuscarCupomServiceImpl;
import br.com.barbeariaFuraoJDBC.service.CadastrarCupomServiceImpl;

@RestController
@RequestMapping("/api")
public class CupomController {
	
	@Autowired
	private BuscarCupomServiceImpl buscarCupomServiceImpl;
	
	@Autowired
	private CadastrarCupomServiceImpl cadastrarCupomServiceImpl;
	
	@GetMapping(path = "/cupons")
	public List<CupomDescontoResource> listarCupons(){
		return  buscarCupomServiceImpl.listarCupons();
	}
	
	@GetMapping(path = "/cupom")
	public List<CupomDescontoResource> listarCuponsPorAdm(@RequestParam(value = "idAdm",defaultValue = "0")int idAdm){
		return  buscarCupomServiceImpl.listarCuponsPorAdm(idAdm);
	}
	
	@GetMapping(path = "/cupom/id/{id}")
	public ResponseEntity<CupomDescontoResource> busCupomDesconto(@PathVariable(name = "id",required = true)int id) throws NotFoundException, ResourceExeption {
		return buscarCupomServiceImpl.buscarCupomPorId(id);
	}
	
	@PutMapping(path = "/cupom/update/id/{id}")
	public ResponseEntity<Void> atualizarCupom(@PathVariable(name = "id",required = true)int id, @RequestBody CupomDescontoResource cupomDescontoResource, @RequestParam(value = "idAdministrador",defaultValue = "0")int idAdministrador) throws ResourceExeption, NotFoundException{
		return buscarCupomServiceImpl.atualizarCupomPorId(cupomDescontoResource, id,idAdministrador);
	}
	
	@DeleteMapping(path = "/cupom/delete/id/{id}")
	public ResponseEntity<Void> deletarCupomPorId(@PathVariable(name = "id",required = true)int id) throws NotFoundException {
		return buscarCupomServiceImpl.deletarCupomPorId(id);
	}
	
	@PostMapping(path = "/cupom/save")
	public ResponseEntity<CupomDesconto> cadastrarCupom(@Valid @RequestBody CupomDescontoResource cupomDescontoResource,@RequestParam(value = "idAdministrador",defaultValue = "0")int idAdministrador) throws ResourceExeption, NotFoundException {
		return cadastrarCupomServiceImpl.cadastrarCupom(cupomDescontoResource,idAdministrador);
	}
	
}
