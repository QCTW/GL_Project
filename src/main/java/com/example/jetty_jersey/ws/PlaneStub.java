package com.example.jetty_jersey.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.jetty_jersey.Dao.DAO;
import com.example.jetty_jersey.Dao.Plane;
import com.example.jetty_jersey.DaoInterface.PlaneDao;

@Path("/plane")
public class PlaneStub
{
	private static PlaneDao taskDao = DAO.getPlaneDao();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public List<Plane> allPlanes()
	{
		List<Plane> l = new ArrayList<Plane>();
		double x;
		String s;
		for (int i = 0; i < 50; i++)
		{
			x = Math.random();
			s = (x < 0.3) ? "airbus" : (x < 0.6) ? "cessna" : "boeing";
			l.add(new Plane(i, s));
		}
		return l;
	}
}
