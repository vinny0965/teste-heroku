package br.com.barbeariaFuraoJDBC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.barbeariaFuraoJDBC.conversor.EnderecoConversor;
import br.com.barbeariaFuraoJDBC.datasource.model.Endereco;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.repository.EnderecoRepository;
import br.com.barbeariaFuraoJDBC.resource.model.EnderecoResource;

@Service
public class CadastrarEnderecoServiceImpl {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private EnderecoConversor enderecoConversor;
	
	public ResponseEntity<Endereco> cadastrar(EnderecoResource enderecoResource) throws ResourceExeption {
		Endereco conversor = enderecoConversor.conversor(enderecoResource);
		int create = enderecoRepository.create(conversor);
		if(create == 0) {
			throw new ResourceExeption("não foi possível cadastrar o endereco, resource:"+enderecoResource);
		}else {
			return new ResponseEntity<>(conversor,HttpStatus.OK);
		}
	}

}
