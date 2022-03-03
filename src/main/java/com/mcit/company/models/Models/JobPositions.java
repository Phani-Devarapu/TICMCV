package com.mcit.company.models.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class JobPositions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id=0;

	private String jobTitle;
	private String jobDescription;
	private int salary;
	private String jobType;
	//	@ElementCollection(targetClass = String.class)
//	List<String> requiredskills = new ArrayList<>();
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private LocalDate creationDate;
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private LocalDate endDate;
	private String companyName;
	private String companyDescription;
	private String companyAddress;
	private String companyEmail;
	private String companyPhone;
}