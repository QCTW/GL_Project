package com.example.jetty_jersey.Dao;
/*
 PUT plane/id
 {
 "planetype" : "exmaple of plane type"
 }

*/
public class Plane{
	private int planeId;
	private String planeType;
	public Plane(int planeId, String planeType) {
		super();
		this.planeId = planeId;
		this.planeType = planeType;
	}
	public int getPlaneId() {
		return planeId;
	}
	public void setPlaneId(int planeId) {
		this.planeId = planeId;
	}
	public String getPlaneType() {
		return planeType;
	}
	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	} 
	
	
	
	
	
}
