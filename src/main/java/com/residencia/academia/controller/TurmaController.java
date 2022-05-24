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

import com.residencia.academia.entity.Turma;
import com.residencia.academia.exception.NoSuchElementFoundException;
import com.residencia.academia.service.TurmaService;

@RestController
@RequestMapping("/turma")
public class TurmaController {
	@Autowired
	private TurmaService turmaService;

	@GetMapping
	public ResponseEntity<List<Turma>> findAllTurma() {
		return new ResponseEntity<>(turmaService.findAllTurma(), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Turma> findTurmaById(@PathVariable Integer id) {
//		return new ResponseEntity<>(turmaService.findTurmaById(id), HttpStatus.OK);
		
//		Turma turma = turmaService.findTurmaById(id);
//		if(turmaService.findTurmaById(id) == null) {
//			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//			return ResponseEntity.notFound().build();
//		} else {
//			return new ResponseEntity<>(turmaService.findTurmaById(id), HttpStatus.OK);
//			return ResponseEntity.ok(turmaService.findTurmaById(id));
//		}
		
		Turma turma = turmaService.findTurmaById(id);
		if(null == turma) {
			throw new NoSuchElementFoundException("Não foi possível encontrar a turma de id = " + id);
		} else {
			return new ResponseEntity<>(turmaService.findTurmaById(id), HttpStatus.OK);
		}
		
	}

	@PostMapping
	public ResponseEntity<Turma> saveTurma(@RequestBody Turma turma) {
		return new ResponseEntity<>(turmaService.saveTurma(turma), HttpStatus.CREATED);

	}

	@PutMapping
	public ResponseEntity<Turma> updateTurma(@RequestBody Turma turma) {
//		return new ResponseEntity<>(turmaService.updateTurma(turma), HttpStatus.OK);
//		return ResponseEntity.ok(turmaService.updateTurma(turma));

		if(turmaService.updateTurma(turma) == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(turmaService.updateTurma(turma));
		}
		
	}

//	@DeleteMapping("/{id}")
//	public ResponseEntity<String> deleteTurmaById(@PathVariable Integer id) {
//		turmaService.deleteTurmaById(id);
//		return new ResponseEntity<>("Turma deletada com sucesso.", HttpStatus.NO_CONTENT);
//		return new ResponseEntity<>("Turma deletada com sucesso.", HttpStatus.OK);
//		
//	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTurma(@PathVariable Integer id) {
		Turma turma = turmaService.findTurmaById(id);
		if(null == turma)
			throw new NoSuchElementFoundException("Não foi possível excluir a Turma, "
					+ "pois não foi "
					+ "encontrada uma turma com o id " + id);

		turmaService.deleteTurma(id);
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	
//	@DeleteMapping("/delete-com-conferencia/{id}")
//	public ResponseEntity<String> deleteTurmaComConferencia(@PathVariable Integer id) {
//		Boolean verificacao = turmaService.deleteTurmaComConferencia(id);
//		if(verificacao) {
//			return new ResponseEntity<>("", HttpStatus.OK);
//		} else {
//			throw new NoSuchElementFoundException("Não foi possível excluir a turma. Não foi encontrada a turma de id = " + id);
//		}
//	}

//	@DeleteMapping
//	public void deleteTurma(@RequestBody Turma turma) {
//		turmaService.deleteTurma(turma);
//	}

}
