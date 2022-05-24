package com.residencia.academia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.academia.entity.Atividade;
import com.residencia.academia.exception.NoSuchElementFoundException;
import com.residencia.academia.service.AtividadeService;

@RestController
@RequestMapping("/atividade")
public class AtividadeController {
	@Autowired
	private AtividadeService atividadeService;

	@GetMapping
	public ResponseEntity<List<Atividade/*DTO*/>> findAllAtividade() {
		return new ResponseEntity<>(atividadeService.findAllAtividade(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Atividade/*DTO*/> findAtividadeById(@PathVariable Integer id) {
		if (atividadeService.findAtividadeById(id) == null) {
			throw new NoSuchElementFoundException("Não foi possível encontrar a atividade de id = " + id + ".");
		}

		return new ResponseEntity<>(atividadeService.findAtividadeById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Atividade/*DTO*/> saveAtividade(@RequestBody Atividade/*DTO*/ atividade/*DTO*/) {
		return new ResponseEntity<>(atividadeService.saveAtividade(atividade/*DTO*/), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Atividade/*DTO*/> updateAtividade(@RequestBody Atividade/*DTO*/ atividade/*DTO*/) {
		if (atividadeService.findAtividadeById(atividade/*DTO*/.getIdAtividade()) == null) {
			throw new NoSuchElementFoundException("Não foi possível atualizar. A atividade de id = "
					+ atividade/*DTO*/.getIdAtividade() + " não foi encontrada.");
		}

		return new ResponseEntity<>(atividadeService.updateAtividade(atividade/*DTO*/), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteAtividade(@RequestBody Atividade/*DTO*/ atividade/*DTO*/) {
		if (atividadeService.findAtividadeById(atividade/*DTO*/.getIdAtividade()) == null) {
			throw new NoSuchElementFoundException("Não foi possível excluir. A atividade de id = "
					+ atividade/*DTO*/.getIdAtividade() + " não foi encontrada.");
		}

		atividadeService.deleteAtividade(atividade/*DTO*/);
		return new ResponseEntity<>("A atividade de id = " + atividade/*DTO*/.getIdAtividade() + " foi excluída com sucesso.",
				HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAtividadeById(@PathVariable Integer id) {
		if (atividadeService.findAtividadeById(id) == null) {
			throw new NoSuchElementFoundException(
					"Não foi possível excluir. A atividade de id = " + id + " não foi encontrada.");
		}

		atividadeService.deleteAtividadeById(id);
		return new ResponseEntity<>("A atividade de id = " + id + " foi excluída com sucesso.", HttpStatus.OK);
	}
	
}
