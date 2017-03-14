package com.example.jettey_jersey.util;

import java.util.ArrayList;
import java.util.List;

import com.example.jetty_jersey.Dao.Flight;
import com.example.jetty_jersey.Dao.Plane;
import com.example.jetty_jersey.Dao.Task;

public class TaskInfo {
	
	public List<Task> tasklist ;
	public Plane plane;
	public Flight flight;
	
	public TaskInfo(Plane plane, Flight flight) {
		super();
		this.tasklist = new ArrayList<Task>();
		this.plane = plane;
		this.flight = flight;
	}
	
	public void addTask(Task t){
		tasklist.add(t);
	}
	
	

}
