package com.example.jetty_jersey.ws;

import javax.ws.rs.Path;
import com.example.jetty_jersey.Dao.*;

@Path("/Flight")
public class FlightStub {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/view/{id}")
	public Flight flightById(@PathParam("id") int id){
		return new Flight();
}

}
