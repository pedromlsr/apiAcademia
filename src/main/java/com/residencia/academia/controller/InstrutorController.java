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
import com.residencia.academia.service.InstrutorService;

@RestController
@RequestMapping("/instrutor")
public class InstrutorController {
	@Autowired
	private InstrutorService instrutorService;

	@GetMapping
	public ResponseEntity<List<Instrutor>> findAllInstrutor() {
//		return ResponseEntity.ok().body(instrutorService.findAllInstrutor());

		List<Instrutor> instrutorList = instrutorService.findAllInstrutor();
		return new ResponseEntity<>(instrutorList, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Instrutor> findInstrutorById(@PathVariable Integer id) {
//		return instrutorService.findInstrutorById(id);

		Instrutor instrutorGetById = instrutorService.findInstrutorById(id);
		return new ResponseEntity<>(instrutorGetById, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Instrutor> saveInstrutor(@RequestBody Instrutor instrutor) {
//		return instrutorService.saveInstrutor(instrutor);

		Instrutor instrutorPost = instrutorService.saveInstrutor(instrutor);
		return new ResponseEntity<>(instrutorPost, HttpStatus.CREATED);

	}

	@PutMapping
	public ResponseEntity<Instrutor> updateInstrutor(@RequestBody Instrutor instrutor) {
//		return instrutorService.updateInstrutor(instrutor);

		Instrutor instrutorPut = instrutorService.updateInstrutor(instrutor);
		return new ResponseEntity<>(instrutorPut, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteInstrutor(@PathVariable Integer id) {
		instrutorService.deleteInstrutor(id);
		return new ResponseEntity<>("Instrutor deletado com sucesso.", HttpStatus.OK);

	}

	@DeleteMapping
	public ResponseEntity<String> deleteInstrutor(@RequestBody Instrutor instrutor) {
		instrutorService.deleteInstrutor(instrutor);
		return new ResponseEntity<>("Instrutor deletado com sucesso.", HttpStatus.OK);
		
	}

}
