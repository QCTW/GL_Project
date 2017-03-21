package com.example.jettey_jersey.util;

import java.util.ArrayList;
import java.util.List;

import com.example.jetty_jersey.Dao.Flight;
import com.example.jetty_jersey.Dao.Plane;

public class PlaneInfo {
	
	private Plane plane;
	private Flight flight ;
	
	public PlaneInfo(Plane plane, Flight flight) {
		this.plane = plane;
		this.flight =flight;
	}
	
	public Plane getPlane() {
		return plane;
	}

	public Flight getFligh() {
		return flight;
	}
	
	

}
