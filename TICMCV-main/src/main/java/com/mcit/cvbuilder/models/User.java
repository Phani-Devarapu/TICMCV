package com.mcit.cvbuilder.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userName;
    private String password;
    private boolean active;
    private String roles;
    
    
    //
    
	//new fileds
	private String firstName;
	
	private String lastName;
	
	private String email;



public User() {
	// TODO Auto-generated constructor stub
}
   
	public User(String userName, String password, boolean active, String roles, String firstName, String lastName,
			String email) {

		this.userName = userName;
		this.password = password;
		this.active = active;
		this.roles = roles;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
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

	public User(String userName, String password, boolean active, String roles) {

		this.userName = userName;
		this.password = password;
		this.active = active;
		this.roles = roles;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}