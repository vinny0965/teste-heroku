package br.com.barbeariaFuraoJDBC.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.barbeariaFuraoJDBC.datasource.model.Administrador;
import br.com.barbeariaFuraoJDBC.datasource.model.Agendamento;
import br.com.barbeariaFuraoJDBC.datasource.model.Cliente;
import br.com.barbeariaFuraoJDBC.datasource.model.CupomDesconto;
import br.com.barbeariaFuraoJDBC.datasource.model.Endereco;
import br.com.barbeariaFuraoJDBC.datasource.model.FluxoCaixa;
import br.com.barbeariaFuraoJDBC.datasource.model.Servico;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;

@Repository
public class FluxoCaixaRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	private RowMapper<FluxoCaixa>rowMapper = (rs,numRow)->{
		FluxoCaixa fluxoCaixa = new FluxoCaixa();
		fluxoCaixa.setId(rs.getInt("fcid"));
		fluxoCaixa.setFormaPagamento(rs.getString("fcformapag"));
		fluxoCaixa.setTotalPagamento(rs.getDouble("fctotalpag"));
		//Agendamento
		Agendamento agendamento = new Agendamento();
		agendamento.setId(rs.getInt("aid"));
		agendamento.setDataAgendamento(rs.getDate("adatagendamento").toLocalDate());
		agendamento.setFuncionario(rs.getString("afuncionario"));
		agendamento.setHorario(rs.getTime("ahorario"));
		//Cliente
		Cliente cliente = new Cliente();
		cliente.setId(rs.getInt("cliid"));
		cliente.setCpf(rs.getString("clicpf"));
		cliente.setDataNascimento(rs.getDate("clidtnasc").toLocalDate());
		cliente.setEmail(rs.getString("cliemail"));
		cliente.setNome(rs.getString("clinome"));
		cliente.setSexo(rs.getString("clisexo"));
		cliente.setTelefone(rs.getString("clitel"));
		//endereco cliente
		Endereco enderecoCliente = new Endereco();
		enderecoCliente.setId(rs.getInt("ecliid"));
		enderecoCliente.setBairro(rs.getString("eclibairro"));
		enderecoCliente.setCep(rs.getString("eclicep"));
		enderecoCliente.setLogradouro(rs.getString("eclilog"));
		enderecoCliente.setNumero(rs.getInt("eclinum"));
		cliente.setEndereco(enderecoCliente);
		agendamento.setCliente(cliente);
		//servico
		Servico servico = new Servico();
		servico.setId(rs.getInt("sid"));
		servico.setTipoServico(rs.getString("stipo"));
		servico.setValor(rs.getDouble("svalor"));
		//adm servico
		Administrador admserv = new Administrador();
		admserv.setId(rs.getInt("admid"));
		admserv.setCpf(rs.getString("admcpf"));
		admserv.setDataNascimento(rs.getDate("admdtnasc").toLocalDate());
		admserv.setEmail(rs.getString("admemail"));
		admserv.setLogin(rs.getString("admlogin"));
		admserv.setNome(rs.getString("admnome"));
		admserv.setSenha(rs.getString("admsenha"));
		admserv.setSexo(rs.getString("admsexo"));
		admserv.setTelefone(rs.getString("admtelefone"));
		//Endereco Administrador
		Endereco endAdm = new Endereco();
		endAdm.setId(rs.getInt("eadmid"));
		endAdm.setBairro(rs.getString("eadmbairro"));
		endAdm.setCep(rs.getString("eadmcep"));
		endAdm.setLogradouro(rs.getString("eadmlog"));
		endAdm.setNumero(rs.getInt("eadmnumero"));
		admserv.setEndereco(endAdm);
		servico.setAdministrador(admserv);
		agendamento.setServico(servico);
		fluxoCaixa.setAgendamento(agendamento);
		//cupom desconto
		CupomDesconto cupomDesconto = new CupomDesconto();
		cupomDesconto.setId(rs.getInt("cdid"));
		cupomDesconto.setCodigo(rs.getString("cdcod"));
		cupomDesconto.setValor(rs.getDouble("cdvalor"));
		//adm cupom
		Administrador admCupom = new Administrador();
		admCupom.setId(rs.getInt("acupomid"));
		admCupom.setCpf(rs.getString("acupomcpf"));
		admCupom.setDataNascimento(rs.getDate("acupomdtnasc").toLocalDate());
		admCupom.setEmail(rs.getString("acupomemail"));
		admCupom.setLogin(rs.getString("acupomlogin"));
		admCupom.setNome(rs.getString("acupomnome"));
		admCupom.setSenha(rs.getString("acupomsenha"));
		admCupom.setSexo(rs.getString("acupomsexo"));
		admCupom.setTelefone(rs.getString("acupomtelefon"));
		//Endereco adm cupom
		Endereco eadmCupom = new Endereco();
		eadmCupom.setId(rs.getInt("eadmcupomid"));
		eadmCupom.setBairro(rs.getString("eadmcupombairro"));
		eadmCupom.setCep(rs.getString("eadmcupomcep"));
		eadmCupom.setLogradouro(rs.getString("eadmcupomlog"));
		eadmCupom.setNumero(rs.getInt("eadmcupomnumero"));
		admCupom.setEndereco(enderecoCliente);
		cupomDesconto.setAdministrador(admCupom);
		fluxoCaixa.setCupomDesconto(cupomDesconto);
		return fluxoCaixa;
	};
	
	public List<FluxoCaixa>list(){
		String sql = "select fc.id as fcid, fc.forma_pagamento as fcformapag, fc.total_pagamento as fctotalpag, fc.id_agendamento as fcagendamento, fc.id_cupom as fcidcupom,\r\n"
				+ "a.id as aid, a.data_agendamento as adatagendamento , a.funcionario as afuncionario, a.horario as ahorario, a.id_cliente as acliente, a.id_servico as aservico  ,\r\n"
				+ "cli.id as cliid, cli.cpf as clicpf, cli.data_nascimento as clidtnasc, cli.email as cliemail, cli.nome as clinome, cli.sexo as clisexo, cli.telefone as clitel, cli.id_endereco as cliend,\r\n"
				+ "ecli.id as ecliid, ecli.bairro as eclibairro, ecli.cep as eclicep, ecli.logradouro as eclilog, ecli.numero as eclinum,\r\n"
				+ "s.id as sid, s.tipo_servico as stipo, s.valor as svalor, s.id_administrador as sadm,\r\n"
				+ "adm.id as admid, adm.cpf as admcpf, adm.data_nascimento as admdtnasc, adm.email as admemail, adm.login as admlogin, adm.nome as admnome, adm.senha as admsenha, adm.sexo as admsexo, adm.telefone as admtelefone, adm.id_endereco as admend,\r\n"
				+ "eadm.id as eadmid, eadm.bairro as eadmbairro, eadm.cep as eadmcep, eadm.logradouro as eadmlog, eadm.numero as eadmnumero,\r\n"
				+ "cd.id as cdid, cd.codigo as cdcod, cd.valor as cdvalor, cd.id_administrador as cdadm,\r\n"
				+ "acupom.id as acupomid, acupom.cpf as acupomcpf, acupom.data_nascimento as acupomdtnasc, acupom.email as acupomemail, acupom.login as acupomlogin, acupom.nome as acupomnome, acupom.senha as acupomsenha, acupom.sexo as acupomsexo, acupom.telefone as acupomtelefon, acupom.id_endereco as acupomend,\r\n"
				+ "eadmcupom.id as eadmcupomid, eadmcupom.bairro as eadmcupombairro, eadmcupom.cep as eadmcupomcep, eadmcupom.logradouro as eadmcupomlog, eadmcupom.numero as eadmcupomnumero\r\n"
				+ "from fluxos_caixa fc\r\n"
				+ "inner join agendamentos a on a.id = fc.id_agendamento\r\n"
				+ "inner join clientes cli on cli.id  = a.id_cliente\r\n"
				+ "inner join enderecos ecli on ecli.id  = cli.id_endereco\r\n"
				+ "inner join servicos s on s.id  = a.id_servico\r\n"
				+ "inner join administradores adm on adm.id  = s.id_administrador\r\n"
				+ "inner join enderecos eadm on eadm.id = adm.id_endereco\r\n"
				+ "inner join cupons_desconto cd on cd.id = fc.id_cupom\r\n"
				+ "inner join administradores acupom on acupom.id  = cd.id_administrador\r\n"
				+ "inner join enderecos eadmcupom on eadmcupom.id  = acupom.id_endereco";
		return jdbcTemplate.query(sql, rowMapper);
	}
	
	@SuppressWarnings("deprecation")
	public FluxoCaixa buscarPorId(int id) throws NotFoundException {
		String sql = "select fc.id as fcid, fc.forma_pagamento as fcformapag, fc.total_pagamento as fctotalpag, fc.id_agendamento as fcagendamento, fc.id_cupom as fcidcupom,\r\n"
				+ "a.id as aid, a.data_agendamento as adatagendamento , a.funcionario as afuncionario, a.horario as ahorario, a.id_cliente as acliente, a.id_servico as aservico  ,\r\n"
				+ "cli.id as cliid, cli.cpf as clicpf, cli.data_nascimento as clidtnasc, cli.email as cliemail, cli.nome as clinome, cli.sexo as clisexo, cli.telefone as clitel, cli.id_endereco as cliend,\r\n"
				+ "ecli.id as ecliid, ecli.bairro as eclibairro, ecli.cep as eclicep, ecli.logradouro as eclilog, ecli.numero as eclinum,\r\n"
				+ "s.id as sid, s.tipo_servico as stipo, s.valor as svalor, s.id_administrador as sadm,\r\n"
				+ "adm.id as admid, adm.cpf as admcpf, adm.data_nascimento as admdtnasc, adm.email as admemail, adm.login as admlogin, adm.nome as admnome, adm.senha as admsenha, adm.sexo as admsexo, adm.telefone as admtelefone, adm.id_endereco as admend,\r\n"
				+ "eadm.id as eadmid, eadm.bairro as eadmbairro, eadm.cep as eadmcep, eadm.logradouro as eadmlog, eadm.numero as eadmnumero,\r\n"
				+ "cd.id as cdid, cd.codigo as cdcod, cd.valor as cdvalor, cd.id_administrador as cdadm,\r\n"
				+ "acupom.id as acupomid, acupom.cpf as acupomcpf, acupom.data_nascimento as acupomdtnasc, acupom.email as acupomemail, acupom.login as acupomlogin, acupom.nome as acupomnome, acupom.senha as acupomsenha, acupom.sexo as acupomsexo, acupom.telefone as acupomtelefon, acupom.id_endereco as acupomend,\r\n"
				+ "eadmcupom.id as eadmcupomid, eadmcupom.bairro as eadmcupombairro, eadmcupom.cep as eadmcupomcep, eadmcupom.logradouro as eadmcupomlog, eadmcupom.numero as eadmcupomnumero\r\n"
				+ "from fluxos_caixa fc\r\n"
				+ "inner join agendamentos a on a.id = fc.id_agendamento\r\n"
				+ "inner join clientes cli on cli.id  = a.id_cliente\r\n"
				+ "inner join enderecos ecli on ecli.id  = cli.id_endereco\r\n"
				+ "inner join servicos s on s.id  = a.id_servico\r\n"
				+ "inner join administradores adm on adm.id  = s.id_administrador\r\n"
				+ "inner join enderecos eadm on eadm.id = adm.id_endereco\r\n"
				+ "inner join cupons_desconto cd on cd.id = fc.id_cupom\r\n"
				+ "inner join administradores acupom on acupom.id  = cd.id_administrador\r\n"
				+ "inner join enderecos eadmcupom on eadmcupom.id  = acupom.id_endereco WHERE fc.id=?";
		FluxoCaixa fluxoCaixa = null;
		try {
			fluxoCaixa = jdbcTemplate.queryForObject(sql, new Object[] {id},rowMapper);
		} catch (Exception e) {
			// TODO: handle exception
			throw new NotFoundException("não foi possível encontrar o fluxo do caixa pelo id: "+id);
		}
		return fluxoCaixa;
	}
	
	public int create(FluxoCaixa fluxoCaixa) {
		String sql = "insert into fluxos_caixa (forma_pagamento,total_pagamento,id_agendamento, id_cupom) values (?,?,?,?)";
		return jdbcTemplate.update(sql,fluxoCaixa.getFormaPagamento(),fluxoCaixa.getTotalPagamento(),fluxoCaixa.getAgendamento().getId(),fluxoCaixa.getCupomDesconto().getId());
	}
	
	public int update(FluxoCaixa fluxoCaixa, int id) {
		String sql = "UPDATE fluxos_caixa SET forma_pagamento =?, total_pagamento=?, id_agendamento=?, id_cupom=? WHERE id=?";
		return jdbcTemplate.update(sql,fluxoCaixa.getFormaPagamento(),fluxoCaixa.getTotalPagamento(),fluxoCaixa.getAgendamento().getId(),fluxoCaixa.getCupomDesconto().getId(),id);
	}
	
	public int delete(int id) {
		String sql = ("DELETE from fluxos_caixa WHERE id=?");
		return jdbcTemplate.update(sql,id);
	}

}
