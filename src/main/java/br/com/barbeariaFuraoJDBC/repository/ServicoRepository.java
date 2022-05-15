package br.com.barbeariaFuraoJDBC.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.barbeariaFuraoJDBC.datasource.model.Administrador;
import br.com.barbeariaFuraoJDBC.datasource.model.Endereco;
import br.com.barbeariaFuraoJDBC.datasource.model.Servico;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;

@Repository
public class ServicoRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Servico>rowMapper = (rs,rowNum)->{
		Servico servico = new Servico();
		servico.setId(rs.getInt("sid"));
		servico.setTipoServico(rs.getString("tipo_servico"));
		servico.setValor(rs.getDouble("valor"));
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
		servico.setAdministrador(administrador);
		return servico;
	};
	
	public List<Servico>list(){
		String sql = "select s.id as sid, tipo_servico , valor ,id_administrador, a.id as aid, a.cpf ,"
				+ " a.data_nascimento , a.email , a.login , a.nome , a.senha , a.sexo , a.telefone , a.id_endereco,"
				+ " e.id as eid,e.bairro,e.cep,e.logradouro ,e.numero from servicos s inner join administradores"
				+ " a on a.id = s.id_administrador inner join enderecos e on e.id = a.id_endereco";
		return jdbcTemplate.query(sql, rowMapper);
	}
	
	@SuppressWarnings("deprecation")
	public Servico getById(int id) throws NotFoundException {
		String sql = "select s.id as sid, tipo_servico , valor ,id_administrador, a.id as aid, a.cpf ,"
				+ " a.data_nascimento , a.email , a.login , a.nome , a.senha , a.sexo , a.telefone , a.id_endereco,"
				+ " e.id as eid,e.bairro,e.cep,e.logradouro ,e.numero from servicos s inner join administradores"
				+ " a on a.id = s.id_administrador inner join enderecos e on e.id = a.id_endereco WHERE s.id=?";
		Servico servico = null;
		try {
			servico = jdbcTemplate.queryForObject(sql, new Object[] {id},rowMapper);
		} catch (DataAccessException e) {
			// TODO: handle exception
			throw new NotFoundException("não foi possível encontrar o serviço pelo id: "+id);
		}
		return servico;
	}
	
	public List<Servico>listByAdministrador(int idAdm){
		String sql = "select s.id as sid, tipo_servico , valor ,id_administrador, a.id as aid, a.cpf ,"
				+ " a.data_nascimento , a.email , a.login , a.nome , a.senha , a.sexo , a.telefone , a.id_endereco,"
				+ " e.id as eid,e.bairro,e.cep,e.logradouro ,e.numero from servicos s inner join administradores"
				+ " a on a.id = s.id_administrador inner join enderecos e on e.id = a.id_endereco WHERE a.id=?";
		return jdbcTemplate.query(sql, rowMapper,idAdm);
	}
	
	@SuppressWarnings("deprecation")
	public Servico getByTipo(String tipo) throws NotFoundException {
		String sql = "select s.id as sid, tipo_servico , valor ,id_administrador, a.id as aid, a.cpf ,"
				+ " a.data_nascimento , a.email , a.login , a.nome , a.senha , a.sexo , a.telefone , a.id_endereco,"
				+ " e.id as eid,e.bairro,e.cep,e.logradouro ,e.numero from servicos s inner join administradores"
				+ " a on a.id = s.id_administrador inner join enderecos e on e.id = a.id_endereco WHERE tipo_servico=?";
		Servico servico = null;
		try {
			servico = jdbcTemplate.queryForObject(sql, new Object[] {tipo},rowMapper);
		} catch (DataAccessException e) {
			// TODO: handle exception
			throw new NotFoundException("não foi possível encontrar o serviço pelo nome: "+tipo);

		}
		return servico;
	}
	
	
	public int create(Servico servico) {
		String sql = "INSERT INTO servicos (tipo_servico, valor, id_administrador)"
				+ " VALUES(?,?,?)";
		return jdbcTemplate.update(sql,servico.getTipoServico(),servico.getValor(),servico.getAdministrador().getId());
	}
	
	public int updateById(Servico servico,int id) {
		String sql = "UPDATE servicos SET tipo_servico =?, valor=?, id_administrador=? WHERE id=?";
		return jdbcTemplate.update(sql,servico.getTipoServico(),servico.getValor(),servico.getAdministrador().getId(),id);
	}
	
	public int deleteById(int id) {
		String sql = ("DELETE from servicos WHERE id=?");
		return jdbcTemplate.update(sql,id);
	}
}
