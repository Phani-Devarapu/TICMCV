package com.mcit.cvbuilder;

import com.mcit.company.models.CompanyUser;
import com.mcit.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String userName);
}