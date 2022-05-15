package br.com.barbeariaFuraoJDBC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.barbeariaFuraoJDBC.conversor.ServicoConversor;
import br.com.barbeariaFuraoJDBC.datasource.model.Servico;
import br.com.barbeariaFuraoJDBC.exception.NotFoundException;
import br.com.barbeariaFuraoJDBC.exception.ResourceExeption;
import br.com.barbeariaFuraoJDBC.repository.ServicoRepository;
import br.com.barbeariaFuraoJDBC.resource.model.ServicoResource;

@Service
public class CadastrarServicoServiceImpl {
	
	@Autowired
	private ServicoConversor conversor;
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	public ResponseEntity<ServicoResource> cadastrar(ServicoResource servicoResource,int idAdm) throws ResourceExeption, NotFoundException {
		if(idAdm>0) {
			Servico conversor2 = conversor.conversor(servicoResource, idAdm);
			if(servicoRepository.create(conversor2)==0) {
				throw new ResourceExeption("não foi possível salvar o serviço, dados resource inválidos, resource: "+servicoResource);
			}else {
				return new ResponseEntity<>(conversor.conversor(conversor2), HttpStatus.CREATED);
			}	
		}
		throw new NotFoundException("Requestparam (idAdministrador) requerido");
	}
	
//	public ResponseEntity<ServicoDTO> cadastrarServico(ServicoDTO servicoDto, int id) throws ResourceExeption, NotFoundException {
//		Administrador buscarAdministradorPorId = administradorServiceImpl.buscarAdministradorPorId(id);
//		servicoDto.setAdministrador(buscarAdministradorPorId);
//		Servico map = mapper.map(servicoDto, Servico.class);
//		if(ServicoDao.createServico(map,id)==false) {
//			throw new ResourceExeption("não foi possível salvar o serviço, dados resource inválidos, resource: "+servicoDto);
//		}else {
//			return new ResponseEntity<>(servicoDto, HttpStatus.CREATED);
//		}
//	}

}
