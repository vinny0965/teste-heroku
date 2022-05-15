//package br.com.barbeariaFuraoJDBC.conversor;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import br.com.barbeariaFuraoJDBC.datasource.model.Caixa;
//import br.com.barbeariaFuraoJDBC.datasource.model.Endereco;
//import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
//import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
//import br.com.barbeariaFuraoJDBC.repository.EnderecoRepository;
//import br.com.barbeariaFuraoJDBC.resource.model.CaixaResource;
//
//@Component
//public class CaixaConversor {
//	
//	@Autowired
//	private EnderecoRepository enderecoRepository;
//	
//	
//	public Caixa conversor(CaixaResource caixaResource) throws ResourceExeption, NotFoundException {
//		Endereco buscarEnderecoPorId = enderecoRepository.getById(Integer.parseInt(caixaResource.getIdEndereco()));
//		try {
//			Caixa caixa = new Caixa();
//			caixa.setNome(caixaResource.getNome());
//			caixa.setCpf(caixaResource.getCpf());
//			caixa.setDataNascimento(LocalDate.parse(caixaResource.getDataNascimento()));
//			caixa.setEmail(caixaResource.getEmail());
//			caixa.setEndereco(buscarEnderecoPorId);
//			caixa.setLogin(caixaResource.getLogin());
//			caixa.setMatricula(caixaResource.getMatricula());
//			caixa.setSenha(caixaResource.getSenha());
//			caixa.setSexo(caixaResource.getSexo());
//			caixa.setTelefone(caixaResource.getTelefone());
//			return caixa;
//			
//		} catch (Exception e) {
//			throw new ResourceExeption("erro ao converter resource caixa para entidade, resource: "+caixaResource);
//		}
//	}
//	
//	public List<CaixaResource>convert(List<Caixa>caixas){
//		List<CaixaResource>caixaResources = new ArrayList<>();
//		for (Caixa caixaCurrent : caixas) {
//			CaixaResource caixaResource = new CaixaResource();
//			caixaResource.setId(caixaCurrent.getId());
//			caixaResource.setCpf(caixaCurrent.getCpf());
//			caixaResource.setDataNascimento(String.valueOf(caixaCurrent.getDataNascimento()));
//			caixaResource.setEmail(caixaCurrent.getEmail());
//			caixaResource.setLogin(caixaCurrent.getLogin());
//			caixaResource.setMatricula(caixaCurrent.getMatricula());
//			caixaResource.setNome(caixaCurrent.getNome());
//			caixaResource.setSenha(caixaCurrent.getSenha());
//			caixaResource.setSexo(caixaCurrent.getSexo());
//			caixaResource.setTelefone(caixaCurrent.getTelefone());
//			caixaResource.setIdEndereco(String.valueOf(caixaCurrent.getEndereco().getId()));
//			caixaResources.add(caixaResource);
//		}
//		return caixaResources;
//	}
//	
//
//	public CaixaResource conversorr(Caixa caixaResource) throws ResourceExeption, NotFoundException {
//		Endereco buscarEnderecoPorId = enderecoRepository.getById((caixaResource.getEndereco().getId()));
//		try {
//			CaixaResource caixa = new CaixaResource();
//			caixa.setNome(caixaResource.getNome());
//			caixa.setCpf(caixaResource.getCpf());
//			caixa.setDataNascimento(String.valueOf(caixaResource.getDataNascimento()));
//			caixa.setEmail(caixaResource.getEmail());
//			caixa.setIdEndereco(String.valueOf(buscarEnderecoPorId.getId()));
//			caixa.setLogin(caixaResource.getLogin());
//			caixa.setMatricula(caixaResource.getMatricula());
//			caixa.setSenha(caixaResource.getSenha());
//			caixa.setSexo(caixaResource.getSexo());
//			caixa.setTelefone(caixaResource.getTelefone());
//			return caixa;
//			
//		} catch (Exception e) {
//			throw new ResourceExeption("erro ao converter resource caixa para entidade, resource: "+caixaResource);
//		}
//	}
//}
