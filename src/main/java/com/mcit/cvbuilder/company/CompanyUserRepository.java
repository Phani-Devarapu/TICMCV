package com.mcit.cvbuilder.company;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mcit.company.models.CompanyUser;

import java.util.Optional;

public interface CompanyUserRepository extends JpaRepository<CompanyUser, Integer> {
	Optional<CompanyUser> findByCompanyName(String companyName);
}