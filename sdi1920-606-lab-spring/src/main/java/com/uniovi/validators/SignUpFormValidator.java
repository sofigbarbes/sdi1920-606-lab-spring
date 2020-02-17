package com.uniovi.validators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

@Component
public class SignUpFormValidator implements Validator {
	@Autowired
	private UsersService usersService = new UsersService();

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		List<User> users = new ArrayList<User>(usersService.getUsers());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
//		if (user.getDni().length() < 5 || user.getDni().length() > 24) {
//			errors.rejectValue("dni", "Error.signup.dni.length");
//		}
		if (usersService.getUserByDni(user.getDni()) != null) {
			errors.rejectValue("dni", "Error.signup.dni.duplicate");
		}
		if (user.getName().length() < 5 || user.getName().length() > 24) {
			errors.rejectValue("name", "Error.signup.name.length");
		}
		if (user.getLastName().length() < 5 || user.getLastName().length() > 24) {
			errors.rejectValue("lastName", "Error.signup.lastName.length");
		}
		if (user.getPassword().length() < 5 || user.getPassword().length() > 24) {
			errors.rejectValue("password", "Error.signup.password.length");
		}
		if (!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "Error.signup.passwordConfirm.coincidence");
		}
		if (!Character.isLetter(user.getDni().charAt(user.getDni().length() - 1))) {
			errors.rejectValue("dni", "Error.signup.dni.format");
		}
		if (user.getDni().length() != 9) {
			System.err.println("bjkbjk");
			errors.rejectValue("dni", "Error.signup.dni.length");
		}
		for (User u : users) {
			if (u.getDni() == user.getDni()) {

			}
		}
	}
}