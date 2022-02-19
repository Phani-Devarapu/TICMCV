package com.mcit.resumebuilder.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class UserRegistrationDto {
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private String userName;
	
	
	//adding the new fields 
	
	private boolean enabled;
	
  private String roles;


	
	public UserRegistrationDto() {
		
	}
	
	
	

	
	public UserRegistrationDto(String firstName, String lastName, String email, String password,
			String roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.setRoles(roles);
	}





	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}




	public String getUserName() {
		return userName;
	}





	public void setUserName(String userName) {
		this.userName = userName;
	}





	public boolean isEnabled() {
		return enabled;
	}





	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}





	public String getRoles() {
		return roles;
	}





	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	

}
