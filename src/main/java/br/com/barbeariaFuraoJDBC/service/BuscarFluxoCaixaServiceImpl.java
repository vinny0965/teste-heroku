package br.com.barbeariaFuraoJDBC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.barbeariaFuraoJDBC.conversor.FluxoCaixaConversor;
import br.com.barbeariaFuraoJDBC.datasource.model.FluxoCaixa;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.repository.FluxoCaixaRepository;
import br.com.barbeariaFuraoJDBC.resource.model.FluxoCaixaResource;

@Service
public class BuscarFluxoCaixaServiceImpl {
	
	@Autowired
	private FluxoCaixaRepository fluxoCaixaRepository;
	
	@Autowired
	private FluxoCaixaConversor conversor;
	
//	public List<FluxoCaixa> listarFluxosCaixa(){
//		return fluxoCaixaRepository.list();
//	}
//	
	public List<FluxoCaixaResource> listarFluxosCaixa() throws ResourceExeption{
		return conversor.conversor(fluxoCaixaRepository.list());
	}
	public FluxoCaixaResource buscarFluxoCaixaPorId(int id) throws NotFoundException, ResourceExeption {
		FluxoCaixa byId = fluxoCaixaRepository.buscarPorId(id);
		if(byId == null) {
			throw new NotFoundException("não foi possível encontrar o fluxo do caixa pelo id");
		}else {
			return conversor.conversor(byId);

		}
	}
	
	public ResponseEntity<Void> atualizarFluxoCaixa(FluxoCaixaResource fluxoCaixaResource, int id,int idAgendamento,int idCupom) throws ResourceExeption, NotFoundException {
		FluxoCaixa conversor2 = conversor.conversor(fluxoCaixaResource, idAgendamento, idCupom);
		int updateById = fluxoCaixaRepository.update(conversor2, id);
		if(updateById == 0) {
			throw new ResourceExeption("não foi possível atualizar o fluxo");
		}else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	public ResponseEntity<Void> deletarPorId(int id) throws NotFoundException {
		int deleteById = fluxoCaixaRepository.delete(id);
		if(deleteById == 0) {
			throw new NotFoundException("não foi possível deletar por id");
		}else {
			return ResponseEntity.noContent().build();
		}
	}

}
