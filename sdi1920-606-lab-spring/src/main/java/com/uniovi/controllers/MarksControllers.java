package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Mark;
import com.uniovi.entities.User;
import com.uniovi.services.MarksService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.AddMarkFormValidator;

@Controller
public class MarksControllers {

	@Autowired // Inyectar el servicio
	private MarksService marksService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private AddMarkFormValidator markValidator;

	@Autowired
	private HttpSession httpSession;

	@RequestMapping(value = "/mark/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute Mark mark) {
		Mark original = marksService.getMark(id);
		// modificar solo score y description
		original.setScore(mark.getScore());
		original.setDescription(mark.getDescription());
		marksService.addMark(original);

		return "redirect:/mark/details/" + id;
	}

	@RequestMapping(value = "/mark/add", method = RequestMethod.POST)
	public String setMark(@Validated Mark mark, BindingResult result) {
		markValidator.validate(mark, result);
		if (result.hasErrors()) {
			return "mark/add";
		}
		marksService.addMark(mark);
		return "redirect:/mark/list";
	}

	@RequestMapping("/mark/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		// return marksService.getMark(id).toString();// " Getting Detail: " + id;
		model.addAttribute("mark", marksService.getMark(id));
		return "mark/details";

	}

	@RequestMapping("/mark/delete/{id}")
	public String deleteMark(@PathVariable Long id) {
		marksService.deleteMark(id);
		return "redirect:/mark/list";
	}

	@RequestMapping(value = "/mark/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		model.addAttribute("mark", marksService.getMark(id));
		model.addAttribute("usersList", usersService.getUsers());

		return "mark/edit";
	}

//	@RequestMapping("/mark/add")
//	public String setMark() {
//		return "/mark/add";
//	}

	@RequestMapping("/mark/list/update")
	public String updateList(Model model, Pageable pageable, Principal principal) {
		String dni = principal.getName(); // DNI es el name de la autenticación
		User user = usersService.getUserByDni(dni);
		Page<Mark> marks = marksService.getMarksForUser(pageable, user);

		model.addAttribute("markList", marks.getContent());
		return "mark/list :: tableMarks";
	}

	@RequestMapping(value = "/mark/add")
	public String getMark(Model model) {
		model.addAttribute("mark", new Mark());
		model.addAttribute("usersList", usersService.getUsers());
		return "mark/add";
	}

	@RequestMapping(value = "/mark/{id}/resend", method = RequestMethod.GET)
	public String setResendTrue(Model model, @PathVariable Long id) {
		marksService.setMarkResend(true, id);
		return "redirect:/mark/list";
	}

	@RequestMapping(value = "/mark/{id}/noresend", method = RequestMethod.GET)
	public String setResendFalse(Model model, @PathVariable Long id) {
		marksService.setMarkResend(false, id);
		return "redirect:/mark/list";
	}

	@RequestMapping("/mark/list")
	public String getList(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
		String dni = principal.getName(); // DNI es el name de la autenticaciOn
		User user = usersService.getUserByDni(dni);
		Page<Mark> marks = new PageImpl<Mark>(new LinkedList<Mark>());

		if (searchText != null && !searchText.isEmpty()) {
			marks = marksService.searchMarksByDescriptionAndNameForUser(pageable, searchText, user);
//			model.addAttribute("markList", marksService.searchMarksByDescriptionAndNameForUser(pageable, searchText, user));
		} else {
			marks = marksService.getMarksForUser(pageable, user);
//			model.addAttribute("markList", marksService.getMarksForUser(pageable, user));
		}
		model.addAttribute("markList",marks.getContent());
		model.addAttribute("page", marks);

		return "mark/list";
	}

//	@RequestMapping(value = "/mark/add", method = RequestMethod.POST)
//	public String setMark() {
//		return "Adding Mark";
//	}

//	@RequestMapping(value = "/mark/add", method = RequestMethod.POST)
//	public String setMark(@RequestParam String description, @RequestParam String score) {
//		return "Added: "+description+" with score: "+score;
//	}

	/*
	 * @RequestMapping("/mark/details") public String getDetail(@RequestParam long
	 * id) { return "Getting Details: " + id; }
	 */

//	@RequestMapping("/mark/list")
//	public String getList() {
//		return marksService.getMarks().toString();// "Getting List";
//	}

//	@RequestMapping("/mark/list")
//	public String getList(Model model) {
//		model.addAttribute("markList", marksService.getMarks());
//		return "mark/list";
//	}

	//
//	@RequestMapping("/mark/list/update")
//	public String updateList(Model model) {
//		model.addAttribute("markList", marksService.getMarks());
//		return "mark/list :: tableMarks";
//	}

//
//	@RequestMapping("/mark/list")
//	public String getList(Model model, Principal principal) {
//		String dni = principal.getName(); // DNI es el name de la autenticación
//		User user = usersService.getUserByDni(dni);
//		model.addAttribute("markList", marksService.getMarksForUser(user));
//		return "mark/list";
//	}

}
