package br.com.barbeariaFuraoJDBC.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.barbeariaFuraoJDBC.datasource.model.Administrador;
import br.com.barbeariaFuraoJDBC.datasource.model.CupomDesconto;
import br.com.barbeariaFuraoJDBC.datasource.model.Endereco;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;

@Repository
public class CupomRepository {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<CupomDesconto>rowMapper = (rs,rowNum)->{
		CupomDesconto cupomDesconto = new CupomDesconto();
		cupomDesconto.setId(rs.getInt("cdid"));
		cupomDesconto.setCodigo(rs.getString("codigo"));
		cupomDesconto.setValor(rs.getDouble("valor"));
		Administrador administrador = new Administrador();
		administrador.setId(rs.getInt("aid"));
		administrador.setCpf(rs.getString("cpf"));
		administrador.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
		administrador.setEmail(rs.getString("email"));
		Endereco endereco = new Endereco();
		endereco.setId(rs.getInt("eid"));
		endereco.setBairro(rs.getString("bairro"));
		endereco.setCep(rs.getString("cep"));
		endereco.setLogradouro(rs.getString("logradouro"));
		endereco.setNumero(rs.getInt("numero"));
		administrador.setEndereco(endereco);
		administrador.setLogin(rs.getString("login"));
		administrador.setNome(rs.getString("nome"));
		administrador.setSenha(rs.getString("senha"));
		administrador.setSexo(rs.getString("sexo"));
		administrador.setTelefone(rs.getString("telefone"));
		cupomDesconto.setAdministrador(administrador);
		return cupomDesconto;
	};
	
	public List<CupomDesconto>list(){
		String sql  = "select cd.id as cdid, codigo , valor , id_administrador , a.id as aid, a.cpf , a.data_nascimento ,"
				+ " a.email ,a.login ,a.nome, a.senha ,a.sexo , a.telefone ,a.id_endereco, e.id as eid, e.bairro,e.cep ,"
				+ "e.logradouro ,e.numero from cupons_desconto cd inner join administradores a on a.id = cd.id_administrador"
				+ " inner join enderecos e on e.id  = a.id_endereco";
		return jdbcTemplate.query(sql, rowMapper);
	}
	
	
	public List<CupomDesconto>listByAdm(int idAdm){
		String sql  = "select cd.id as cdid, codigo , valor , id_administrador , a.id as aid, a.cpf , a.data_nascimento ,"
				+ " a.email ,a.login ,a.nome, a.senha ,a.sexo , a.telefone ,a.id_endereco, e.id as eid, e.bairro,e.cep ,"
				+ "e.logradouro ,e.numero from cupons_desconto cd inner join administradores a on a.id = cd.id_administrador"
				+ " inner join enderecos e on e.id  = a.id_endereco WHERE a.id=?";
		return jdbcTemplate.query(sql, rowMapper,idAdm);
	}
	@SuppressWarnings("deprecation")
	public CupomDesconto getById(int id) throws NotFoundException{
		String sql  = "select cd.id as cdid, codigo , valor , id_administrador , a.id as aid, a.cpf , a.data_nascimento ,"
				+ " a.email ,a.login ,a.nome, a.senha ,a.sexo , a.telefone ,a.id_endereco, e.id as eid, e.bairro,e.cep ,"
				+ "e.logradouro ,e.numero from cupons_desconto cd inner join administradores a on a.id = cd.id_administrador"
				+ " inner join enderecos e on e.id  = a.id_endereco WHERE cd.id=?";
		CupomDesconto cupom = null;
		try {
			cupom= jdbcTemplate.queryForObject(sql, new Object[] {id}, rowMapper);
		} catch (DataAccessException e) {
			// TODO: handle exception
			throw new NotFoundException("não foi possível encontrar o cupom pelo id: "+id);
		}
		
		return cupom;
	}
	
	public int create(CupomDesconto cupomDesconto) {
		String sql = "insert into cupons_desconto (codigo,valor,id_administrador) values (?,?,?)";
		return jdbcTemplate.update(sql,cupomDesconto.getCodigo(),cupomDesconto.getValor(),cupomDesconto.getAdministrador().getId());
		
	}
	
	public int updateById(CupomDesconto cupomDesconto, int id) {
		String sql = "UPDATE cupons_desconto SET codigo=?, valor=?,id_administrador=? WHERE id=?";
		return jdbcTemplate.update(sql,cupomDesconto.getCodigo(),cupomDesconto.getValor(),cupomDesconto.getAdministrador().getId(),id);

	}
	
	public int deleteById(int id) {
		String sql = "DELETE from cupons_desconto WHERE id=?";
		return jdbcTemplate.update(sql,id);
	}
}
