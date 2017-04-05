package com.example.jetty_jersey.DaoInterfaceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jetty_jersey.util.TaskInfo;
import com.example.jetty_jersey.Dao.Flight;
import com.example.jetty_jersey.Dao.MRO;
import com.example.jetty_jersey.Dao.Plane;
import com.example.jetty_jersey.Dao.Task;
import com.example.jetty_jersey.DaoInterface.PlaneDao;
import com.example.jetty_jersey.DaoInterface.TaskDao;
import com.example.jetty_jersey.db.DatabaseConnecter;
import com.example.jetty_jersey.db.DatabaseSettings;
import com.example.jetty_jersey.db.Utility;

public class TaskImpl implements TaskDao
{
	private static Logger log = LogManager.getLogger(TaskImpl.class.getName());

	public TaskImpl()
	{
	}

	public List<Task> getAllTasks()
	{
		return getTasksInRange(0, DatabaseSettings.MAX_RESULTS_PER_QUERY);
	}

	public TaskInfo getTasksById(int id)
	{
		PlaneDao p = new PlaneImpl();
		Task t = new Task(id, 2);
		Plane plane = p.getPlanebyId(t.getPlaneId());
		Flight flight = new Flight(1, plane.getPlaneId());
		MRO mro = new MRO();
		TaskInfo taskinfo = new TaskInfo(plane, flight, mro);
		taskinfo.addTask(t);
		return taskinfo;
	}

	public TaskInfo getTasksByPlaneId(int id)
	{
		double x = Math.random();
		String s = (x < 0.3) ? "airbus" : (x < 0.6) ? "cessna" : "boeing";
		Plane plane = new Plane(id, s);
		Flight flight = new Flight(1, id);

		TaskInfo taskinfo = new TaskInfo(plane, flight, null);
		Task t;
		for (int i = 0; i < 10; i++)
		{
			t = new Task(i, id);
			taskinfo.addTask(t);
		}
		return taskinfo;
	}

	public void addTask(Task t)
	{
		// TODO Auto-generated method stub

	}

	public void modifyTask(Task t)
	{
		// TODO Auto-generated method stub

	}

	public void addTask(int id)
	{
		// TODO Auto-generated method stub

	}

	public List<Task> getTasksInRange(int iStart, int iEnd)
	{
		DatabaseConnecter dbConnect = new DatabaseConnecter();
		List<Map<String, String>> results = dbConnect.selectInRangeFromTableName("task", iStart, iEnd);
		List<Task> tl = new ArrayList<Task>();
		for (Map<String, String> m : results)
		{
			Task t = new Task(Utility.convertIntString(m.get("id")), Utility.convertDateString(m.get("startTime")), Utility.convertDateString(m.get("endTime")), m.get("description"),
					m.get("periodicity"), m.get("ataCategory"), Utility.convertBoolString(m.get("hangarNeed")), Utility.convertIntString(m.get("planeId")),
					Utility.convertIntString(m.get("taskStatus")), Utility.convertIntString(m.get("mroId")));
			tl.add(t);
			System.out.println(t.toString());
		}
		dbConnect.close();
		return tl;
	}

	// For test only
	public static void main(String[] args)
	{
		TaskImpl test = new TaskImpl();
		test.getAllTasks();
	}

}
