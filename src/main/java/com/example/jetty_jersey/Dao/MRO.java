package com.example.jetty_jersey.Dao;

/**
 * 
 * PUT mro/id
 * {
 * 
 * "qualification" : "example of qualification"
 * "name" : "example of name"
 * "surname" : "example of surname"
 * }
 * 
 */

public class MRO
{

	private String qualification = "N/A";
	private String name;
	private final int id;

	public MRO(String name, int mroId)
	{
		this.name = name;
		this.id = mroId;
	}

	public String getQualification()
	{
		return qualification;
	}

	public void setQualification(String qualification)
	{
		this.qualification = qualification;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getId()
	{
		return id;
	}

}
