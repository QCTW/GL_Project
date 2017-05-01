package com.example.jetty_jersey.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.jetty_jersey.Dao.MRO;

@Path("/mro")
public class MroStub
{

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public List<MRO> allMro()
	{
		List<MRO> l = new ArrayList<MRO>();
		for (int i = 0; i < 50; i++)
		{
			l.add(new MRO(i,"mro"+i));
		}
		return l;

	}
}
