package br.com.barbeariaFuraoJDBC.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.resource.model.ProdutoResource;
import br.com.barbeariaFuraoJDBC.service.BuscarProdutoServiceImpl;

@RestController
@RequestMapping("/api")
public class ProdutoController {
	
	@Autowired
	private BuscarProdutoServiceImpl buscarProdutoServiceImpl;
	
	@RequestMapping(value = "/produtos")
	public List<ProdutoResource>listar() throws ResourceExeption{
		return buscarProdutoServiceImpl.listar();
	}
	
	@RequestMapping(value = "/produto/id/{id}")
	public ProdutoResource buscarProdutoPorId(@PathVariable(value = "id",required = true)int id) throws ResourceExeption, NotFoundException {
		return buscarProdutoServiceImpl.buscarPorId(id);
	}

}
