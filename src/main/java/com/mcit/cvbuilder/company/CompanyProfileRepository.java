package com.mcit.cvbuilder.company;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mcit.company.models.CompanyProfile;
import com.mcit.company.models.CompanyRegisterForm;
import com.mcit.models.RegisterForm;
import com.mcit.models.UserProfile;

import java.util.Optional;

public interface CompanyProfileRepository extends JpaRepository<CompanyProfile, Integer> {
	Optional<CompanyProfile> findByCompanyName(String companyName);

	void save(CompanyRegisterForm companyRegisterForm);

}