package com.mcit.cvbuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mcit.models.Education;
import com.mcit.models.Job;
import com.mcit.models.UserProfile;

public class Validations {

	Set<String> validationErros = new HashSet();

	public Set<String> validateProfile(UserProfile userProfile) {

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

	public Set<String> validateDates(UserProfile userProfile) {
		validateExperienceDate(userProfile.getJobs());
		validateEducationDate(userProfile.getEducations());
		return validationErros;
	}

	private boolean ValidateDate(LocalDate localDate) {
		return localDate.isAfter(LocalDate.now());

	}

	public Set<String> validateExperienceDate(List<Job> jobs) {
		jobs.stream().forEach(job -> {
			if (ValidateDate(job.getStartDate())) {
				validationErros.add("Enter Valid Start Dates In Experience");
			}
			if (job.getEndDate().isBefore(job.getStartDate())) {
				validationErros.add("End date must be after the start date in Experience");
			}
		});

		return validationErros;
	}

	public Set<String> validateEducationDate(List<Education> educations) {
		educations.stream().forEach(edu -> {
			if (ValidateDate(edu.getStartDate())) {
				validationErros.add("Enter Valid Start Dates In education");
			}
			if (edu.getEndDate().isBefore(edu.getStartDate())) {
				validationErros.add("End date must be after the start date in education");
			}
		});

		return validationErros;
	}

}
