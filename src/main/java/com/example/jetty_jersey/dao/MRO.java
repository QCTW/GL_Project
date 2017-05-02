package com.example.jetty_jersey.dao;

import com.example.jetty_jersey.db.CustomHashMap;

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
	private int id;

	
	public MRO()
	{
		this.id = -1;
	}

	public MRO(int mroId, String name)
	{
		this.name = name;
		this.id = mroId;
	}
	
	public MRO(String name){
		this.name=name;
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

	public CustomHashMap<String, String> toMap()
	{
		CustomHashMap<String, String> chm = new CustomHashMap<String, String>();
		chm.put("_id", String.valueOf(id));
		chm.put("name", name);
		chm.put("qualification", qualification);

		return chm;
	}
	
	@Override
	public String toString()
	{
		return "MRO(" + id + "," + name + "," + qualification + ")";
	}

}
