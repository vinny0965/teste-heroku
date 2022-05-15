package br.com.barbeariaFuraoJDBC.conversor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.barbeariaFuraoJDBC.datasource.model.Cliente;
import br.com.barbeariaFuraoJDBC.datasource.model.Endereco;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.repository.EnderecoRepository;
import br.com.barbeariaFuraoJDBC.resource.model.ClienteResource;

@Component
public class ClienteConversor {
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente converter(ClienteResource clienteResource, int id) throws ResourceExeption, NotFoundException {
		Endereco buscarEnderecoPorId = enderecoRepository.getById(id);
		try {
			Cliente cliente = new Cliente();
			cliente.setNome(clienteResource.getNome());
			cliente.setCpf(clienteResource.getCpf());
			cliente.setDataNascimento(LocalDate.parse(clienteResource.getDataNascimento()));
			cliente.setEmail(clienteResource.getEmail());
			cliente.setSexo(clienteResource.getSexo());
			cliente.setTelefone(clienteResource.getTelefone());
			cliente.setEndereco(buscarEnderecoPorId);
			return cliente;
			
		} catch (Exception e) {
			throw new ResourceExeption("não foi possível converter o cliente resource para entidade resource:"+clienteResource);
		}
	}
	
	public List<ClienteResource> converter(List<Cliente> clienteResource) throws ResourceExeption, NotFoundException {
		List<ClienteResource>clienteResources = new ArrayList<>();
		try {
			for (Cliente clienteCurrent : clienteResource) {
				ClienteResource cliente = new ClienteResource();
				cliente.setId(clienteCurrent.getId());
				cliente.setNome(clienteCurrent.getNome());
				cliente.setCpf(clienteCurrent.getCpf());
				cliente.setDataNascimento(String.valueOf(clienteCurrent.getDataNascimento()));
				cliente.setEmail(clienteCurrent.getEmail());
				cliente.setSexo(clienteCurrent.getSexo());
				cliente.setTelefone(clienteCurrent.getTelefone());
				cliente.setIdEndereco(String.valueOf(clienteCurrent.getEndereco().getId()));
				clienteResources.add(cliente);
			}
		} catch (Exception e) {
			throw new ResourceExeption("não foi possível converter o cliente resource para entidade resource:"+clienteResource);
		}
		return clienteResources;
	}
	
	public ClienteResource conv(Cliente clienteResource) throws ResourceExeption, NotFoundException {
		Endereco buscarEnderecoPorId = enderecoRepository.getById(clienteResource.getEndereco().getId());
		try {
			ClienteResource cliente = new ClienteResource();
			cliente.setId(clienteResource.getId());
			cliente.setNome(clienteResource.getNome());
			cliente.setCpf(clienteResource.getCpf());
			cliente.setDataNascimento(String.valueOf(clienteResource.getDataNascimento()));
			cliente.setEmail(clienteResource.getEmail());
			cliente.setSexo(clienteResource.getSexo());
			cliente.setTelefone(clienteResource.getTelefone());
			cliente.setIdEndereco(String.valueOf(buscarEnderecoPorId.getId()));
			return cliente;
			
		} catch (Exception e) {
			throw new ResourceExeption("não foi possível converter o cliente resource para entidade resource:"+clienteResource);
		}
	}
	
}
