package com.example.jetty_jersey.ws;

import javax.ws.rs.Path;

import com.example.jetty_jersey.Dao.*;

@Path("/login")
public class LoginStub {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{username}/{id}")
	public MRO getUser(@PathParam("username") String username,
			@PathParam("id") int id){
		return new MRO();
	}
}
