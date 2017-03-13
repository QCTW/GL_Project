package com.example.jetty_jersey.ws;

import java.util.List;

import com.example.jetty_jersey.Dao.Flight;
import com.example.jetty_jersey.Dao.Plane;
import com.example.jetty_jersey.Dao.Task;

public class TaskInfo {
	
	List<Task> tasklist ;
	Plane plane;
	Flight flight;
	
	public TaskInfo(List<Task> tasklist, Plane plane, Flight flight) {
		super();
		this.tasklist = tasklist;
		this.plane = plane;
		this.flight = flight;
	}
	
	

}
