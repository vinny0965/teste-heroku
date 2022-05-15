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
import org.springframework.web.bind.annotation.RestController;

import br.com.barbeariaFuraoJDBC.datasource.model.Administrador;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.resource.model.AdministradorResource;
import br.com.barbeariaFuraoJDBC.service.BuscarAdministradorServiceImpl;
import br.com.barbeariaFuraoJDBC.service.CadastrarAdministradorServiceImpl;

@RestController
@RequestMapping("/api")
public class AdministradorController {
	
	@Autowired
	private BuscarAdministradorServiceImpl buscarAdministradorServiceImpl;
	
	@Autowired
	private CadastrarAdministradorServiceImpl cadastrarAdministradorServiceImpl;
	
	@GetMapping(path = "/administradores")
	public List<AdministradorResource> listarAdministradores(){
		return buscarAdministradorServiceImpl.listarAdministradores();
	}
	
	@GetMapping(path = "/administrador/id/{id}")
	public AdministradorResource buscarAdministradorPorId(@PathVariable(name = "id",required = true)int id) throws  NotFoundException, ResourceExeption {
		return buscarAdministradorServiceImpl.buscarAdministradorPorId(id);
	}
	@GetMapping(path = "/administrador/cpf/{cpf}")
	public AdministradorResource buscarAdministradorPorId(@PathVariable(name = "cpf",required = true)String cpf) throws  NotFoundException, ResourceExeption {
		return buscarAdministradorServiceImpl.buscarAdministradorPorCpf(cpf);
	}
	
	@PostMapping(path = "/administrador/save")
	public ResponseEntity<Administrador> cadastrarAdministrador(@Valid @RequestBody AdministradorResource administradorResource) throws  ResourceExeption, NotFoundException {
		return cadastrarAdministradorServiceImpl.cadastrar(administradorResource);
	}
	
	@DeleteMapping(path = "/administrador/delete/id/{id}")
	public ResponseEntity<Void> deletarAdministrador(@PathVariable(name = "id",required = true)int id) throws NotFoundException {
		buscarAdministradorServiceImpl.deletarAdministrador(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(path = "/administrador/update/id/{id}")
	public void atualizarEndereco(@PathVariable(name = "id",required = true)int id, @RequestBody AdministradorResource administradorResource) throws ResourceExeption, NotFoundException {
		buscarAdministradorServiceImpl.atualizarAdministrador(administradorResource, id);
	}
	

}
