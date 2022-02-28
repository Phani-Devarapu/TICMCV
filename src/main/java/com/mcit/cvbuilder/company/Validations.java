package com.mcit.cvbuilder.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mcit.company.models.CompanyProfile;
import com.mcit.models.Education;
import com.mcit.models.Job;
import com.mcit.models.UserProfile;

public class Validations {

	Set<String> validationErros = new HashSet();

	public Set<String> validateProfile(CompanyProfile companyProfile) {

		if (companyProfile.getCompanyName().isBlank()) {
			validationErros.add("CompanyName should not be empty");
		}
		if (companyProfile.getCompanyEmail().isBlank() || companyProfile.getCompanyEmail().length() < 8) {
			validationErros.add("Not a Valid Email");
		}
		if (companyProfile.getCompanyPhone().isBlank()) {
			validationErros.add("CompanyPhone should not be empty");
		}
		if (companyProfile.getDesignation().isBlank()) {
			validationErros.add("Designation should not be empty");
		}
		return validationErros;

	}

	public Set<String> validateDates(CompanyProfile companyProfile) {
		validateExperienceDate(companyProfile.getJobs());
		validateEducationDate(companyProfile.getEducations());
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
