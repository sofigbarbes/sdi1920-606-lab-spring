package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Professor;
import com.uniovi.services.ProfessorService;

@Component
public class ProfessorValidator implements Validator {

	@Autowired
	private ProfessorService professorService = new ProfessorService();

	@Override
	public boolean supports(Class<?> clazz) {
		return Professor.class.equals(clazz);

	}

	@Override
	public void validate(Object target, Errors errors) {
		Professor professor = (Professor) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
		if (professor.getnombre().length() < 5 || professor.getnombre().length() > 24) {
			errors.rejectValue("nombre", "Error.signup.name.length");
		}
		if (professor.getApellido().length() < 5 || professor.getApellido().length() > 24) {
			errors.rejectValue("apellido", "Error.signup.lastName.length");
		}
		
		if (!Character.isLetter(professor.getDni().charAt(professor.getDni().length() - 1))) {
			errors.rejectValue("dni", "Error.signup.dni.format");
		}
		if (professor.getDni().length() != 9) {
			errors.rejectValue("dni", "Error.signup.dni.length");
		}
		
	}

}
