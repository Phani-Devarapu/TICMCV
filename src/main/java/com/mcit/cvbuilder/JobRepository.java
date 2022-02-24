package com.mcit.cvbuilder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mcit.models.Job;
import com.mcit.models.User;

import java.util.Optional;

import javax.transaction.Transactional;

public interface JobRepository extends JpaRepository<Job, Integer> {

	@Modifying
    @Transactional
	@Query(value = "DELETE FROM JOB WHERE job_id =?1", nativeQuery = true)
	void deleteJobByProfile(int id);

}