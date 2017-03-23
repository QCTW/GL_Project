package com.example.jetty_jersey.Dao;

/**
 
 PUT mro/id
 {
 
 "qualification" : "example of qualification"
 "name" : "example of name"
 "surname" : "example of surname"
 }

*/

public class MRO{
	
	private String qualification;
	private String name;
	//private String surname;
	private int id;
	
	public MRO() {
		id = (int)(Math.random()*100);
		qualification = "qualif"+id;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	


	
}
