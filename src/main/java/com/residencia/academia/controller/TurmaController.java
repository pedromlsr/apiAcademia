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
		if (turmaService.findTurmaById(id) == null) {
			throw new NoSuchElementFoundException("Não foi possível encontrar a turma de id = " + id + ".");
		}

		return new ResponseEntity<>(turmaService.findTurmaById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Turma> saveTurma(@RequestBody Turma turma) {
		return new ResponseEntity<>(turmaService.saveTurma(turma), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Turma> updateTurma(@RequestBody Turma turma) {
		if (turmaService.findTurmaById(turma.getIdTurma()) == null) {
			throw new NoSuchElementFoundException(
					"Não foi possível atualizar. A turma de id = " + turma.getIdTurma() + " não foi encontrada.");
		}

		return new ResponseEntity<>(turmaService.updateTurma(turma), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteTurma(@RequestBody Turma turma) {
		if (turmaService.findTurmaById(turma.getIdTurma()) == null) {
			throw new NoSuchElementFoundException(
					"Não foi possível excluir. A turma de id = " + turma.getIdTurma() + " não foi encontrada.");
		}

		turmaService.deleteTurma(turma);
		return new ResponseEntity<>("A turma de id = " + turma.getIdTurma() + " foi excluída com sucesso.",
				HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTurmaById(@PathVariable Integer id) {
		if (turmaService.findTurmaById(id) == null) {
			throw new NoSuchElementFoundException(
					"Não foi possível excluir. A turma de id = " + id + " não foi encontrada.");
		}

		turmaService.deleteTurmaById(id);
		return new ResponseEntity<>("A turma de id = " + id + " foi excluída com sucesso.", HttpStatus.OK);
	}

}
