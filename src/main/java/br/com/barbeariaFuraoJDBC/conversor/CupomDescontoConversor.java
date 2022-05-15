package br.com.barbeariaFuraoJDBC.conversor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.barbeariaFuraoJDBC.datasource.model.Administrador;
import br.com.barbeariaFuraoJDBC.datasource.model.CupomDesconto;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.repository.AdministradorRepository;
import br.com.barbeariaFuraoJDBC.resource.model.CupomDescontoResource;

@Component
public class CupomDescontoConversor {

	@Autowired
	private AdministradorRepository administradorRepository;
	
	public CupomDesconto conversor(CupomDescontoResource cupomDescontoResource,int idAdministrador) throws ResourceExeption, NotFoundException {
		Administrador buscarAdministradorPorId = administradorRepository.getById(idAdministrador);
		try {
			CupomDesconto cupomDesconto = new CupomDesconto();
			cupomDesconto.setAdministrador(buscarAdministradorPorId);
			cupomDesconto.setCodigo(cupomDescontoResource.getCodigo());
			cupomDesconto.setValor(Double.parseDouble(cupomDescontoResource.getValor()));
			return cupomDesconto;
		} catch (Exception e) {
			throw new ResourceExeption("não foi possível converter o resouce para entidade, resource: "+cupomDescontoResource);
		}
	}
	
	public List<CupomDescontoResource>cupons(List<CupomDesconto>cupomDescontos){
		List<CupomDescontoResource>cupomDescontoResources = new ArrayList<>();
		for (CupomDesconto cupomDescontoCurrent : cupomDescontos) {
			CupomDescontoResource cupomDescontoResource = new CupomDescontoResource(cupomDescontoCurrent.getId(),cupomDescontoCurrent.getCodigo(),
					String.valueOf(cupomDescontoCurrent.getValor()), String.valueOf(cupomDescontoCurrent.getAdministrador().getId()));
			cupomDescontoResources.add(cupomDescontoResource);
		}
		return cupomDescontoResources;
	}
	
	public CupomDescontoResource conversor(CupomDesconto cupomDescontoResource) throws ResourceExeption, NotFoundException {
		Administrador buscarAdministradorPorId = administradorRepository.getById((cupomDescontoResource.getAdministrador().getId()));
		try {
			CupomDescontoResource cupomDesconto = new CupomDescontoResource(cupomDescontoResource.getId(),cupomDescontoResource.getCodigo(),
					String.valueOf(cupomDescontoResource.getValor()),String.valueOf(buscarAdministradorPorId.getId()));
			return cupomDesconto;
		} catch (Exception e) {
			throw new ResourceExeption("não foi possível converter o resouce para entidade, resource: "+cupomDescontoResource);
		}
	}
	
}
