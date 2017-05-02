package com.example.jetty_jersey.util;

import com.example.jetty_jersey.dao.Flight;
import com.example.jetty_jersey.dao.MRO;
import com.example.jetty_jersey.dao.Plane;
import com.example.jetty_jersey.dao.Task;

public class TaskInfo
{

	public Task task;
	public Plane plane;
	public Flight flight;
	public MRO mro; // To check with Mohamad if MRO should be here or in the Task

	public TaskInfo(Task task, Plane plane, Flight flight, MRO mro)
	{
		this.task = task;
		this.plane = plane;
		this.flight = flight;
		this.mro = mro;
	}

	@Override
	public String toString()
	{
		return "TaskInfo [task=" + task + ", plane=" + plane + ", flight=" + flight + ", mro=" + mro + "]";
	}

}
