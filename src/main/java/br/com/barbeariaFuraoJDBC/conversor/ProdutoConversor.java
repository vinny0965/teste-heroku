package br.com.barbeariaFuraoJDBC.conversor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.barbeariaFuraoJDBC.datasource.model.Produto;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.resource.model.ProdutoResource;

@Component
public class ProdutoConversor {
	
	
	public Produto conversor(ProdutoResource produtoResource) throws ResourceExeption {
		try {
			Produto produto = new Produto();
			produto.setCodigoBarras(produtoResource.getCodigoBarras());
			produto.setDescricao(produtoResource.getDescricao());
			produto.setValor(Double.valueOf(produtoResource.getValor()));
			produto.setValidade(LocalDate.parse(produtoResource.getValidade()));
			return produto;
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResourceExeption("não foi possível converter o resource para entidade");
		}
	}
	
	public List<ProdutoResource>conversor(List<Produto>produtos) throws ResourceExeption{
		List<ProdutoResource>produtoResources = new ArrayList<ProdutoResource>();
		try {
			for (Produto produtoCurrent : produtos) {
				ProdutoResource produtoResource = new ProdutoResource(produtoCurrent);
				produtoResources.add(produtoResource);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResourceExeption("não foi possível converter o resource para entidade");

		}
		return produtoResources;
	}
	
	public ProdutoResource conversor(Produto produto) throws ResourceExeption {
		try {
			ProdutoResource produtoResource = new ProdutoResource(produto);
			return produtoResource;
		} catch (Exception e) {
			throw new ResourceExeption("não foi possível converter o resource para entidade");

			// TODO: handle exception
		}
	}

}
