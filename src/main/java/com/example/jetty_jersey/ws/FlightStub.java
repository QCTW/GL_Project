package com.example.jetty_jersey.ws;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.jetty_jersey.dao.*;
import com.example.jetty_jersey.dao_interface.FlightDao;
import com.example.jetty_jersey.dao_implementation.FlightImpl;

@Path("/Flight")
public class FlightStub {

	FlightDao flight = new FlightImpl();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/view/{id}")
	public Flight flightById(@PathParam("id") int id) {
		return flight.getFlightbyId(id + "");
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/view/{flights}")
	public void addFlights(@PathParam("flights") String flights) {
		String[] splitedFile;
		String[] splitedLine;

		splitedFile = flights.split("\n");
		for (int i = 0; i < splitedFile.length; i++) {
			splitedLine = splitedFile[i].split(",");
			if (splitedLine.length != 2) {
				System.out.println("Le fichier n'est pas dans le bon format!");
			}
			int id = Integer.parseInt(splitedLine[0]);
			int planeId = Integer.parseInt(splitedLine[1]);
			Flight f = new Flight(id, planeId);

		}

	}

}
