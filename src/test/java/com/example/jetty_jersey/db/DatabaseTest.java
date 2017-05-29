package com.example.jetty_jersey.db;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

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
	public void testDatabaseFunction()
	{
		DatabaseConnecter dbc = new DatabaseConnecter();
		String id = dbc.generateNewId("mro");
		assertEquals(id, "10");

		Map<String, String> data = new CustomHashMap<String, String>();
		data.put("qualification", "JUnit test qualification");
		data.put("name", "JUnit");
		data.put("surname", "Test");
		data.put("email", "test@gmail.com");
		dbc.insertToTableName("mro", data);
		// dbc.deleteAllFromTableNameWhereFieldEqValue(tableName, fieldName, fieldValue);
		// dbc.updateDataInTableNameWhereFieldEqValue(tableName, fieldName, fieldValue, data);

		//
		// dbc.selectAllFromTableName(tableName);
		//
		// dbc.selectAllFromTableWhereFieldEqValue(tableName, fieldName, fieldValue);
		//
		// dbc.selectAllFromTableWhereFieldEqValueSortAscendingByField(tableName, fieldName, fieldValue, sortField);
		//
		// dbc.selectAllFromTableWhereFieldEqValueSortDescendingByField(tableName, fieldName, fieldValue, sortField);
		//
		// dbc.getIdInTableNameWhereFieldEqValue(tableName, fieldName, fieldValue);
		dbc.close();
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

		Task newTask = tInfoList.get(0).task;
		s = test.modifyTask(new Task(newTask.getId(), 3, newTask.getStartTime(), newTask.getEndTime(), newTask.getPlaneId(), newTask.getTaskStatus(), newTask.getMccId(), newTask.getMroId()));
		assertTrue(s.getExecutionStatus() == Status.Execution.SUCCESSFUL);

		s = test.deleteTask(newTask.getId());
		assertTrue(s.getExecutionStatus() == Status.Execution.SUCCESSFUL);
		if (s.getExecutionStatus() != Status.Execution.SUCCESSFUL)
			fail("Unable to delete newly added task into database.");

	}

}
