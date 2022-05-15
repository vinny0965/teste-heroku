package br.com.barbeariaFuraoJDBC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.barbeariaFuraoJDBC.conversor.CupomDescontoConversor;
import br.com.barbeariaFuraoJDBC.datasource.model.CupomDesconto;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.repository.CupomRepository;
import br.com.barbeariaFuraoJDBC.resource.model.CupomDescontoResource;

@Service
public class BuscarCupomServiceImpl {

	@Autowired
	private CupomRepository cupomRepository;
	
	@Autowired
	private CupomDescontoConversor conversor;
	
	public List<CupomDescontoResource> listarCupons(){
		return conversor.cupons(cupomRepository.list());
	}
	
	public List<CupomDescontoResource> listarCuponsPorAdm(int idAdm){
		return conversor.cupons(cupomRepository.listByAdm(idAdm));
	}
	
	
	public ResponseEntity<CupomDescontoResource> buscarCupomPorId(int id) throws  NotFoundException, ResourceExeption {
		CupomDesconto byId = cupomRepository.getById(id);
		if(byId==null) {
			throw new NotFoundException("cupom de desconto não encontrado");
		}else {
			return ResponseEntity.ok(conversor.conversor(byId));
		}
	}
	
	
	public ResponseEntity<Void> deletarCupomPorId(int id) throws  NotFoundException {
		int deleteById = cupomRepository.deleteById(id);
		if(deleteById==0) {
			throw new NotFoundException("não foi pessível deletar o cupom");
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	public ResponseEntity<Void> atualizarCupomPorId(CupomDescontoResource cupomDescontoResource, int id,int idAdministrador) throws  ResourceExeption, NotFoundException {
		if(idAdministrador >0) {
			CupomDesconto conversor2 = conversor.conversor(cupomDescontoResource,idAdministrador);
			int updateById = cupomRepository.updateById(conversor2,id);
			if(updateById == 0) {
				throw new ResourceExeption("não foi possível atualizar o cupom");
			}else {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
		}
		throw new NotFoundException("RequestParam (idAdministrador)requerido");
		
	}
	

}
