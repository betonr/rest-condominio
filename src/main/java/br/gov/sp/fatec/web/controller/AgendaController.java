package br.gov.sp.fatec.web.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.model.Agenda;
import br.gov.sp.fatec.service.AgendaService;
import br.gov.sp.fatec.view.View;

@RestController
@RequestMapping(value = "/agenda")
@CrossOrigin
public class AgendaController {

	@Autowired
	private AgendaService agendaService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@JsonView(View.Agenda.class)
	public ResponseEntity<Agenda> save(@RequestBody Agenda agenda, UriComponentsBuilder uriComponentsBuilder){
		agenda = agendaService.salvar(agenda);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(uriComponentsBuilder.path("/getById?id=" + agenda.getId()).build().toUri());
		return new ResponseEntity<Agenda>(agenda, responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@JsonView(View.Agenda.class)
	public ResponseEntity<Collection<Agenda>> getAll() {
		return new ResponseEntity<Collection<Agenda>>(agendaService.todos(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@JsonView(View.Agenda.class)
	public ResponseEntity<String> delete(@RequestParam(value="id") Long id, UriComponentsBuilder uriComponentsBuilder) {
		boolean success = agendaService.delete(id);
		if(success) {
			return new ResponseEntity<String>("Horário: "+id+" deletado com sucesso!", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Erro ao deletar o usuário: "+id, HttpStatus.BAD_REQUEST);
	}

}