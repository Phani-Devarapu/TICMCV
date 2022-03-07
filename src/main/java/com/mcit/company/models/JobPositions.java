package com.mcit.company.models;



import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table
public class JobPositions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id = 0;

	private String jobTitle;
	private String jobDescription;
	private int salary;
	private String jobType;

	@ElementCollection(targetClass = String.class)
	List<String> requiredskills = new ArrayList<>();

	@Transient
	private String skillsInString;

	private String requiredExperience;

	private LocalDateTime jobPostedTime;
	private boolean isFulfilled;

	private String companyName;
	private String companyId;
	private String companyDescription;
	private String companyAddress;
	private String companyEmail;
	private String companyPhone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public List<String> getRequiredskills() {
		return requiredskills;
	}

	public void setRequiredskills(List<String> requiredskills) {
		this.requiredskills = requiredskills;
	}

	public String getSkillsInString() {
		return skillsInString;
	}

	public void setSkillsInString(String skillsInString) {
		this.skillsInString = skillsInString;
	}

	public String getRequiredExperience() {
		return requiredExperience;
	}

	public void setRequiredExperience(String requiredExperience) {
		this.requiredExperience = requiredExperience;
	}

	public LocalDateTime getJobPostedTime() {
		return jobPostedTime;
	}

	public void setJobPostedTime(LocalDateTime jobPostedTime) {
		this.jobPostedTime = jobPostedTime;
	}

	public boolean isFulfilled() {
		return isFulfilled;
	}

	public void setFulfilled(boolean isFulfilled) {
		this.isFulfilled = isFulfilled;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyDescription() {
		return companyDescription;
	}

	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getFormattedSalary() {
		NumberFormat customFormat = NumberFormat.getInstance();
		customFormat.setGroupingUsed(true);
		return customFormat.format(this.salary);
	}

	@Override
	public String toString() {
		return "JobPositions [id=" + id + ", jobTitle=" + jobTitle + ", jobDescription=" + jobDescription + ", salary="
				+ salary + ", jobType=" + jobType + ", requiredskills=" + requiredskills + ", skillsInString="
				+ skillsInString + ", requiredExperience=" + requiredExperience + ", jobPostedTime=" + jobPostedTime
				+ ", isFulfilled=" + isFulfilled + ", companyName=" + companyName + ", companyId=" + companyId
				+ ", companyDescription=" + companyDescription + ", companyAddress=" + companyAddress
				+ ", companyEmail=" + companyEmail + ", companyPhone=" + companyPhone + "]";
	}

}