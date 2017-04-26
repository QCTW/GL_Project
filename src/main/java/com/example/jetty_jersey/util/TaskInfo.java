package com.example.jetty_jersey.util;

import com.example.jetty_jersey.Dao.Flight;
import com.example.jetty_jersey.Dao.MRO;
import com.example.jetty_jersey.Dao.Plane;
import com.example.jetty_jersey.Dao.Task;

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
