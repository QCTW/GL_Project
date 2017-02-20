package com.example.jetty_jersey.ws;

import com.example.jetty_jersey.Dao.*;
import javax.ws.rs.Path;

@Path("/ata")
public class AtaStub {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/view/{id}")
	public String getAtaById(@PathParam("id") int id){
		return new String();
	}

}
