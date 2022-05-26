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
	
	@Autowired
	private InstrutorService instrutorService;
	
	@Autowired
	private AtividadeService atividadeService;

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
		
		if(turma.getInstrutor() != null) {
			turmaDTO.setInstrutorDTO(instrutorService.findInstrutorDTOById(turma.getInstrutor().getIdInstrutor()));
		} else {
			turmaDTO.setAtividadeDTO(null);
		}

		if(turma.getAtividade() != null) {
			turmaDTO.setAtividadeDTO(atividadeService.findAtividadeDTOById(turma.getAtividade().getIdAtividade()));
		} else {
			turmaDTO.setAtividadeDTO(null);
		}

		return turmaDTO;
	}

	private Turma convertDTOToEntity(TurmaDTO turmaDTO) {
		Turma turma = new Turma();

		turma.setIdTurma(turmaDTO.getIdTurma());
		turma.setDataInicio(turmaDTO.getDataInicio());
		turma.setDataFim(turmaDTO.getDataFim());
		turma.setDuracaoTurma(turmaDTO.getDuracaoTurma());
		turma.setHorarioTurma(turmaDTO.getHorarioTurma());
		
		turma.setInstrutor(instrutorService.findInstrutorById(turmaDTO.getInstrutorDTO().getIdInstrutor()));
		turma.setAtividade(atividadeService.findAtividadeById(turmaDTO.getAtividadeDTO().getIdAtividade()));
		
		return turma;
	}

}
