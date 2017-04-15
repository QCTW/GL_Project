package com.example.jetty_jersey.Dao;

/**
 * 
 * PUT mro/id
 * {
 * 
 * "qualification" : "example of qualification"
 * "name" : "example of name"
 * }
 * 
 */

public class MRO
{

	private String qualification = "N/A";
	private String name = "Not assigned";
	private final int id;

	public MRO()
	{
		this.id = -1;
	}

	public MRO(int mroId, String name)
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

	@Override
	public String toString()
	{
		return "MRO(" + id + "," + name + "," + qualification + ")";
	}

}
