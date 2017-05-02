package com.example.jetty_jersey.dao;

/*
 PUT plane/id
 {
 "planetype" : "exmaple of plane type"
 }

*/
public class Plane
{
	private final int planeId;
	private String planeType;

	public Plane(int planeId, String planeType)
	{
		super();
		this.planeId = planeId;
		this.planeType = planeType;
	}

	public int getPlaneId()
	{
		return planeId;
	}

	public String getPlaneType()
	{
		return planeType;
	}

	public void setPlaneType(String planeType)
	{
		this.planeType = planeType;
	}

	@Override
	public String toString()
	{
		return "Plane(" + planeId + "," + planeType + ")";
	}

}
