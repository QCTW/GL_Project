package com.example.jetty_jersey.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.jetty_jersey.Dao.MRO;
import com.example.jetty_jersey.DaoInterface.MroDao;
import com.example.jetty_jersey.DaoInterfaceImpl.MroImpl;

@Path("/mro")
public class MroStub {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public List<MRO> allMro(){
		List<MRO> l = new ArrayList<MRO>();
		for (int i = 0; i < 10; i++) {
			l.add(new MRO());
		}
		return l;
		
	}
}
