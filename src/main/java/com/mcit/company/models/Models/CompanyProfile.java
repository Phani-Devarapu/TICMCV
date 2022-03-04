package com.mcit.company.models.Models;

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
    private int id;
    private int theme;
    private String companyLoginId;
    private String companyName;
    private String companySecondaryName;
    private String email;
    private int companyPhone;
    private String designation;

    @OneToMany(cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JoinColumn(name = "job_id")
    List<JobPositions> jobs = new ArrayList<>();
    
}
