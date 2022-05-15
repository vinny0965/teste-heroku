package br.com.barbeariaFuraoJDBC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.barbeariaFuraoJDBC.datasource.model.Estoque;
import br.com.barbeariaFuraoJDBC.repository.EstoqueRepository;

@Service
public class BuscarEstoqueServiceImpl {
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	
	public List<Estoque>list(){
		return estoqueRepository.list();
	}
	

}
