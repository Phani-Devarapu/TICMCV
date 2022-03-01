package com.mcit.company.models.Repository;

import com.mcit.company.models.Models.CompanyProfile;
import com.mcit.company.models.Models.JobPositions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobPositionRepository extends JpaRepository<JobPositions,Integer> {
    Optional<CompanyProfile> findByCompanyName(String companyLoginId);
}
