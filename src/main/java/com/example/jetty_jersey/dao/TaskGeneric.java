package com.example.jetty_jersey.Dao;

public class TaskGeneric
{
	private final int id;
	private String description;
	private String periodicity;
	private String ataCategory;
	private boolean hangarNeed;
	private final int duree; // in hour
	private final String planeType;

	public TaskGeneric()
	{
		this.id = (int) (Math.random() * 1000);
		this.description = "Lallaa";
		this.periodicity = "Toto";
		this.ataCategory = "ATA" + Math.random() * 1000;
		this.hangarNeed = (Math.random() < 0.5) ? true : false;
		this.duree = 3;
		this.planeType = "BOEING";
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

}
