package com.example.jetty_jersey.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.jetty_jersey.Dao.*;
import com.example.jetty_jersey.DaoInterface.FlightDao;
import com.example.jetty_jersey.DaoInterfaceImpl.FlightImpl;

@Path("/Flight")
public class FlightStub {
	
	FlightDao flight = new FlightImpl();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/view/{id}")
	public Flight flightById(@PathParam("id") int id){
		return flight.getFlightbyId(id+"");
	}
	

}
