package com.mcit.cvbuilder;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mcit.cvbuilder.models.RegisterForm;
import com.mcit.cvbuilder.models.User;
import com.mcit.cvbuilder.models.UserProfile;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
    Optional<UserProfile> findByUserName(String userName);

	void save(RegisterForm registerForm);

	
}