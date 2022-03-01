package com.mcit.cvbuilder;

import com.mcit.company.models.CompanyProfile;
import com.mcit.company.models.CompanyRegisterForm;
import com.mcit.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
	Optional<UserProfile> findByUserName(String userName);

	void save(CompanyRegisterForm companyRegisterForm);

}