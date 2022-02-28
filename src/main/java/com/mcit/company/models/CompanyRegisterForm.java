package com.mcit.company.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CompanyRegisterForm {
	
	private String companyName;
	private String emailId;
	private String password;
		
}
