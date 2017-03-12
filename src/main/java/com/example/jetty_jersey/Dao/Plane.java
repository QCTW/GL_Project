package com.example.jetty_jersey.Dao;
/*
 PUT plane/id
 {
 "planetype" : "exmaple of plane type"
 }

*/
public class Plane{
	private String planeId;
	private String planeType; 
	
	public String getPlaneId() {
		System.out.println("hello");
		return planeId;
	}
	public void setPlaneId(String planeId) {
		this.planeId = planeId;
	}
	public String getPlaneType() {
		return planeType;
	}
	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}
	
	
}
