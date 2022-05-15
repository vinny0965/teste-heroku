//package br.com.barbeariaFuraoJDBC.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import br.com.barbeariaFuraoJDBC.conversor.CaixaConversor;
//import br.com.barbeariaFuraoJDBC.datasource.model.Caixa;
//import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
//import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
//import br.com.barbeariaFuraoJDBC.repository.CaixaRepository;
//import br.com.barbeariaFuraoJDBC.resource.model.CaixaResource;
//
//@Service
//public class BuscarCaixaServiceImpl {
//	
//	@Autowired
//	private CaixaConversor caixaConversor;
////	
//	@Autowired
//	private CaixaRepository caixaRepository;
//	
//	
//	public List<CaixaResource> listarCaixas(){
//		return caixaConversor.convert(caixaRepository.list());
//	}
//	
//	public Caixa buscarCaixaPorId(int id) throws NotFoundException {
//		Caixa byId = caixaRepository.getById(id);
//		if(byId==null) {
//			throw new NotFoundException("não foi possível encontrar o caixa pelo id:" +id); 
//		}else {
//			return byId;
//		}
//	}
//	
//	public void deletarCaixa(int id) throws  NotFoundException {
//			if(caixaRepository.deleteById(id)==0) {
//				throw new NotFoundException("não foi possível deletar o caixa pelo id: "+id);
//			}
//	}
//	
//	public void atualizarCaixa(CaixaResource caixaResource, int id) throws  ResourceExeption, NotFoundException {
//		Caixa conversor = caixaConversor.conversor(caixaResource);
//		int updateById = caixaRepository.updateById(conversor, id);
//		if(updateById == 0) {
//			throw new ResourceExeption("não foi possível atualizar o caixa pelo id:" +id);
//		}
//		
//	}
//	
//	
//
//}
