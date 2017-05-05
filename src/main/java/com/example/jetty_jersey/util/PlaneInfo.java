package com.example.jetty_jersey.util;

import java.util.List;

import com.example.jetty_jersey.dao.Flight;
import com.example.jetty_jersey.dao.Plane;

public class PlaneInfo
{
	private final Plane plane;
	private final List<Flight> flights;

	public PlaneInfo(Plane plane, List<Flight> flights)
	{
		this.plane = plane;
		this.flights = flights;
	}

	public Plane getPlane()
	{
		return plane;
	}

	public List<Flight> getFlighs()
	{
		return flights;
	}

}
