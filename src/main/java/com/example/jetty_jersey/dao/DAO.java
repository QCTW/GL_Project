package com.example.jetty_jersey.Dao;

import com.example.jetty_jersey.DaoInterface.FlightDao;
import com.example.jetty_jersey.DaoInterface.PlaneDao;
import com.example.jetty_jersey.DaoInterface.TaskDao;
import com.example.jetty_jersey.DaoInterfaceImpl.*;

public class DAO
{
	private static TaskDao t;
	private static FlightDao f;
	private static PlaneDao p;

	public static FlightDao getFlightDao()
	{
		if (f == null)
		{
			f = new FlightImpl();
		}
		return f;
	}

	public static TaskDao getTaskDao()
	{
		if (t == null)
		{
			t = new TaskImpl();
		}
		return t;
	}

	public static PlaneDao getPlaneDao()
	{
		if (p == null)
		{
			p = new PlaneImpl();
		}
		return p;
	}

}
