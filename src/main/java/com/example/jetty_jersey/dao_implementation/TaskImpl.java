package com.example.jetty_jersey.dao_implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jetty_jersey.util.TaskInfo;
import com.example.jetty_jersey.dao.Flight;
import com.example.jetty_jersey.dao.MRO;
import com.example.jetty_jersey.dao.Plane;
import com.example.jetty_jersey.dao.Status;
import com.example.jetty_jersey.dao.Task;
import com.example.jetty_jersey.dao_interface.TaskDao;
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
		List<TaskInfo> l = getTasksInRange(0, DatabaseSettings.MAX_RESULTS_PER_QUERY);
		return l;
	}

	public TaskInfo getTasksById(int id)
	{
		DatabaseConnecter dbConnect = new DatabaseConnecter();
		List<Map<String, String>> results = dbConnect.selectAllFromTableWhereFieldEqValue("task", "_id", Integer.toString(id));

		Map<String, String> m = results.get(0);
		Task t = new Task(Utility.convertIntString(m.get("_id")), Utility.convertDateString(m.get("startTime")), Utility.convertDateString(m.get("endTime")), m.get("description"),
				m.get("periodicity"), m.get("ataCategory"), Utility.convertBoolString(m.get("hangarNeed")), Utility.convertIntString(m.get("planeId")), Utility.convertIntString(m.get("taskStatus")),
				Utility.convertIntString(m.get("mroId")));
		MRO mro = getMROById(dbConnect, m.get("mroId"));
		Plane p = getPlaneById(dbConnect, m.get("planeId"));
		Flight f = getFlightByPlaneId(dbConnect, m.get("planeId"));
		TaskInfo wrap = new TaskInfo(t, p, f, mro);

		dbConnect.close();
		return wrap;
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

		}
		dbConnect.close();
		return tl;
	}

	public Status addTask(Task t)
	{
		DatabaseConnecter dbc = new DatabaseConnecter();
		Status s = dbc.insertToTableName("task", t.toMap());
		dbc.close();
		return s;
	}

	public Status modifyTask(Task t)
	{
		DatabaseConnecter dbc = new DatabaseConnecter();
		Status s = dbc.updateDataInTableNameWhereFieldEqValue("task", "_id", String.valueOf(t.getId()), t.toMap());
		dbc.close();
		return s;
	}

	public Status deleteTask(int id)
	{
		DatabaseConnecter dbc = new DatabaseConnecter();
		Status deletedStatus = dbc.deleteAllFromTableNameWhereFieldEqValue("task", "_id", String.valueOf(id));
		dbc.close();
		return deletedStatus;
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
		}
		dbConnect.close();
		return tl;
	}

	private MRO getMROById(DatabaseConnecter dbc, String id)
	{
		if (id == null || id.length() == 0)
			return new MRO(-1, "Not assigned");
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
				p = new Plane(Utility.convertIntString(id), res.get(0).get("planetype"));
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
		Task t = new Task(-1, Utility.convertDateString("23/04/2017"), Utility.convertDateString("24/04/2017"),
				"3130   Data Recorders (Flight/Maintenance) : The unit which continuously records critical flight, aircraft and powerplant system data, such as attitude, air speed, altitude, engine power, etc., to be used in the event of a crash. Includes the system and parts that provide a source of power and inputs, from various sources critical to flight, to flight data recorder. Typical parts are spool rod, magazine, etc.",
				"Each flight", "Aircraft General (ATA 00-18)", false, 3, 9, 1);
		Status s = test.addTask(t);
		System.out.println("Add new task by _id=-1 : " + s.toString());
		test.getAllTasks();
		s = test.deleteTask(3);
		System.out.println("Delete task _id=3 : " + s.toString());
		t = new Task(3, Utility.convertDateString("23/05/2017"), Utility.convertDateString("24/05/2017"),
				"3130   Data Recorders (Flight/Maintenance) : The unit which continuously records critical flight, aircraft and powerplant system data, such as attitude, air speed, altitude, engine power, etc., to be used in the event of a crash. Includes the system and parts that provide a source of power and inputs, from various sources critical to flight, to flight data recorder. Typical parts are spool rod, magazine, etc.",
				"Each flight", "Aircraft General (ATA 00-18)", false, 3, 5, 1);
		s = test.addTask(t);
		System.out.println("Add back task _id=3 : " + s.toString());

		TaskInfo ti = test.getTasksById(1);
		System.out.println(ti.toString());
	}

}
