package com.mcit.company.models.Repository;

import com.mcit.company.models.CompanyProfile;
import com.mcit.company.models.JobPositions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobPositionRepository extends JpaRepository<JobPositions,Integer> {
    Optional<CompanyProfile> findByCompanyName(String companyLoginId);
    List<JobPositions> findByCompanyId(String companyLoginId);
}
