package com.example.jetty_jersey.dao;

public class TaskGeneric
{
	private final int id;
	private final String description;
	private final String periodicity;
	private final String ataCategory;
	private final boolean hangarNeed;
	private final float duree; // in hour
	private final String planeType;

	public TaskGeneric()
	{
		int rand = (int) (Math.random() * 1000);
		this.id = rand;
		this.description = "Demain, dès l’aube, à l’heure où blanchit la campagne. Je partirai. Vois-tu, je sais que tu m’attends. J’irai par la forêt, j’irai par la montagne. Je ne puis demeurer loin de toi plus longtemps.";
		this.periodicity = "Daily";
		this.ataCategory = "Demain, dès l’aube (Victor Hugo)";
		this.hangarNeed = (Math.random() < 0.5) ? true : false;
		this.duree = (long) (Math.random() * 12);
		this.planeType = "Fake Data Type" + rand;
	}

	public TaskGeneric(int id, String description, String periodicity, String ataCategory, boolean hangarNeed, float duree, String planeType)
	{
		this.id = id;
		this.description = description;
		this.periodicity = periodicity;
		this.ataCategory = ataCategory;
		this.hangarNeed = hangarNeed;
		this.duree = duree;
		this.planeType = planeType;
	}

	public int getId()
	{
		return id;
	}

	public float getDuree()
	{
		return duree;
	}

	public String getPlaneType()
	{
		return planeType;
	}

	public boolean isHangarNeed()
	{
		return hangarNeed;
	}

	public String getDescription()
	{
		return description;
	}

	public String getPeriodicity()
	{
		return periodicity;
	}

	public String getAtaCategory()
	{
		return ataCategory;
	}

}
