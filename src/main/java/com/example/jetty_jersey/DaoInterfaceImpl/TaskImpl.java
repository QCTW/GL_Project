package com.example.jetty_jersey.DaoInterfaceImpl;

import java.util.ArrayList;
import java.util.HashMap;
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
	private final Map<String, Plane> planeCache = new HashMap<String, Plane>();
	private final Map<String, Flight> flightCache = new HashMap<String, Flight>();
	private final Map<String, MRO> mroCache = new HashMap<String, MRO>();

	public TaskImpl()
	{
	}

	public List<TaskInfo> getAllTasks()
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
		TaskInfo taskinfo = new TaskInfo(t, plane, flight, mro);
		return taskinfo;
	}

	public List<TaskInfo> getTasksByPlaneId(int id)
	{
		DatabaseConnecter dbConnect = new DatabaseConnecter();
		List<Map<String, String>> results = dbConnect.selectAllFromTableWhereFieldEqValue("task", "planeId", Integer.toString(id));
		List<TaskInfo> tl = new ArrayList<TaskInfo>();
		for (Map<String, String> m : results)
		{
			Task t = new Task(Utility.convertIntString(m.get("_id")), Utility.convertDateString(m.get("startTime")), Utility.convertDateString(m.get("endTime")), m.get("description"),
					m.get("periodicity"), m.get("ataCategory"), Utility.convertBoolString(m.get("hangarNeed")), Utility.convertIntString(m.get("planeId")),
					Utility.convertIntString(m.get("taskStatus")), Utility.convertIntString(m.get("mroId")));
			MRO mro = getMROById(dbConnect, m.get("mroId"));
			Plane p = getPlaneById(dbConnect, m.get("planeId"));
			Flight f = getFlightByPlaneId(dbConnect, m.get("planeId"));
			TaskInfo wrap = new TaskInfo(t, p, f, mro);
			tl.add(wrap);
			System.out.println(t.toString());

		}
		dbConnect.close();
		return tl;
	}

	public void addTask(Task t)
	{
		// TODO Auto-generated method stub

	}

	public void modifyTask(Task t)
	{
		// TODO Auto-generated method stub

	}

	public void deleteTask(int id)
	{
		// TODO Auto-generated method stub

	}

	public List<TaskInfo> getTasksInRange(int iStart, int iEnd)
	{
		DatabaseConnecter dbConnect = new DatabaseConnecter();
		List<Map<String, String>> results = dbConnect.selectInRangeFromTableName("task", iStart, iEnd);
		List<TaskInfo> tl = new ArrayList<TaskInfo>();
		for (Map<String, String> m : results)
		{
			Task t = new Task(Utility.convertIntString(m.get("_id")), Utility.convertDateString(m.get("startTime")), Utility.convertDateString(m.get("endTime")), m.get("description"),
					m.get("periodicity"), m.get("ataCategory"), Utility.convertBoolString(m.get("hangarNeed")), Utility.convertIntString(m.get("planeId")),
					Utility.convertIntString(m.get("taskStatus")), Utility.convertIntString(m.get("mroId")));
			MRO mro = getMROById(dbConnect, m.get("mroId"));
			Plane p = getPlaneById(dbConnect, m.get("planeId"));
			Flight f = getFlightByPlaneId(dbConnect, m.get("planeId"));
			TaskInfo wrap = new TaskInfo(t, p, f, mro);
			tl.add(wrap);
			System.out.println(t.toString());

		}
		dbConnect.close();
		return tl;
	}

	private MRO getMROById(DatabaseConnecter dbc, String id)
	{
		MRO m = mroCache.get(id);
		if (m == null)
		{
			List<Map<String, String>> res = dbc.selectAllFromTableWhereFieldEqValue("mro", "_id", id);
			if (res == null || res.size() <= 0)
				log.error("Unable to find MRO id : " + id + " in the database!");
			else
			{
				m = new MRO(Utility.convertIntString(id), res.get(0).get("name"));
				m.setQualification(res.get(0).get("qualification"));
				mroCache.put(id, m);
			}
		}
		return m;
	}

	private Plane getPlaneById(DatabaseConnecter dbc, String id)
	{
		Plane p = planeCache.get(id);
		if (p == null)
		{
			List<Map<String, String>> res = dbc.selectAllFromTableWhereFieldEqValue("plane", "_id", id);
			if (res == null || res.size() <= 0)
				log.error("Unable to find plane id : " + id + " in the database!");
			else
			{
				p = new Plane(Utility.convertIntString(id), res.get(0).get("planeType"));
				planeCache.put(id, p);
			}
		}
		return p;
	}

	private Flight getFlightByPlaneId(DatabaseConnecter dbc, String id)
	{
		Flight f = flightCache.get(id);
		if (f == null)
		{
			// TODO: Add the ordering into all the flights of the same planeId. To ensure to have the last active flight at position 0
			List<Map<String, String>> res = dbc.selectAllFromTableWhereFieldEqValue("flight", "planeId", id);
			if (res == null || res.size() <= 0)
				log.error("Unable to find flight id : " + id + " in the database!");
			else
			{
				Map<String, String> fst = res.get(0);
				f = new Flight(Utility.convertIntString(fst.get("_id")), fst.get("commercialId"), fst.get("departureAirport"), fst.get("arrivalAirport"),
						Utility.convertDateString(fst.get("departureTime")), Utility.convertDateString(fst.get("arrivalTime")), Utility.convertIntString(fst.get("planeId")));
				flightCache.put(id, f);
			}
		}
		return f;
	}

	// For unit test
	public static void main(String[] args)
	{
		TaskImpl test = new TaskImpl();
		test.getAllTasks();
	}

}
