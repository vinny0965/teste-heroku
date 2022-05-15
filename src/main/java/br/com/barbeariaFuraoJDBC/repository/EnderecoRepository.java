package br.com.barbeariaFuraoJDBC.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.barbeariaFuraoJDBC.datasource.model.Endereco;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;

@Repository
public class EnderecoRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<Endereco> rowMapper = (rs,rowNum)->{
		Endereco endereco = new Endereco();
		endereco.setId(rs.getInt("id"));
		endereco.setLogradouro(rs.getString("logradouro"));
		endereco.setBairro(rs.getString("bairro"));
		endereco.setNumero(rs.getInt("numero"));
		endereco.setCep(rs.getString("cep"));
		return endereco;
	};
	
	public List<Endereco>list(){
		String sql = "select * from enderecos";
		return jdbcTemplate.query(sql, rowMapper);
	}
	
	@SuppressWarnings("deprecation")
	public Endereco getById(int id) throws NotFoundException{
		String sql = "select * from enderecos WHERE id= ?";
		Endereco endereco = null;
		try {
			endereco = jdbcTemplate.queryForObject(sql, new Object[] {id},rowMapper);
		}catch (DataAccessException e) {
			// TODO: handle exception
			throw new NotFoundException("Não foi possível encontrar o endereco pelo id: "+id);
		}
		return endereco;
	}
	public int create(Endereco endereco) {
		String sql = "insert into enderecos (bairro, cep, logradouro, numero) values(?,?,?,?)";
		return jdbcTemplate.update(sql,endereco.getBairro(),endereco.getCep(),endereco.getLogradouro(),endereco.getNumero());
	}
	
	public int deleteById(int id) {
		String sql = "DELETE from enderecos WHERE id=?";
		return jdbcTemplate.update(sql, id);
	}
	
	public int updateById(Endereco endereco,int id) {
		String sql = "UPDATE enderecos SET bairro =?, cep=?,"
				+ "logradouro=?, numero=? WHERE id=?";
		return jdbcTemplate.update(sql,endereco.getBairro(),
				endereco.getCep(),endereco.getLogradouro(),endereco.getNumero(),id);
	}

}
