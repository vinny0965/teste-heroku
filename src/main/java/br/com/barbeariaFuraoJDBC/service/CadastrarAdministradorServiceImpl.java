package br.com.barbeariaFuraoJDBC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.barbeariaFuraoJDBC.conversor.AdministradorConversor;
import br.com.barbeariaFuraoJDBC.datasource.model.Administrador;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.repository.AdministradorRepository;
import br.com.barbeariaFuraoJDBC.resource.model.AdministradorResource;

@Service
public class CadastrarAdministradorServiceImpl {

	@Autowired
	private AdministradorConversor administradorConversor;
	
	@Autowired
	private AdministradorRepository administradorRepository;
	
	public ResponseEntity<Administrador> cadastrar(AdministradorResource administradorResource) throws ResourceExeption, NotFoundException{
		Administrador conversor = administradorConversor.conversor(administradorResource);
		int create = administradorRepository.create(conversor);
		if(create == 0) {
			throw new ResourceExeption("não foi possível cadastrar o administrador, resource:"+administradorResource);
		}else {
			return new ResponseEntity<>(conversor, HttpStatus.CREATED);
		}
	}
}
