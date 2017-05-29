package com.example.jetty_jersey.db;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.example.jetty_jersey.dao.Status;
import com.example.jetty_jersey.dao.Task;
import com.example.jetty_jersey.dao_implementation.TaskImpl;
import com.example.jetty_jersey.util.TaskInfo;

public class DatabaseTest
{

	@Before
	public void initialize()
	{
		DatabaseSimulator.execute();
	}

	@Test
	public void testTaskImpl()
	{
		TaskImpl test = new TaskImpl();
		Task t = new Task(-1, 1, "2017/05/02 19:15", "2017/05/02 23:15", 99, 3, 1, 1);
		List<TaskInfo> l1 = test.getAllTasks();

		Status s = test.addTask(t);
		List<TaskInfo> l2 = test.getAllTasks();
		assertTrue(s.getExecutionStatus() == Status.Execution.SUCCESSFUL && (l1.size() + 1) == l2.size());

		List<TaskInfo> tInfoList = test.getTasksByPlaneId(99);
		if (tInfoList.size() == 0)
			fail("Unable to find newly added task into database.");
		s = test.deleteTask(tInfoList.get(0).task.getId());
		assertTrue(s.getExecutionStatus() == Status.Execution.SUCCESSFUL);
		if (s.getExecutionStatus() != Status.Execution.SUCCESSFUL)
			fail("Unable to delete newly added task into database.");

	}

}
