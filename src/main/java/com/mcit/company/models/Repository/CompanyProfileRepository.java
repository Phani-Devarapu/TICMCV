package com.mcit.company.models;

import com.mcit.models.RegisterForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyProfileRepository extends JpaRepository<CompanyProfile, Integer> {
	Optional<CompanyProfile> findByCompanyName(String companyName);

	void save(RegisterForm registerForm);

}