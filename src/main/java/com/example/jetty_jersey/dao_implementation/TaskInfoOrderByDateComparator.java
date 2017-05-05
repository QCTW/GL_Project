package com.example.jetty_jersey.dao_implementation;

import java.util.Comparator;
import java.util.Date;

import com.example.jetty_jersey.util.TaskInfo;

public class TaskInfoOrderByDateComparator implements Comparator<TaskInfo>
{
	private final Date dateNow = new Date();

	public int compare(TaskInfo ti1, TaskInfo ti2)
	{
		// TODO
		return 0;
	}

}
