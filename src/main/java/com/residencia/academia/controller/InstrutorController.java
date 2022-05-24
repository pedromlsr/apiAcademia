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
		return ResponseEntity.ok().body(instrutorService.findAllInstrutor());

//		List<Instrutor> instrutorList = instrutorService.findAllInstrutor();
//		return new ResponseEntity<>(instrutorList, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Instrutor> findInstrutorById(@PathVariable Integer id) {
//		return instrutorService.findInstrutorById(id);

//		Instrutor instrutorGetById = instrutorService.findInstrutorById(id);
//		return new ResponseEntity<>(instrutorGetById, HttpStatus.OK);

//		Instrutor instrutor = instrutorService.findInstrutorById(id);
		if(instrutorService.findInstrutorById(id) == null)
//			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			return ResponseEntity.notFound().build();
		else {
//			return new ResponseEntity<>(instrutorService.findInstrutorById(id), HttpStatus.OK);
			return ResponseEntity.ok().body(instrutorService.findInstrutorById(id));
		}
	}

	@PostMapping
	public ResponseEntity<Instrutor> saveInstrutor(@RequestBody Instrutor instrutor) {
//		return instrutorService.saveInstrutor(instrutor);
		return new ResponseEntity<>(instrutorService.saveInstrutor(instrutor), HttpStatus.CREATED);
		
	}

	@PutMapping
	public ResponseEntity<Instrutor> updateInstrutor(@RequestBody Instrutor instrutor) {
//		return instrutorService.updateInstrutor(instrutor);

//		Instrutor instrutorPut = instrutorService.updateInstrutor(instrutor);
//		return new ResponseEntity<>(instrutorPut, HttpStatus.OK);
		
//		return new ResponseEntity<>(instrutorService.updateInstrutor(instrutor), HttpStatus.OK);
//		return ResponseEntity.ok(instrutorService.updateInstrutor(instrutor));
		
		if(instrutorService.updateInstrutor(instrutor) == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(instrutorService.updateInstrutor(instrutor));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteInstrutor(@PathVariable Integer id) {
		instrutorService.deleteInstrutor(id);
		return new ResponseEntity<>("Instrutor deletado com sucesso.", HttpStatus.OK);
//		return new ResponseEntity<>("Instrutor deletado com sucesso.", HttpStatus.NO_CONTENT);

		// Neste caso, não estamos fazendo nenhum tipo de verificação se o método está
		// funcionando de maneira correta. O ideal seria fazer um try catch.

	}

	@DeleteMapping
	public ResponseEntity<String> deleteInstrutor(@RequestBody Instrutor instrutor) {
		instrutorService.deleteInstrutor(instrutor);
		return new ResponseEntity<>("Instrutor deletado com sucesso.", HttpStatus.OK);
//		return new ResponseEntity<>("Instrutor deletado com sucesso.", HttpStatus.NO_CONTENT);

	}

}
