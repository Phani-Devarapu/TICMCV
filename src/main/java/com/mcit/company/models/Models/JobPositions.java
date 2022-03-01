package com.mcit.company.models.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class JobPositions {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
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
//    private String jobTitle;
//    private String jobDescription;
//    	    
//    private int salary; // annual
//    private String jobType; // choose one among Permanent, Contract, Internship, Freelance - maybe radio type?
//	    
//    @ElementCollection(targetClass=String.class)
//    List<String> requiredskills = new ArrayList<>();
//    
//    private String companyName;
//    private String companyDescription;
//    private String companyAddress;
//    private String companyEmail;
//    private String companyPhone;
//  
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate creationDate;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate endDate;    // what date the offer is expiring
}