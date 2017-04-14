package com.example.jetty_jersey.Dao;

import java.util.Date;

import com.example.jetty_jersey.db.Utility;

public class Task
{

	private final int id;
	private Date startTime;
	private Date endTime;
	private String description;
	private String periodicity;
	private String ataCategory;
	private boolean hangarNeed;
	private int planeId;
	private int taskStatus;
	private int mroId;
	private static int cpt = 0;

	public Task(int id, Date startTime, Date endTime, String description, String periodicity, String ataCategory, boolean hangarNeed, int planeId, int taskStatus, int mroId)
	{
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
		this.mroId = mroId;
	}

	public Task(int id, int planeId)
	{
		int x = (int) (Math.random() * 1000);
		this.id = cpt++;
		this.startTime = new Date((long) (Math.random() * System.currentTimeMillis()));
		this.endTime = new Date((long) (Math.random() * System.currentTimeMillis()));
		this.description = "description" + x;
		this.periodicity = "periodicity" + x;
		this.ataCategory = "ata" + x;
		this.hangarNeed = (Math.random() < 0.5) ? true : false;
		this.planeId = planeId;
		this.taskStatus = (Math.random() < 0.5) ? 1 : 2;
		;
		this.mroId = (int) (Math.random() * 10);
	}

	public int getId()
	{
		return id;
	}

	public Date getStartTime()
	{
		return startTime;
	}

	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	public Date getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getPeriodicity()
	{
		return periodicity;
	}

	public void setPeriodicity(String periodicity)
	{
		this.periodicity = periodicity;
	}

	public String getAtaCategory()
	{
		return ataCategory;
	}

	public void setAtaCategory(String ataCategory)
	{
		this.ataCategory = ataCategory;
	}

	public boolean isHangarNeed()
	{
		return hangarNeed;
	}

	public void setHangarNeed(boolean hangarNeed)
	{
		this.hangarNeed = hangarNeed;
	}

	public int getPlaneId()
	{
		return planeId;
	}

	public void setPlaneId(int planeId)
	{
		this.planeId = planeId;
	}

	public int getTaskStatus()
	{
		return taskStatus;
	}

	public void setTaskStatus(int taskStatus)
	{
		this.taskStatus = taskStatus;
	}

	public int getMroId()
	{
		return mroId;
	}

	public void setMroId(int mroId)
	{
		this.mroId = mroId;
	}

	@Override
	public String toString()
	{
		return "id=" + id + ";start=" + Utility.convertDateToString(startTime) + ";end=" + Utility.convertDateToString(endTime) + ";desc=" + description + ";periodicity=" + periodicity + ";ata="
				+ ataCategory + ";hangarNeed=" + hangarNeed + ";planeId=" + planeId + ";taskStatus=" + taskStatus + ";mroId=" + mroId;
	}

}
