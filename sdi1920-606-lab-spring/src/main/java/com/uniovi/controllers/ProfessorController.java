package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Mark;
import com.uniovi.entities.Professor;
import com.uniovi.entities.User;
import com.uniovi.services.ProfessorService;
import com.uniovi.validators.ProfessorValidator;

@Controller
public class ProfessorController {

	@Autowired
	ProfessorService profService;
	@Autowired
	private ProfessorValidator professorValidator;

	@RequestMapping("/professor/list")
	public String getList(Model model) {
		model.addAttribute("professorList", profService.getProfessors());
		return "professor/list";
	}

	@RequestMapping(value = "/professor/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute Professor p) {
		p.setId(id);
		profService.addProfessor(p);
		return "redirect:/professor/details/" + id;
	}

	@RequestMapping(value = "/professor/add", method = RequestMethod.POST)
	public String setProfessor(@Validated Professor professor, BindingResult result) {
		professorValidator.validate(professor,result);
		if (result.hasErrors()) {
			return "professor/add";
		}
		profService.addProfessor(professor);
		
		return "redirect:/professor/list";
	}

	@RequestMapping("/professor/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("professor", profService.getProfessor(id));
		return "professor/details";
	}

	@RequestMapping("/professor/delete/{id}")
	public String deleteProfessor(@PathVariable Long id) {
		profService.deleteProfessor(id);
		return "redirect:/professor/list";
	}

	@RequestMapping(value = "/professor/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		model.addAttribute("professor", profService.getProfessor(id));
		return "professor/edit";
	}

	@RequestMapping("/professor/add")
	public String setProfessor(Model model) {
		model.addAttribute("professor", new Professor());
		model.addAttribute("usersList", profService.getProfessors());
		return "professor/add";
	}

}
