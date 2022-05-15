//package br.com.barbeariaFuraoJDBC.service;
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
//public class CadastrarCaixaServiceImpl {
//	
//	@Autowired
//	private CaixaRepository caixaRepository;
//	
//	@Autowired
//	private CaixaConversor caixaConversor;
//	
//	public void CadastrarCaixa(CaixaResource caixaResource) throws ResourceExeption, NotFoundException {
//		Caixa conversor = caixaConversor.conversor(caixaResource);
//		if(caixaRepository.create(conversor)==0) {
//			throw new ResourceExeption("não foi possível cadastrar o caixa, resource: "+caixaResource);
//		}
//	}
//
//}
