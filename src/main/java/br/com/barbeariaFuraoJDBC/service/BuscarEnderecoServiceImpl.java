package br.com.barbeariaFuraoJDBC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.barbeariaFuraoJDBC.conversor.EnderecoConversor;
import br.com.barbeariaFuraoJDBC.datasource.model.Endereco;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.repository.EnderecoRepository;
import br.com.barbeariaFuraoJDBC.resource.model.EnderecoResource;

@Service
public class BuscarEnderecoServiceImpl {

	@Autowired
	private EnderecoConversor conversor;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public List<EnderecoResource> listarEnderecos() throws ResourceExeption {
		List<EnderecoResource> list = conversor.conversor(enderecoRepository.list());
		return list;
	}

	public EnderecoResource buscarEnderecoPorId(int id) throws ResourceExeption, NotFoundException {
		Endereco enderecosById = enderecoRepository.getById(id);
		EnderecoResource conversor2 = conversor.conversor(enderecosById);
		return conversor2;
		

	}
	

	public void AtualizarEndereco(EnderecoResource enderecoResource, int id) throws ResourceExeption {
		Endereco conversor2 = conversor.conversor(enderecoResource);
		if (enderecoRepository.updateById(conversor2, id) == 0) {
			throw new ResourceExeption("Endereco não encontrado pelo id");
		}
	}

	public void deletarEndereco(int id) throws NotFoundException {
		int deleteById = enderecoRepository.deleteById(id);
		if (deleteById == 0) {
			throw new NotFoundException("Endereco não encontrado pelo id");
		}

	}

}
