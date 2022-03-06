package com.mcit.company.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Entity
@Table
@ToString
public class JobPositions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id = 0;

	private String jobTitle;
	private String jobDescription;
	private int salary;
	private String jobType;

	@ElementCollection(targetClass = String.class)
	List<String> requiredskills = new ArrayList<>();

	@Transient
	private String skillsInString;
	
	private String requiredExperience;

	private LocalDateTime jobPostedTime;
	private boolean isFulfilled;

	private String companyName;
	private String companyId;
	private String companyDescription;
	private String companyAddress;
	private String companyEmail;
	private String companyPhone;
}