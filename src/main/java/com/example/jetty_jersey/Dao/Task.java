package com.example.jetty_jersey.Dao;
import java.security.Timestamp;


public class Task{
	
	private Timestamp startTime;
	private Timestamp endTime;
	private String description;
	private String periodicity;
	private String ataCategory;
	private boolean hangarNeed;
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
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
