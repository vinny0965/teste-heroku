package br.com.barbeariaFuraoJDBC.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.barbeariaFuraoJDBC.datasource.model.Estoque;
import br.com.barbeariaFuraoJDBC.service.BuscarEstoqueServiceImpl;

@RestController
@RequestMapping("/api")
public class EstoqueController {
	
	@Autowired
	private BuscarEstoqueServiceImpl buscarEstoqueServiceImpl;
	
	
	@GetMapping(path = "/estoques")
	private List<Estoque>buscar(){
		return buscarEstoqueServiceImpl.list();
	}

}
