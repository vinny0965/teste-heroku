package br.com.barbeariaFuraoJDBC.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.barbeariaFuraoJDBC.datasource.model.Cliente;
import br.com.barbeariaFuraoJDBC.datasource.model.Endereco;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;

@Repository
public class ClienteRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Cliente>rowMapper = (rs,rowNum)->{
		Cliente cliente = new Cliente();
		cliente.setId(rs.getInt("cid"));
		cliente.setCpf(rs.getString("cpf"));
		cliente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
		cliente.setEmail(rs.getString("email"));
		cliente.setNome(rs.getString("nome"));
		cliente.setSexo(rs.getString("sexo"));
		cliente.setTelefone(rs.getString("telefone"));
		
		Endereco endereco = new Endereco();
		endereco.setId(rs.getInt("eid"));
		endereco.setBairro(rs.getString("bairro"));
		endereco.setCep(rs.getString("cep"));
		endereco.setLogradouro(rs.getString("logradouro"));
		endereco.setNumero(rs.getInt("numero"));
		cliente.setEndereco(endereco);
		return cliente;
		
	};
	
	public List<Cliente>list(){
		String sql = "select c.id as cid, cpf, data_nascimento, email, nome, sexo, "
				+ "telefone, id_endereco, e.id as eid, e.bairro ,e.cep ,"
				+ "e.logradouro,e.numero from clientes c  inner join enderecos"
				+ " e on e.id = c.id_endereco";
		return jdbcTemplate.query(sql, rowMapper);
	}
	
	public List<Cliente>listByEndereco(int id){
		String sql = "select c.id as cid, cpf, data_nascimento, email, nome, sexo, "
				+ "telefone, id_endereco, e.id as eid, e.bairro ,e.cep ,"
				+ "e.logradouro,e.numero from clientes c  inner join enderecos"
				+ " e on e.id = c.id_endereco WHERE e.id=?";
		return jdbcTemplate.query(sql, rowMapper,id);
		
	}
	
	
	
	@SuppressWarnings("deprecation")
	public Cliente getById(int id) throws NotFoundException {
		String sql = "select c.id as cid, cpf, data_nascimento, email, nome, sexo,"
				+ " telefone, id_endereco, e.id as eid, e.bairro ,e.cep ,"
				+ "e.logradouro,e.numero from clientes c  inner join enderecos e"
				+ " on e.id = c.id_endereco WHERE c.id=?";
		Cliente cliente = null;
		
		try {
			cliente = jdbcTemplate.queryForObject(sql, new Object[] {id},rowMapper);
			
		} catch (DataAccessException e) {
			// TODO: handle exception
			throw new NotFoundException("Não foi possível encontrar o cliente pelo id: "+id);
		}
		return cliente;
	}
	
	@SuppressWarnings("deprecation")
	public Cliente getByCpf(String cpf) throws NotFoundException {
		String sql = "select c.id as cid, cpf, data_nascimento, email, nome, sexo,"
				+ " telefone, id_endereco, e.id as eid, e.bairro ,e.cep ,"
				+ "e.logradouro,e.numero from clientes c  inner join enderecos e"
				+ " on e.id = c.id_endereco WHERE cpf=?";
		Cliente cliente = null;
		
		try {
			cliente = jdbcTemplate.queryForObject(sql, new Object[] {cpf},rowMapper);
			
		} catch (DataAccessException e) {
			throw new NotFoundException("Não foi possível encontrar o cliente pelo cpf: "+cpf);

			// TODO: handle exception
		}
		return cliente;
	}
	
	public int UpdateById(Cliente cliente,int id) {
		String sql = "UPDATE clientes SET cpf =?, data_nascimento=?, email=?,"
				+ "nome=?,sexo=?, telefone=?, id_endereco=?  WHERE id=?";
		return jdbcTemplate.update(sql,cliente.getCpf(),cliente.getDataNascimento(),
				cliente.getEmail(),cliente.getNome(),cliente.getSexo(),cliente.getTelefone(),
				cliente.getEndereco().getId(),id);
	}
	
	public int deleteById(int id) {
		String sql = ("DELETE from clientes WHERE id=?");
		return jdbcTemplate.update(sql,id);
	}
	
	public int Create(Cliente cliente) {
		String sql = "INSERT INTO clientes (cpf, data_nascimento, email, "
				+ "nome,sexo,telefone,id_endereco) VALUES(?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,cliente.getCpf(),cliente.getDataNascimento(),
				cliente.getEmail(),cliente.getNome(),cliente.getSexo(),cliente.getTelefone(),
				cliente.getEndereco().getId());
	}

	
}
