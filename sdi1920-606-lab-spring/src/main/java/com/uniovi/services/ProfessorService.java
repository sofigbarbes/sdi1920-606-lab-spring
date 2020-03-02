package com.uniovi.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Professor;
import com.uniovi.entities.User;
import com.uniovi.repositories.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository profRepository;

//	@PostConstruct
//	public void init() {
//		professors.add(new Professor(1L, "333333333", "Paco", "García", "ciencias"));
//		professors.add(new Professor(2L, "444444444", "Marta", "Sánchez", "letras"));
//	}

	public List<Professor> getProfessors() {
		List<Professor> professors = new ArrayList<Professor>();
		profRepository.findAll().forEach(professors::add);
		return professors;
	}

	public Professor getProfessor(Long id) {
		return profRepository.findById(id).get();

	}

	public void addProfessor(Professor p) {
		profRepository.save(p);

	}

	public void deleteProfessor(Long id) {
		profRepository.deleteById(id);

	}
	
}
