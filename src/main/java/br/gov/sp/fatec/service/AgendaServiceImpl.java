package br.gov.sp.fatec.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Agenda;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.repository.AgendaRepository;
import br.gov.sp.fatec.repository.UsuarioRepository;

@Service("agendaService")
@Transactional
public class AgendaServiceImpl implements AgendaService {

	@Autowired
	private AgendaRepository agendaRepo;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	public void setAnotacaoRepo(AgendaRepository agendaRepo) {
		this.agendaRepo = agendaRepo;
	}
	
	public void setUsuarioRepo(UsuarioRepository usuarioRepo) {
		this.usuarioRepo = usuarioRepo;
	}
	
	@Override
	@PreAuthorize("isAuthenticated()")
	public Agenda salvar(Agenda agenda) {
		if(agenda.getUsuario() != null) {
			Usuario usuario = agenda.getUsuario();
			if(usuario.getId() != null) {
				usuario = usuarioRepo.findById(usuario.getId()).get();
			}
			else {
				usuario = usuarioRepo.save(usuario);
			}
		}
		return agendaRepo.save(agenda);
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public boolean delete(Long idAgenda) {
		Optional<Agenda> agenda =  agendaRepo.findById(idAgenda);
		if(agenda.isPresent()) {
			agendaRepo.delete(agenda.get());
			return true;
		}
		return false;
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Agenda> todos() {
		List<Agenda> retorno = new ArrayList<Agenda>();
		for(Agenda agenda: agendaRepo.findAll()) {
			retorno.add(agenda);
		}
		return retorno;
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Agenda> buscarPorUsuario(String nome) {
		if(nome == null || nome.isEmpty()) {
			return todos();
		}
		return agendaRepo.findByUsuarioNome(nome);
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public Agenda buscarPorId(Long idAnotacao) {
		Optional<Agenda> anotacao = agendaRepo.findById(idAnotacao);
		if(anotacao.isPresent()) {
			return anotacao.get();
		}
		return null;
	}

}
