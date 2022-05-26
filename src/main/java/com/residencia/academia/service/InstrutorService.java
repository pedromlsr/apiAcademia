package com.residencia.academia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.academia.dto.InstrutorDTO;
import com.residencia.academia.dto.TurmaDTO;
import com.residencia.academia.entity.Instrutor;
import com.residencia.academia.entity.Turma;
import com.residencia.academia.repository.InstrutorRepository;

@Service
public class InstrutorService {
	@Autowired
	private InstrutorRepository instrutorRepository;

	public List<Instrutor> findAllInstrutor() {
		return instrutorRepository.findAll();
	}

	public Instrutor findInstrutorById(Integer id) {
		return instrutorRepository.existsById(id) ? instrutorRepository.findById(id).get() : null;
	}

	public InstrutorDTO findInstrutorDTOById(Integer id) {
		return instrutorRepository.existsById(id) ? convertEntityToDTO(instrutorRepository.findById(id).get()) : null;
	}

	public Instrutor saveInstrutor(Instrutor instrutor) {
		return instrutorRepository.save(instrutor);
	}

	public InstrutorDTO saveInstrutorDTO(InstrutorDTO instrutorDTO) {
		Instrutor instrutor = convertDTOToEntity(instrutorDTO);
		Instrutor instrutorSave = instrutorRepository.save(instrutor);
		return convertEntityToDTO(instrutorSave);
	}

	public Instrutor updateInstrutor(Instrutor instrutor) {
		return instrutorRepository.save(instrutor);
	}

	public void deleteInstrutor(Instrutor instrutor) {
		instrutorRepository.delete(instrutor);
	}

	public void deleteInstrutorById(Integer id) {
		instrutorRepository.deleteById(id);
	}

	private InstrutorDTO convertEntityToDTO(Instrutor instrutor) {
		InstrutorDTO instrutorDTO = new InstrutorDTO();

		instrutorDTO.setIdInstrutor(instrutor.getIdInstrutor());
		instrutorDTO.setRgInstrutor(instrutor.getRgInstrutor());
		instrutorDTO.setNomeInstrutor(instrutor.getNomeInstrutor());
		instrutorDTO.setDataNascimento(instrutor.getDataNascimento());
		instrutorDTO.setTitulacaoInstrutor(instrutor.getTitulacaoInstrutor());

		List<TurmaDTO> listTurmaDTO = new ArrayList<>();

		if (instrutor.getTurmaList() != null) {

			for (Turma turma : instrutor.getTurmaList()) {
				TurmaDTO turmaDTO = new TurmaDTO();

				turmaDTO.setIdTurma(turma.getIdTurma());
				turmaDTO.setDataInicio(turma.getDataInicio());
				turmaDTO.setDataFim(turma.getDataFim());
				turmaDTO.setDuracaoTurma(turma.getDuracaoTurma());
				turmaDTO.setHorarioTurma(turma.getHorarioTurma());

				listTurmaDTO.add(turmaDTO);
			}

			instrutorDTO.setTurmaDTOList(listTurmaDTO);
		}

		return instrutorDTO;
	}

	private Instrutor convertDTOToEntity(InstrutorDTO instrutorDTO) {
		Instrutor instrutor = new Instrutor();

		instrutor.setIdInstrutor(instrutorDTO.getIdInstrutor());
		instrutor.setRgInstrutor(instrutorDTO.getRgInstrutor());
		instrutor.setNomeInstrutor(instrutorDTO.getNomeInstrutor());
		instrutor.setDataNascimento(instrutorDTO.getDataNascimento());
		instrutor.setTitulacaoInstrutor(instrutorDTO.getTitulacaoInstrutor());

		return instrutor;
	}

}
