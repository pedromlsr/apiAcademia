package com.residencia.academia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.academia.dto.AtividadeDTO;
import com.residencia.academia.dto.TurmaDTO;
import com.residencia.academia.entity.Atividade;
import com.residencia.academia.entity.Turma;
import com.residencia.academia.repository.AtividadeRepository;

@Service
public class AtividadeService {
	@Autowired
	private AtividadeRepository atividadeRepository;

	public List<Atividade> findAllAtividade() {
		return atividadeRepository.findAll();
	}

	public Atividade findAtividadeById(Integer id) {
		return atividadeRepository.existsById(id) ? atividadeRepository.findById(id).get() : null;
	}

	public AtividadeDTO findAtividadeDTOById(Integer id) {
		return atividadeRepository.existsById(id) ? convertEntityToDTO(atividadeRepository.findById(id).get()) : null;
	}

	public Atividade saveAtividade(Atividade atividade) {
		return atividadeRepository.save(atividade);
	}

	public AtividadeDTO saveAtividadeDTO(AtividadeDTO atividadeDTO) {
		Atividade atividade = convertDTOToEntity(atividadeDTO);
		Atividade atividadeSave = atividadeRepository.save(atividade);
		return convertEntityToDTO(atividadeSave);
	}

	public Atividade updateAtividade(Atividade atividade) {
		return atividadeRepository.save(atividade);
	}

	public void deleteAtividade(Atividade atividade) {
		atividadeRepository.delete(atividade);
	}

	public void deleteAtividadeById(Integer id) {
		atividadeRepository.deleteById(id);
	}

	private AtividadeDTO convertEntityToDTO(Atividade atividade) {
		AtividadeDTO atividadeDTO = new AtividadeDTO();

		atividadeDTO.setIdAtividade(atividade.getIdAtividade());
		atividadeDTO.setNomeAtividade(atividade.getNomeAtividade());

		List<TurmaDTO> listTurmaDTO = new ArrayList<>();

		if (atividade.getTurmaList() != null) {

			for (Turma turma : atividade.getTurmaList()) {
				TurmaDTO turmaDTO = new TurmaDTO();

				turmaDTO.setIdTurma(turma.getIdTurma());
				turmaDTO.setDataInicio(turma.getDataInicio());
				turmaDTO.setDataFim(turma.getDataFim());
				turmaDTO.setDuracaoTurma(turma.getDuracaoTurma());
				turmaDTO.setHorarioTurma(turma.getHorarioTurma());
				turmaDTO.setInstrutor(turma.getInstrutor());
				turmaDTO.setAtividade(turma.getAtividade());

				listTurmaDTO.add(turmaDTO);
			}

			atividadeDTO.setTurmaDTOList(listTurmaDTO);
		}

		return atividadeDTO;
	}

	private Atividade convertDTOToEntity(AtividadeDTO atividadeDTO) {
		Atividade atividade = new Atividade();

		atividade.setIdAtividade(atividadeDTO.getIdAtividade());
		atividade.setNomeAtividade(atividadeDTO.getNomeAtividade());

		return atividade;
	}

}
