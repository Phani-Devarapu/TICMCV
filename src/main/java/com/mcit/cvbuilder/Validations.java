package com.mcit.cvbuilder;

import java.util.ArrayList;
import java.util.List;

import com.mcit.models.UserProfile;


public class Validations {

	List<String> validationErros = new ArrayList<String>();

	public List<String> validateProfile(UserProfile userProfile) {

		if (userProfile.getFirstName().isBlank()) {
			validationErros.add("FirstName should not be empty");
		}
		if (userProfile.getLastName().isBlank()) {
			validationErros.add("LastName should not be empty");
		}
		if (userProfile.getEmail().isBlank() || userProfile.getEmail().length() < 8) {
			validationErros.add("Not a Valid Email");
		}
		return validationErros;

	}

}
