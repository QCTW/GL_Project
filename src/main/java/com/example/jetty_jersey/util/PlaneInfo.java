package com.example.jetty_jersey.util;

import com.example.jetty_jersey.Dao.Flight;
import com.example.jetty_jersey.Dao.Plane;

public class PlaneInfo
{

	private final Plane plane;
	private final Flight flight;

	public PlaneInfo(Plane plane, Flight flight)
	{
		this.plane = plane;
		this.flight = flight;
	}

	public Plane getPlane()
	{
		return plane;
	}

	public Flight getFligh()
	{
		return flight;
	}

}
