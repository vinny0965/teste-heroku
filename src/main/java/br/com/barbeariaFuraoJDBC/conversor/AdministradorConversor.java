package br.com.barbeariaFuraoJDBC.conversor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.barbeariaFuraoJDBC.datasource.model.Administrador;
import br.com.barbeariaFuraoJDBC.datasource.model.Endereco;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.repository.EnderecoRepository;
import br.com.barbeariaFuraoJDBC.resource.model.AdministradorResource;

@Component
public class AdministradorConversor {

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Administrador conversor(AdministradorResource administradorResource)
			throws ResourceExeption, NotFoundException {
		Integer checkIdEndereco = checkIdEndereco(administradorResource.getEndereco());
		Endereco buscarEnderecoPorId = enderecoRepository.getById(checkIdEndereco);

		try {
			Administrador administrador = new Administrador();
			LocalDate ceckDataNascimento = ceckDataNascimento(administradorResource.getDataNascimento());
			if (buscarEnderecoPorId != null) {
				Endereco endereco = buscarEnderecoPorId;
				administrador.setEndereco(endereco);
			}
			administrador.setCpf(administradorResource.getCpf());
			administrador.setDataNascimento(ceckDataNascimento);
			administrador.setEmail(administradorResource.getEmail());
			administrador.setLogin(administradorResource.getLogin());
			administrador.setSenha(administradorResource.getSenha());
			administrador.setSexo(administradorResource.getSexo());
			administrador.setTelefone(administradorResource.getTelefone());
			administrador.setNome(administradorResource.getNome());
			return administrador;
		} catch (Exception e) {
			throw new ResourceExeption(
					"não foi possível converter o resouce para entidade, resource:" + administradorResource);
		}
	}
	
	public List<AdministradorResource> conversor(List<Administrador>administradors){
		List<AdministradorResource>administradorResources = new ArrayList<>();
		try {
			for (Administrador administrador : administradors) {
				AdministradorResource administradorResource  = new AdministradorResource();
				administradorResource.setId(administrador.getId());
				administradorResource.setCpf(administrador.getCpf());
				administradorResource.setDataNascimento(String.valueOf(administrador.getDataNascimento()));
				administradorResource.setEmail(administrador.getEmail());
				administradorResource.setEndereco(String.valueOf(administrador.getEndereco().getId()));
				administradorResource.setLogin(administrador.getLogin());
				administradorResource.setNome(administrador.getNome());
				administradorResource.setSenha(administrador.getSenha());
				administradorResource.setSexo(administrador.getSexo());
				administradorResource.setTelefone(administrador.getTelefone());
				administradorResources.add(administradorResource);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return administradorResources;
	}

	public AdministradorResource conversor(Administrador administradorResource)
			throws ResourceExeption, NotFoundException {
		try {
			AdministradorResource administrador = new AdministradorResource();
			LocalDate ceckDataNascimento = (administradorResource.getDataNascimento());
			administrador.setId(administradorResource.getId());
			administrador.setEndereco(String.valueOf(administradorResource.getEndereco().getId()));
			administrador.setCpf(administradorResource.getCpf());
			administrador.setDataNascimento(String.valueOf(ceckDataNascimento));
			administrador.setEmail(administradorResource.getEmail());
			administrador.setLogin(administradorResource.getLogin());
			administrador.setSenha(administradorResource.getSenha());
			administrador.setSexo(administradorResource.getSexo());
			administrador.setTelefone(administradorResource.getTelefone());
			administrador.setNome(administradorResource.getNome());
			
			return administrador;
		} catch (Exception e) {
			throw new ResourceExeption(
					"não foi possível converter o resouce para entidade, resource:" + administradorResource);
		}
	}
	
	public LocalDate ceckDataNascimento(String dataNascimento) {
		return LocalDate.parse(dataNascimento);
	}

	public Integer checkIdEndereco(String idEndereco) {
		return Integer.parseInt(idEndereco);
	}

}
