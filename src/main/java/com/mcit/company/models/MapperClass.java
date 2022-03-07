package com.mcit.company.models;


import javax.persistence.*;


@Entity
@Table
public class MapperClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    public String companyLoginId;
    public boolean iscompanyLoginId;
    
	public MapperClass(String companyLoginId, boolean iscompanyLoginId) {
		super();
		this.companyLoginId = companyLoginId;
		this.iscompanyLoginId = iscompanyLoginId;
	}

	public MapperClass() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyLoginId() {
		return companyLoginId;
	}

	public void setCompanyLoginId(String companyLoginId) {
		this.companyLoginId = companyLoginId;
	}

	public boolean isIscompanyLoginId() {
		return iscompanyLoginId;
	}

	public void setIscompanyLoginId(boolean iscompanyLoginId) {
		this.iscompanyLoginId = iscompanyLoginId;
	}
}
