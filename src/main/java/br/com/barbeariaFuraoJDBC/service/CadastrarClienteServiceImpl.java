package br.com.barbeariaFuraoJDBC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.barbeariaFuraoJDBC.conversor.ClienteConversor;
import br.com.barbeariaFuraoJDBC.datasource.model.Cliente;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.repository.ClienteRepository;
import br.com.barbeariaFuraoJDBC.resource.model.ClienteResource;

@Service
public class CadastrarClienteServiceImpl {
	
	@Autowired
	private ClienteConversor clienteConversor;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public ResponseEntity<Cliente> cadastrar(ClienteResource clienteResource, int id) throws ResourceExeption, NotFoundException {
		Cliente converter = clienteConversor.converter(clienteResource,id);
		if(clienteRepository.Create(converter) == 0){
			throw new ResourceExeption("não foi possível converter o resource para entidade, resource: "+clienteResource);
		}
		return new ResponseEntity<>(converter,HttpStatus.CREATED);
	}
	

}
