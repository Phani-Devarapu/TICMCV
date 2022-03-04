package com.mcit.cvbuilder.user;

import com.mcit.company.models.Models.CompanyProfile;
import com.mcit.company.models.Models.JobPositions;
import com.mcit.models.RegisterForm;
import com.mcit.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {


    Optional<UserProfile> findByUserName(String userName);
//	Optional<CompanyProfile> findByCompanyName(String companyLoginId);
	void save(RegisterForm registerForm);

	void save(CompanyProfile companyProfile);
    static void save(JobPositions newOffer){};
}