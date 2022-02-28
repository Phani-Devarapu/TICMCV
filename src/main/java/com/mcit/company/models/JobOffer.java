package com.mcit.company.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class JobOffer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int jobId;
	private String jobTitle;
	private String jobDescription;
	private int salary;
	private String jobType;
	@ElementCollection(targetClass = String.class)
	List<String> requiredskills = new ArrayList<>();
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate creationDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
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