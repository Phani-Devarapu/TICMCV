package com.mcit.company.models;

import javax.persistence.*;



import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class CompanyProfile {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String companyId;
    private String companyName;
    private String companySecondaryName;
    private String email;
    private int companyPhone;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCompanyId() {
		return companyId;
	}


	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getCompanySecondaryName() {
		return companySecondaryName;
	}


	public void setCompanySecondaryName(String companySecondaryName) {
		this.companySecondaryName = companySecondaryName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getCompanyPhone() {
		return companyPhone;
	}


	public void setCompanyPhone(int companyPhone) {
		this.companyPhone = companyPhone;
	}


	@Override
	public String toString() {
		return "CompanyProfile [id=" + id + ", companyId=" + companyId + ", companyName=" + companyName
				+ ", companySecondaryName=" + companySecondaryName + ", email=" + email + ", companyPhone="
				+ companyPhone + "]";
	}

	    
    
    
    
}
