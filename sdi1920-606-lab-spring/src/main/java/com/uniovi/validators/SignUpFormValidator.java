package com.uniovi.validators;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

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
		System.out.println("before Cast");

		User user = (User) target;
		System.out.println(target);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
		if (user.getDni().length() < 5 || user.getDni().length() > 24) {
			errors.rejectValue("dni", "Error.signup.dni.length");
			System.out.println("inside dni");
		}
		if (usersService.getUserByDni(user.getDni()) != null) {
			errors.rejectValue("dni", "Error.signup.dni.duplicate");
			System.out.println("inside dni usado ya");
		}
		if (user.getName().length() < 5 || user.getName().length() > 24) {
			errors.rejectValue("name", "Error.signup.name.length");
			System.out.println("inside name length");
		}
		if (user.getLastName().length() < 5 || user.getLastName().length() > 24) {
			errors.rejectValue("lastName", "Error.signup.lastName.length");
			System.out.println("inside lastNameLengrth");
		}
		if (user.getPassword().length() < 5 || user.getPassword().length() > 24) {
			errors.rejectValue("password", "Error.signup.password.length");
			System.out.println("inside pdw");
		}
		if (!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "Error.signup.passwordConfirm.coincidence");
			System.out.println("inside psweord congirn");
		}
	}
}