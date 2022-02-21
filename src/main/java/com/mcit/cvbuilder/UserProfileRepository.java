package com.mcit.cvbuilder;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mcit.models.RegisterForm;
import com.mcit.models.UserProfile;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
    Optional<UserProfile> findByUserName(String userName);

	void save(RegisterForm registerForm);

	
}