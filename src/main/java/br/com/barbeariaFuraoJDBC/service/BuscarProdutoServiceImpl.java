package br.com.barbeariaFuraoJDBC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.barbeariaFuraoJDBC.conversor.ProdutoConversor;
import br.com.barbeariaFuraoJDBC.datasource.model.Produto;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.repository.ProdutoRepository;
import br.com.barbeariaFuraoJDBC.resource.model.ProdutoResource;

@Service
public class BuscarProdutoServiceImpl {
	
	@Autowired
	private ProdutoConversor produtoConversor;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	public List<ProdutoResource>listar() throws ResourceExeption{
		return produtoConversor.conversor(produtoRepository.list());
	}
	
	public ProdutoResource buscarPorId(int id) throws ResourceExeption, NotFoundException {
		Produto buscarPorId = produtoRepository.buscarPorId(id);
		return produtoConversor.conversor(buscarPorId);
	}

}
