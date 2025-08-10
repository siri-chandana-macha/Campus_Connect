package com.campusconnect.model;
import java.util.*;
public class InterviewExperience {
	private int id;
	private String name;
	private String rollNumber;
	private String company;
	private String packageOffered;
	private String duration;
	private String questions;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPackageOffered() {
		return packageOffered;
	}
	public void setPackageOffered(String packageOffered) {
		this.packageOffered = packageOffered;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getQuestions() {
		return questions;
	}
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	public List<String> getQuestionList() {
	    if (questions != null && !questions.isEmpty()) {
	        return Arrays.asList(questions.split("\\r?\\n"));
	    }
	    return new ArrayList<>();
	}



	
	
	

}
