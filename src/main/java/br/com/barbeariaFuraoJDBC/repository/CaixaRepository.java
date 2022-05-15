//package br.com.barbeariaFuraoJDBC.repository;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import br.com.barbeariaFuraoJDBC.datasource.model.Caixa;
//import br.com.barbeariaFuraoJDBC.datasource.model.Endereco;
//import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
//
//@Repository
//public class CaixaRepository {
//	
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//
//	private RowMapper<Caixa>rowMapper = (rs,rowNum)->{
//		Caixa caixa = new Caixa();
//		caixa.setId(rs.getInt("cid"));
//		caixa.setCpf(rs.getString("cpf"));
//		caixa.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
//		caixa.setEmail(rs.getString("email"));
//		caixa.setLogin(rs.getString("login"));
//		caixa.setMatricula(rs.getString("matricula"));
//		caixa.setNome(rs.getString("nome"));
//		caixa.setSenha(rs.getString("senha"));
//		caixa.setSexo(rs.getString("sexo"));
//		caixa.setTelefone(rs.getString("telefone"));
//		Endereco endereco = new Endereco();
//		endereco.setId(rs.getInt("eid"));
//		endereco.setBairro(rs.getString("bairro"));
//		endereco.setCep(rs.getString("cep"));
//		endereco.setLogradouro(rs.getString("logradouro"));
//		endereco.setNumero(rs.getInt("numero"));
//		caixa.setEndereco(endereco);
//		return caixa;
//	};
//	
//	public List<Caixa>list(){
//		String sql = "select c.id as cid, cpf ,data_nascimento , email ,login ,matricula ,nome ,senha ,sexo ,telefone ,"
//				+ "id_endereco , e.id  as eid, e.bairro , e.cep , e.logradouro"
//				+ " , e.numero from  caixas c inner join enderecos"
//				+ " e on e.id  = c.id_endereco order by c.id asc";
//		return jdbcTemplate.query(sql, rowMapper);
//	}
//	
//	@SuppressWarnings("deprecation")
//	public Caixa getById(int id) throws NotFoundException{
//		String sql = "select c.id as cid, cpf ,data_nascimento , email ,login ,matricula ,nome ,senha ,sexo ,telefone ,"
//				+ "id_endereco , e.id  as eid, e.bairro , e.cep , e.logradouro"
//				+ " , e.numero from  caixas c inner join enderecos"
//				+ " e on e.id  = c.id_endereco WHERE c.id=?";
//		Caixa caixa = null;
//		try {
//			caixa = jdbcTemplate.queryForObject(sql, new Object[] {id},rowMapper);
//		} catch (DataAccessException e) {
//			// TODO: handle exception
//			throw new NotFoundException("não foi possível encontrar o caixa pelo id: "+id);
//		}
//		return caixa;
//	}
//	
//	public int updateById(Caixa caixa,int id) {
//		String sql = "UPDATE caixas SET cpf =?, data_nascimento=?, email=?,"
//				+ "login=?,matricula=?, nome=?,senha=?,sexo=?, telefone=?,"
//				+ " id_endereco=?  WHERE id=?";
//		return jdbcTemplate.update(sql,caixa.getCpf(),caixa.getDataNascimento(),
//				caixa.getEmail(),caixa.getLogin(),caixa.getMatricula(),caixa.getNome(),
//				caixa.getSenha(),caixa.getSexo(),caixa.getTelefone(),
//				caixa.getEndereco().getId(),id);
//	}
//	
//	public int create(Caixa caixa) {
//		String sql = "INSERT INTO caixas (cpf, data_nascimento, email,login, matricula, "
//				+ "nome,senha, sexo,telefone,id_endereco) VALUES(?,?,?,?,?,?,?,?,?,?)";
//		return jdbcTemplate.update(sql,caixa.getCpf(),caixa.getDataNascimento(),
//				caixa.getEmail(),caixa.getLogin(),caixa.getMatricula(),caixa.getNome(),
//				caixa.getSenha(),caixa.getSexo(),caixa.getTelefone(),
//				caixa.getEndereco().getId());
//	}
//	
//	public int deleteById(int id) {
//		String sql = ("DELETE from caixas WHERE id=?");
//		return jdbcTemplate.update(sql,id);
//	}
//}
