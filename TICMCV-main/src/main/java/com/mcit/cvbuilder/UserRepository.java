package com.mcit.cvbuilder;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mcit.cvbuilder.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}