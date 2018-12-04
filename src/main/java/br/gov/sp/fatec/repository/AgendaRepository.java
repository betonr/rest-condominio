package br.gov.sp.fatec.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Agenda;

public interface AgendaRepository extends CrudRepository<Agenda, Long> {
	
	public List<Agenda> findByUsuarioNome(String nome);

}
