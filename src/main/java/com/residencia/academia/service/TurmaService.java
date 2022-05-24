package com.residencia.academia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.academia.entity.Turma;
import com.residencia.academia.repository.TurmaRepository;

@Service
public class TurmaService {
	@Autowired
	private TurmaRepository turmaRepository;

	public List<Turma> findAllTurma() {
		return turmaRepository.findAll();
	}

	public Turma findTurmaById(Integer id) {
		return turmaRepository.findById(id).isPresent() ? turmaRepository.findById(id).get() : null;
	}

	public Turma saveTurma(Turma turma) {
		return turmaRepository.save(turma);
	}

	public Turma updateTurma(Turma turma) {
		return turmaRepository.existsById(turma.getIdTurma()) ? turmaRepository.save(turma) : null;
	}

//	public void deleteTurmaById(Integer id) {
//    	Turma turma = turmaRepository.findById(id).get();
//    	turmaRepository.delete(turma);
//		
//		if(!turmaRepository.existsById(id)) {
//			throw new NoSuchElementFoundException("Não foi possível excluir a turma. Não foi encontrada uma turma de id = " + id);
//		} else {
//			turmaRepository.deleteById(id);
//		}
//	}

	public void deleteTurma(Integer id) {
		Turma turma = turmaRepository.findById(id).get();
		turmaRepository.delete(turma);
	}
//    
//    public void deleteTurma(Turma turma){
//    	turmaRepository.delete(turma);
//    }
}
