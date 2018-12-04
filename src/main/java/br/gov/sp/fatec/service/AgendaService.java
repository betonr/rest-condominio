package br.gov.sp.fatec.service;

import java.util.List;

import br.gov.sp.fatec.model.Agenda;

public interface AgendaService {
	
	public Agenda salvar(Agenda autorizacao);
	
	public void excluir(Long idAnotacao);
	
	public List<Agenda> todos();
	
	public List<Agenda> buscarPorUsuario(String nome);
	
	public Agenda buscarPorId(Long idAnotacao);

}
