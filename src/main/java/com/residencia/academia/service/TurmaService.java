package com.residencia.academia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.academia.dto.TurmaDTO;
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
		return turmaRepository.existsById(id) ? turmaRepository.findById(id).get() : null;
	}

	public TurmaDTO findTurmaDTOById(Integer id) {
		return turmaRepository.existsById(id) ? convertEntityToDTO(turmaRepository.findById(id).get()) : null;
	}

	public Turma saveTurma(Turma turma) {
		return turmaRepository.save(turma);
	}

	public TurmaDTO saveTurmaDTO(TurmaDTO turmaDTO) {
		Turma turma = convertDTOToEntity(turmaDTO);
		Turma turmaSave = turmaRepository.save(turma);
		return convertEntityToDTO(turmaSave);
	}

	public Turma updateTurma(Turma turma) {
		return turmaRepository.save(turma);
	}

	public void deleteTurma(Turma turma) {
		turmaRepository.delete(turma);
	}

	public void deleteTurmaById(Integer id) {
		turmaRepository.deleteById(id);
	}

	private TurmaDTO convertEntityToDTO(Turma turma) {
		TurmaDTO turmaDTO = new TurmaDTO();

		turmaDTO.setIdTurma(turma.getIdTurma());
		turmaDTO.setDataInicio(turma.getDataInicio());
		turmaDTO.setDataFim(turma.getDataFim());
		turmaDTO.setDuracaoTurma(turma.getDuracaoTurma());
		turmaDTO.setHorarioTurma(turma.getHorarioTurma());

		turmaDTO.setInstrutor(turma.getInstrutor());
		turmaDTO.setAtividade(turma.getAtividade());

//		InstrutorDTO instrutorDTO = new InstrutorDTO();
//		
//		if (turma.getInstrutor() != null) {
//			
//			instrutorDTO.setIdInstrutor(turma.getInstrutor().getIdInstrutor());
//			instrutorDTO.setRgInstrutor(turma.getInstrutor().getRgInstrutor());
//			instrutorDTO.setNomeInstrutor(turma.getInstrutor().getNomeInstrutor());
//			instrutorDTO.setDataNascimento(turma.getInstrutor().getDataNascimento());
//			instrutorDTO.setTitulacaoInstrutor(turma.getInstrutor().getTitulacaoInstrutor());
//			
//			turmaDTO.setInstrutorDTO(instrutorDTO);
//		}
//		
//		AtividadeDTO atividadeDTO = new AtividadeDTO();
//			
//		if (turma.getAtividade() != null) {
//			
//			atividadeDTO.setIdAtividade(turma.getAtividade().getIdAtividade());
//			atividadeDTO.setNomeAtividade(turma.getAtividade().getNomeAtividade());
//			
//			turmaDTO.setAtividadeDTO(atividadeDTO);
//		}

		return turmaDTO;
	}

	private Turma convertDTOToEntity(TurmaDTO turmaDTO) {
		Turma turma = new Turma();

		turma.setIdTurma(turmaDTO.getIdTurma());
		turma.setDataInicio(turmaDTO.getDataInicio());
		turma.setDataFim(turmaDTO.getDataFim());
		turma.setDuracaoTurma(turmaDTO.getDuracaoTurma());
		turma.setHorarioTurma(turmaDTO.getHorarioTurma());

		turma.setInstrutor(turmaDTO.getInstrutor());
		turma.setAtividade(turmaDTO.getAtividade());

		return turma;
	}

}
