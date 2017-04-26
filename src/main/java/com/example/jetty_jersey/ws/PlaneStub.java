package com.example.jetty_jersey.ws;

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
	private static PlaneDao planeDao = DAO.getPlaneDao();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public List<Plane> allPlanes()
	{
		return planeDao.getAllPlanes();
	}
}
