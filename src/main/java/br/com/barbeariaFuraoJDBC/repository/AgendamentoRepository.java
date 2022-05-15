package br.com.barbeariaFuraoJDBC.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.barbeariaFuraoJDBC.datasource.model.Administrador;
import br.com.barbeariaFuraoJDBC.datasource.model.Agendamento;
import br.com.barbeariaFuraoJDBC.datasource.model.Cliente;
import br.com.barbeariaFuraoJDBC.datasource.model.Endereco;
import br.com.barbeariaFuraoJDBC.datasource.model.Servico;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;

@Repository
public class AgendamentoRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Agendamento>rowMapper= (rs,rowNum)->{
		Agendamento agendamento = new Agendamento();
		agendamento.setId(rs.getInt("aid"));
		agendamento.setDataAgendamento(rs.getDate("data_agendamento").toLocalDate());
		agendamento.setFuncionario(rs.getString("funcionario"));
		agendamento.setHorario(rs.getTime("horario"));
		Cliente cliente = new Cliente();
		cliente.setId(rs.getInt("cid"));
		cliente.setCpf(rs.getString("ccpf"));
		cliente.setDataNascimento(rs.getDate("cdatans").toLocalDate());
		cliente.setEmail(rs.getString("cemail"));
		cliente.setNome(rs.getString("cnome"));
		cliente.setSexo(rs.getString("csexo"));
		cliente.setTelefone(rs.getString("ctelefone"));
		// endereco
		Endereco endereco = new Endereco();
		endereco.setId(rs.getInt("eid"));
		endereco.setBairro(rs.getString("ebairro"));
		endereco.setCep(rs.getString("ecep"));
		endereco.setLogradouro(rs.getString("elog"));
		endereco.setNumero(rs.getInt("enumero"));
		cliente.setEndereco(endereco);
		agendamento.setCliente(cliente);
		// servico
		Servico servico = new Servico();
		servico.setId(rs.getInt("sid"));
		servico.setTipoServico(rs.getString("tipo_servico"));
		servico.setValor(rs.getDouble("valor"));
		// administrador
		Administrador administrador = new Administrador();
		administrador.setId(rs.getInt("adid"));
		administrador.setCpf(rs.getString("adcpf"));
		administrador.setDataNascimento(rs.getDate("adnasc").toLocalDate());
		administrador.setEmail(rs.getString("ademail"));
		administrador.setLogin(rs.getString("adlogin"));
		administrador.setNome(rs.getString("adnome"));
		administrador.setSenha(rs.getString("adsenha"));
		administrador.setSexo(rs.getString("adsexo"));
		administrador.setTelefone(rs.getString("adtelefone"));
		Endereco endereco2 = new Endereco();
		endereco2.setId(rs.getInt("eadid"));
		endereco2.setBairro(rs.getString("eadbairro"));
		endereco2.setCep(rs.getString("edadcep"));
		endereco2.setLogradouro(rs.getString("edadlog"));
		endereco2.setNumero(rs.getInt("edadnumero"));
		administrador.setEndereco(endereco2);
		servico.setAdministrador(administrador);
		agendamento.setServico(servico);
		return agendamento;
	};
	
	public List<Agendamento>getAll(){
		String sql = "select a.id as aid, data_agendamento ,funcionario , horario , id_cliente , id_servico, c.id as cid, c.cpf as ccpf , c.data_nascimento as cdatans , c.email as cemail ,c.nome as cnome ,c.sexo csexo , c.telefone as ctelefone, c.id_endereco as cendereco,\r\n"
				+ "e.id as eid, e.bairro as ebairro, e.cep as ecep ,e.logradouro as elog ,e.numero as enumero , s.id as sid, s.tipo_servico ,s.valor ,s.id_administrador, ad.id as adid, ad.cpf as adcpf , ad.data_nascimento as adnasc , ad.email as ademail , ad.login as adlogin,\r\n"
				+ "ad.nome as adnome , ad.senha as adsenha , ad.sexo  as adsexo, ad.telefone as adtelefone, ad.id_endereco as adendereco, ead.id as eadid, ead.bairro as eadbairro, ead.cep as edadcep, ead.logradouro as edadlog , ead.numero as edadnumero\r\n"
				+ "from agendamentos a inner join clientes c on c.id = a.id_cliente\r\n"
				+ "inner join enderecos e on e.id = c.id_endereco \r\n"
				+ "inner join servicos s on s.id = a.id_servico \r\n"
				+ "inner join administradores ad on ad.id = s.id_administrador\r\n"
				+ "inner join enderecos ead on ead.id  = ad.id_endereco";
		return jdbcTemplate.query(sql, rowMapper);
	}
	
	public List<Agendamento>listByCliente(int id){
		String sql = "select a.id as aid, data_agendamento ,funcionario , horario , id_cliente , id_servico, c.id as cid, c.cpf as ccpf , c.data_nascimento as cdatans , c.email as cemail ,c.nome as cnome ,c.sexo csexo , c.telefone as ctelefone, c.id_endereco as cendereco,\r\n"
				+ "e.id as eid, e.bairro as ebairro, e.cep as ecep ,e.logradouro as elog ,e.numero as enumero , s.id as sid, s.tipo_servico ,s.valor ,s.id_administrador, ad.id as adid, ad.cpf as adcpf , ad.data_nascimento as adnasc , ad.email as ademail , ad.login as adlogin,\r\n"
				+ "ad.nome as adnome , ad.senha as adsenha , ad.sexo  as adsexo, ad.telefone as adtelefone, ad.id_endereco as adendereco, ead.id as eadid, ead.bairro as eadbairro, ead.cep as edadcep, ead.logradouro as edadlog , ead.numero as edadnumero\r\n"
				+ "from agendamentos a inner join clientes c on c.id = a.id_cliente\r\n"
				+ "inner join enderecos e on e.id = c.id_endereco \r\n"
				+ "inner join servicos s on s.id = a.id_servico \r\n"
				+ "inner join administradores ad on ad.id = s.id_administrador\r\n"
				+ "inner join enderecos ead on ead.id  = ad.id_endereco WHERE c.id=?";		
		return jdbcTemplate.query(sql,rowMapper,id);
	}
	
	@SuppressWarnings("deprecation")
	public Agendamento findById(int id) throws NotFoundException {
		String sql = "select a.id as aid, data_agendamento ,funcionario , horario , id_cliente , id_servico, c.id as cid, c.cpf as ccpf , c.data_nascimento as cdatans , c.email as cemail ,c.nome as cnome ,c.sexo csexo , c.telefone as ctelefone, c.id_endereco as cendereco,\r\n"
				+ "e.id as eid, e.bairro as ebairro, e.cep as ecep ,e.logradouro as elog ,e.numero as enumero , s.id as sid, s.tipo_servico ,s.valor ,s.id_administrador, ad.id as adid, ad.cpf as adcpf , ad.data_nascimento as adnasc , ad.email as ademail , ad.login as adlogin,\r\n"
				+ "ad.nome as adnome , ad.senha as adsenha , ad.sexo  as adsexo, ad.telefone as adtelefone, ad.id_endereco as adendereco, ead.id as eadid, ead.bairro as eadbairro, ead.cep as edadcep, ead.logradouro as edadlog , ead.numero as edadnumero\r\n"
				+ "from agendamentos a inner join clientes c on c.id = a.id_cliente\r\n"
				+ "inner join enderecos e on e.id = c.id_endereco \r\n"
				+ "inner join servicos s on s.id = a.id_servico \r\n"
				+ "inner join administradores ad on ad.id = s.id_administrador\r\n"
				+ "inner join enderecos ead on ead.id  = ad.id_endereco WHERE a.id=?";
		Agendamento agendamento = null;
		try {
			agendamento = jdbcTemplate.queryForObject(sql, new Object[] {id},rowMapper);
		} catch (Exception e) {
			// TODO: handle exception
			throw new NotFoundException("não foi possível encontrar o agendamento pelo id: "+id);
		}
		return agendamento;
	}
	
	
	public int create(Agendamento agendamento) {
		String sql = "insert into agendamentos (data_agendamento,funcionario,horario, id_cliente, id_servico) values (?,?,?,?,?)";
		return jdbcTemplate.update(sql,agendamento.getDataAgendamento(),agendamento.getFuncionario(),
				agendamento.getHorario(),agendamento.getCliente().getId(),agendamento.getServico().getId());
	}
		
	
}
