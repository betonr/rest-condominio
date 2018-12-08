package br.gov.sp.fatec.web.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.sp.fatec.model.Agenda;
import br.gov.sp.fatec.service.AgendaService;
import br.gov.sp.fatec.view.View;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "/aged")
@CrossOrigin
public class AgendaaController {
	
	@Autowired
	private AgendaService anotacaoService;

	public void setAnotacaoService(AgendaService anotacaoService) {
		this.anotacaoService = anotacaoService;
	}
	
	@RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
	@JsonView(View.Agenda.class)
	public ResponseEntity<Agenda> pesquisarPorId(@PathVariable("id") Long id) {
		return new ResponseEntity<Agenda>(anotacaoService.buscarPorId(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@JsonView(View.Agenda.class)
	public ResponseEntity<Collection<Agenda>> getAll() {
		return new ResponseEntity<Collection<Agenda>>(anotacaoService.todos(), HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@JsonView(View.Agenda.class)
	public ResponseEntity<Agenda> salvar(@RequestBody Agenda anotacao, UriComponentsBuilder uriComponentsBuilder) {
		anotacao = anotacaoService.salvar(anotacao);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(uriComponentsBuilder.path("/getById/" + anotacao.getId()).build().toUri());
		return new ResponseEntity<Agenda>(anotacao, responseHeaders, HttpStatus.CREATED);
	}
	
}
