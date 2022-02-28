package com.mcit.company.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class CompanyProfile {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int jobId;
    private String companyName;
    private String companyEmail;
    private String companyPhone;
    private String designation;

    @OneToMany(cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JoinColumn(name = "job_id")
    List<JobOffer> jobs = new ArrayList<>();
    
}
