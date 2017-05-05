package com.example.jetty_jersey.util;

import java.util.ArrayList;
import java.util.List;

import com.example.jetty_jersey.dao.Flight;
import com.example.jetty_jersey.dao.Plane;

public class PlaneInfo
{
	private final Plane plane;
	private final List<Flight> flight;
	private int numberOfFlights;

	public PlaneInfo(Plane plane, List<Flight> flight)
	{
		this.plane = plane;
		this.flight = flight;
	}

	public Plane getPlane()
	{
		return plane;
	}

	public List<Flight> getFlighs()
	{
		return new ArrayList();
	}

}
