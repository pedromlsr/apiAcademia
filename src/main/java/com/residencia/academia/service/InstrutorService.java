package com.residencia.academia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.academia.entity.Instrutor;
import com.residencia.academia.repository.InstrutorRepository;

@Service
public class InstrutorService {
	
    @Autowired
    private InstrutorRepository instrutorRepository;

    public List<Instrutor> findAllInstrutor(){
        return instrutorRepository.findAll();
    }

    public Instrutor findInstrutorById(Integer id) {
    	return instrutorRepository.findById(id).isPresent() ?
    			instrutorRepository.findById(id).get() : null;
    	
//    	if(instrutorRepository.findById(id).get() == null) {
//    		throw new NotFoundException("Não foi possível encontrar o instrutor de id = " + id);
//    	}
//    	return instrutorRepository.findById(id).get();
    }

    public Instrutor saveInstrutor(Instrutor instrutor){
        return instrutorRepository.save(instrutor);
    }

    public Instrutor updateInstrutor(Instrutor instrutor){
        return instrutorRepository.existsById(instrutor.getIdInstrutor()) ?
        		instrutorRepository.save(instrutor) : null;
    }

    public void deleteInstrutor(Integer id){
    	Instrutor instrutor = instrutorRepository.findById(id).get();
    	instrutorRepository.delete(instrutor);
    }
    
    public void deleteInstrutor(Instrutor instrutor){
    	instrutorRepository.delete(instrutor);
    }
}
