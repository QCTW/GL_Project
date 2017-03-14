package com.example.jetty_jersey.Dao;
import java.security.Timestamp;
import java.util.Date;
/*
PUT gla/task/id
 {
 
 "startTime" : "21/03/2017"
 "endTime" : "25/03/2017"
 "description" : "pneu train avant "
 "periodicity" : "tous les mois"
 "ataCategory" : "ata1"
 "hangarNeed" : "true"

}


*/

public class Task{
	
	private Date startTime;
	private Date endTime;
	private String description;
	private String periodicity;
	private String ataCategory;
	private boolean hangarNeed;
	
	public Task(){
		
	}
	public Task(Date startTime, Date endTime, String description, String periodicity, String ataCategory,
			boolean hangarNeed) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
		this.periodicity = periodicity;
		this.ataCategory = ataCategory;
		this.hangarNeed = hangarNeed;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPeriodicity() {
		return periodicity;
	}
	public void setPeriodicity(String periodicity) {
		this.periodicity = periodicity;
	}
	public String getAtaCategory() {
		return ataCategory;
	}
	public void setAtaCategory(String ataCategory) {
		this.ataCategory = ataCategory;
	}
	public boolean isHangarNeed() {
		return hangarNeed;
	}
	public void setHangarNeed(boolean hangarNeed) {
		this.hangarNeed = hangarNeed;
	}
	
	
	

}
