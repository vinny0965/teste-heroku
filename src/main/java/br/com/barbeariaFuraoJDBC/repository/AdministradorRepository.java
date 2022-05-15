package br.com.barbeariaFuraoJDBC.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.barbeariaFuraoJDBC.datasource.model.Administrador;
import br.com.barbeariaFuraoJDBC.datasource.model.Endereco;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;

@Repository
public class AdministradorRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Administrador>rowMapper = (rs,rowNum)->{
		Administrador administrador = new Administrador();
		administrador.setId(rs.getInt("aid"));
		administrador.setCpf(rs.getString("cpf"));
		administrador.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
		administrador.setEmail(rs.getString("email"));
		administrador.setLogin(rs.getString("login"));
		administrador.setNome(rs.getString("nome"));
		administrador.setSenha(rs.getString("senha"));
		administrador.setSexo(rs.getString("sexo"));
		administrador.setTelefone(rs.getString("telefone"));
		
		Endereco endereco = new Endereco();
		endereco.setId(rs.getInt("eid"));
		endereco.setBairro(rs.getString("bairro"));
		endereco.setCep(rs.getString("cep"));
		endereco.setLogradouro(rs.getString("logradouro"));
		endereco.setNumero(rs.getInt("numero"));
		administrador.setEndereco(endereco);
		return administrador;
	};
	
	public List<Administrador>list(){
		String sql = ("select a.id as aid,cpf ,data_nascimento ,email , login ,nome ,senha ,sexo ,telefone, id_endereco ,e.id as eid, e.bairro ,e.cep ,e.logradouro,e.numero"
				+ "  from administradores a inner join enderecos e on e.id = a.id_endereco");
		return jdbcTemplate.query(sql, rowMapper);
	}
	
	@SuppressWarnings("deprecation")
	public Administrador getById(int id) throws NotFoundException {
		String sql = ("select a.id as aid,cpf ,data_nascimento ,email , login ,nome ,senha ,sexo ,telefone, id_endereco ,e.id as eid, e.bairro ,e.cep ,e.logradouro,e.numero"
				+ "  from administradores a inner join enderecos e on e.id = a.id_endereco WHERE a.id=?");
		Administrador administrador = null;
		try {
			administrador = jdbcTemplate.queryForObject(sql, new Object[] {id},rowMapper);
		} catch (DataAccessException e) {
			// TODO: handle exception
			throw new NotFoundException("Não foi possível encontrar o administrador pelo id: "+id);
		}
		return administrador;
	}
	
	@SuppressWarnings("deprecation")
	public Administrador getByCpf(String cpf) throws NotFoundException {
		String sql = ("select a.id as aid,cpf ,data_nascimento ,email , login ,nome ,senha ,sexo ,telefone, id_endereco ,e.id as eid, e.bairro ,e.cep ,e.logradouro,e.numero"
				+ "  from administradores a inner join enderecos e on e.id = a.id_endereco WHERE cpf=?");
		Administrador administrador = null;
		try {
			administrador = jdbcTemplate.queryForObject(sql, new Object[] {cpf},rowMapper);
		} catch (DataAccessException e) {
			// TODO: handle exception
			throw new NotFoundException("Não foi possível encontrar o administrador pelo cpf: "+cpf);
		}
		return administrador;
	}
	
	public int create (Administrador administrador) {
		String sql = "INSERT INTO administradores (cpf, data_nascimento, email, "
				+ "login,nome,senha,sexo,telefone,id_endereco) VALUES(?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,administrador.getCpf(),administrador.getDataNascimento(),
				administrador.getEmail(),administrador.getLogin(),administrador.getNome(),
				administrador.getSenha(),administrador.getSexo(),administrador.getTelefone(),administrador.getEndereco().getId());		
	}
	
	public int UpdateById(Administrador administrador, int id) {
		String sql = "UPDATE administradores SET cpf =?, data_nascimento=?, email=?, login=?,"
				+ "nome=?,senha=?,sexo=?, telefone=?, id_endereco=?  WHERE id=?";
		
		return jdbcTemplate.update(sql, administrador.getCpf(),administrador.getDataNascimento(),
				administrador.getEmail(),administrador.getLogin(),administrador.getNome(),
				administrador.getSenha(),administrador.getSexo(),administrador.getTelefone(),
				administrador.getEndereco().getId(),id);
	}
	
	public int deleteById(int id) {
		String sql = ("DELETE from administradores WHERE id=?");
		return jdbcTemplate.update(sql,id);
		
	}
	
}
