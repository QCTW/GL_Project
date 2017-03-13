package com.example.jetty_jersey.Dao;
import java.security.Timestamp;
import java.util.Date;


public class Task{
	
	private int id;
	private Date startTime;
	private Date endTime;
	private String description;
	private String periodicity;
	private String ataCategory;
	private boolean hangarNeed;
	private int planeId;
	private int taskStatus;
	
	public Task(){
		
	}

	public Task(int id, Date startTime, Date endTime, String description, String periodicity, String ataCategory,
			boolean hangarNeed, int planeId, int taskStatus) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
		this.periodicity = periodicity;
		this.ataCategory = ataCategory;
		this.hangarNeed = hangarNeed;
		this.planeId = planeId;
		this.taskStatus = taskStatus;
	}
	
	public Task(int id, int planeId){
		this.id = id;
		this.startTime = new Date((long)(Math.random()*System.currentTimeMillis()));
		this.endTime = new Date((long)(Math.random()*System.currentTimeMillis()));
		this.description = "description"+id;
		this.periodicity = "periodicity"+id;
		this.ataCategory = "ata"+id;
		this.hangarNeed = (Math.random()<0.5)?true:false;
		this.planeId = planeId;
		this.taskStatus = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getPlaneId() {
		return planeId;
	}

	public void setPlaneId(int planeId) {
		this.planeId = planeId;
	}

	public int getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	
	
	

}
