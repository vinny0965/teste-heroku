package br.com.barbeariaFuraoJDBC.conversor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.barbeariaFuraoJDBC.datasource.model.Endereco;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.resource.model.EnderecoResource;


@Component
public class EnderecoConversor {
	
	
	public Endereco conversor(EnderecoResource enderecoResource) throws ResourceExeption {
		
		try {
			Endereco endereco = new Endereco();
			Integer checkNumeroEndereco = checkNumeroEndereco(enderecoResource.getNumero());
			endereco.setLogradouro(enderecoResource.getLogradouro());
			endereco.setBairro(enderecoResource.getBairro());
			endereco.setCep(enderecoResource.getCep());
			endereco.setNumero(checkNumeroEndereco);
			return endereco;
		} catch (Exception e) {
			throw new ResourceExeption("não foi possível converter o resource para entidade: "+enderecoResource);
		}
		
	}
	
	public List<EnderecoResource> conversor(List<Endereco>endereco) throws ResourceExeption {
		List<EnderecoResource>enderecos = new ArrayList<>();
		try {
			for (Endereco end : endereco) {
				EnderecoResource enderecoResource = new EnderecoResource(end);
				enderecos.add(enderecoResource);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResourceExeption("não foi possível converter a entidade para o resource, entidade: "+endereco);
		}
		return enderecos;
	}
	
	public EnderecoResource conversor(Endereco endereco) throws ResourceExeption {
		try {
			EnderecoResource enderecoResource = new EnderecoResource(endereco);
			return enderecoResource;
		} catch (Exception e) {
			throw new ResourceExeption("não foi possível converter a entidade para o resouce, entidade: "+endereco);
		}
	}
	
	private Integer checkNumeroEndereco(String numero) {
		return Integer.parseInt(numero);
	}
	
	

}
