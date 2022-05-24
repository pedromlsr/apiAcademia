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

import com.residencia.academia.entity.Instrutor;
import com.residencia.academia.exception.NoSuchElementFoundException;
import com.residencia.academia.service.InstrutorService;

@RestController
@RequestMapping("/instrutor")
public class InstrutorController {
	@Autowired
	private InstrutorService instrutorService;

	@GetMapping
	public ResponseEntity<List<Instrutor/*DTO*/>> findAllInstrutor() {
		return new ResponseEntity<>(instrutorService.findAllInstrutor(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Instrutor/*DTO*/> findInstrutorById(@PathVariable Integer id) {
		if (instrutorService.findInstrutorById(id) == null) {
			throw new NoSuchElementFoundException("Não foi possível encontrar o instrutor de id = " + id + ".");
		}

		return new ResponseEntity<>(instrutorService.findInstrutorById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Instrutor/*DTO*/> saveInstrutor(@RequestBody Instrutor/*DTO*/ instrutor/*DTO*/) {
		return new ResponseEntity<>(instrutorService.saveInstrutor(instrutor/*DTO*/), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Instrutor/*DTO*/> updateInstrutor(@RequestBody Instrutor/*DTO*/ instrutor/*DTO*/) {
		if (instrutorService.findInstrutorById(instrutor/*DTO*/.getIdInstrutor()) == null) {
			throw new NoSuchElementFoundException("Não foi possível atualizar. O instrutor de id = "
					+ instrutor/*DTO*/.getIdInstrutor() + " não foi encontrado.");
		}

		return new ResponseEntity<>(instrutorService.updateInstrutor(instrutor/*DTO*/), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteInstrutor(@RequestBody Instrutor/*DTO*/ instrutor/*DTO*/) {
		if (instrutorService.findInstrutorById(instrutor/*DTO*/.getIdInstrutor()) == null) {
			throw new NoSuchElementFoundException("Não foi possível excluir. O instrutor de id = "
					+ instrutor/*DTO*/.getIdInstrutor() + " não foi encontrado.");
		}

		instrutorService.deleteInstrutor(instrutor/*DTO*/);
		return new ResponseEntity<>("O instrutor de id = " + instrutor/*DTO*/.getIdInstrutor() + " foi excluído com sucesso.",
				HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteInstrutorById(@PathVariable Integer id) {
		if (instrutorService.findInstrutorById(id) == null) {
			throw new NoSuchElementFoundException(
					"Não foi possível excluir. O instrutor de id = " + id + " não foi encontrado.");
		}

		instrutorService.deleteInstrutorById(id);
		return new ResponseEntity<>("O instrutor de id = " + id + " foi excluído com sucesso.", HttpStatus.OK);
	}
	
}