package br.com.barbeariaFuraoJDBC.conversor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.barbeariaFuraoJDBC.datasource.model.Administrador;
import br.com.barbeariaFuraoJDBC.datasource.model.Servico;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.repository.AdministradorRepository;
import br.com.barbeariaFuraoJDBC.resource.model.ServicoResource;

@Component
public class ServicoConversor {
	
	@Autowired
	private AdministradorRepository administradorRepository;
	
	public Servico conversor(ServicoResource servicoResource,int idAdm) throws ResourceExeption, NotFoundException  {
		Administrador buscarAdministradorPorId = administradorRepository.getById(idAdm);
		try {
			Servico servico = new Servico();
			servico.setTipoServico(servicoResource.getTipoServico());
			servico.setValor(Double.parseDouble(servicoResource.getValor()));
			servico.setAdministrador(buscarAdministradorPorId);
			return servico;
		} catch (Exception e) {
			throw new ResourceExeption("não foi possível converter o resource servico para entidade resouce: "+servicoResource);
		}
	}
	
	public List<ServicoResource>conversor(List<Servico>servicos) throws ResourceExeption{
		List<ServicoResource>servicoResources = new ArrayList<>();
		try {
			for (Servico servicoCurrent : servicos) {
				ServicoResource servicoResource = new ServicoResource(servicoCurrent);
				servicoResources.add(servicoResource);
			}
		} catch (Exception e) {
			throw new ResourceExeption("não foi possível converter os servicos para resources");
		}
		
		return servicoResources;
	}
	
	public ServicoResource conversor(Servico servico) throws ResourceExeption {
		try {
			ServicoResource servicoResource  = new ServicoResource(servico);
			return servicoResource;
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResourceExeption("não foi possível converter o serviço para resouce");		}
	}

}
