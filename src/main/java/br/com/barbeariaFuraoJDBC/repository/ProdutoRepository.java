package br.com.barbeariaFuraoJDBC.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.barbeariaFuraoJDBC.datasource.model.Produto;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;

@Repository
public class ProdutoRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	private RowMapper<Produto>rowMapper = (rs, rowNum)->{
		Produto produto = new Produto();
		produto.setId(rs.getInt("pid"));
		produto.setCodigoBarras(rs.getString("cdbarras"));
		produto.setDescricao(rs.getString("descricao"));
		produto.setValidade(rs.getDate("validade").toLocalDate());
		produto.setValor(rs.getDouble("valor"));
		return produto;
	};
	
	public List<Produto>list(){
		String sql = "select p.id as pid, codigo_barras as cdbarras, descricao as descricao, validade as validade, valor from produtos p";
		return jdbcTemplate.query(sql, rowMapper);
	}
	
	@SuppressWarnings("deprecation")
	public Produto buscarPorId(int id) throws NotFoundException {
		String sql = "select p.id as pid, codigo_barras as cdbarras, descricao as descricao, validade as validade, valor from produtos p WHERE p.id=?";
		Produto produto = null;

		try {
			produto = jdbcTemplate.queryForObject(sql, new Object[] {id},rowMapper);
		} catch (Exception e) {
			// TODO: handle exception
			throw new NotFoundException("não foi possível encontrar o produto pelo id: "+id);
		}
		return produto;
	}
	
	
	

}
