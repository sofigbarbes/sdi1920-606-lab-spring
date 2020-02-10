package com.uniovi.services;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.uniovi.entities.Professor;

@Service
public class ProfessorService {
	private List<Professor> professors = new LinkedList<Professor>();

//		@Autowired
//		private MarksRepository marksRepository;

	@PostConstruct
	public void init() {
		professors.add(new Professor(1L, "333333333","Paco","García", "ciencias"));
		professors.add(new Professor(1L, "444444444","Marta","Sánchez", "letras"));
	}

	
	public List<Professor> getProfessors() {
		return professors;
	}

	public Professor getProfessor(Long id) {
		return professors.stream().filter(professor -> professor.getId().equals(id)).findFirst().get();
	}

	public void addProfessor(Professor p) {
		if (p.getId() == null) {
			p.setId(professors.get(professors.size() - 1).getId() + 1);
		}
	}

	public void deleteProfessor(Long id) {
		professors.removeIf(p -> p.getId().equals(id));
	}
}
