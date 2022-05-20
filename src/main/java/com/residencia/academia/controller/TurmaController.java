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
import com.residencia.academia.service.TurmaService;

@RestController
@RequestMapping("/turma")
public class TurmaController {
	@Autowired
	private TurmaService turmaService;

	@GetMapping
	public ResponseEntity<List<Turma>> findAllTurma() {
//        return ResponseEntity.ok().body(turmaService.findAllTurma());

		List<Turma> turmaList = turmaService.findAllTurma();
		return new ResponseEntity<>(turmaList, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Turma> findTurmaById(@PathVariable Integer id) {
//        return turmaService.findTurmaById(id);

		Turma turmaGetById = turmaService.findTurmaById(id);
		return new ResponseEntity<>(turmaGetById, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Turma> saveTurma(@RequestBody Turma turma) {
//        return turmaService.saveTurma(turma);

		Turma turmaPost = turmaService.saveTurma(turma);
		return new ResponseEntity<>(turmaPost, HttpStatus.CREATED);

	}

	@PutMapping
	public ResponseEntity<Turma> updateTurma(@RequestBody Turma turma) {
//        return turmaService.updateTurma(turma);

		Turma turmaPut = turmaService.updateTurma(turma);
		return new ResponseEntity<>(turmaPut, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTurma(@PathVariable Integer id) {
		turmaService.deleteTurma(id);
		return new ResponseEntity<>("Turma deletada com sucesso.", HttpStatus.OK);

	}

//	@DeleteMapping
//	public void deleteTurma(@RequestBody Turma turma) {
//		turmaService.deleteTurma(turma);
//	}

}
