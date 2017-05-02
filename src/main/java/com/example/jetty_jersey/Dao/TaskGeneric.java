package com.example.jetty_jersey.Dao;

public class TaskGeneric
{
	private final int id;
	private final String description;
	private final String periodicity;
	private final String ataCategory;
	private final boolean hangarNeed;
	private final int duree; // in hour
	private final String planeType;

	public TaskGeneric()
	{
		this.id = (int) (Math.random() * 1000);
		this.description = "Lallaa";
		this.periodicity = "Toto";
		this.ataCategory = "ATA" + Math.random() * 1000;
		this.hangarNeed = true;
		this.duree = 3;
		this.planeType = "BOEING";
	}

}
