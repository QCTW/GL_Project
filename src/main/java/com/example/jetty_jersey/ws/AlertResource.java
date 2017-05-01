package com.example.jetty_jersey.ws;


import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/alert")
public class AlertResource {
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Flight")
	public void addAlert(){
	System.out.println("Add an alert for missing plane and crew");
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("Flight/{id}")
	public void deleteAlert(@PathParam("id")long id){
		
		System.out.println("delete an alert for missing plane and crew");
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/aircraft")
	public void addAlertOFP(){
		
		
		
		System.out.println("Add an alert for missing aircraft");
	}
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("aircraft/{id}")
	public void deleteAlertAircraft(@PathParam("id")long id){
		
		System.out.println("delete an alert for missing aircraft="+id);
	}
	
	
}
