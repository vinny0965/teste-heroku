package br.com.barbeariaFuraoJDBC.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.barbeariaFuraoJDBC.datasource.model.Estoque;
import br.com.barbeariaFuraoJDBC.datasource.model.Produto;

@Repository
public class EstoqueRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Estoque>rowMapper=(rs,rowNum)->{
		Estoque estoque = new Estoque();
		estoque.setId(rs.getInt("eid"));
		estoque.setLote(rs.getString("lote"));
		estoque.setQuantidade(rs.getInt("quantidade"));
		Produto produto = new Produto();
		produto.setId(rs.getInt("pid"));
		produto.setCodigoBarras(rs.getString("cdbarras"));
		produto.setDescricao(rs.getString("descricao"));
		produto.setValidade(rs.getDate("validade").toLocalDate());
		produto.setValor(rs.getDouble("valor"));
		estoque.setProduto(produto);
		return estoque;
	};

	
	public List<Estoque>list(){
		String sql = "select e.id as eid, lote ,quantidade,id_produto, p.id as pid,p.codigo_barras as cdbarras, p.descricao as descricao, p.validade as validade, p.valor as valor from estoques e inner join produtos p on p.id = e.id_produto";
		return jdbcTemplate.query(sql, rowMapper);
	}
	
}
