package com.example.jetty_jersey.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.jetty_jersey.dao.DAO;
import com.example.jetty_jersey.dao.Plane;
import com.example.jetty_jersey.dao_interface.PlaneDao;

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
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addPlanes/{planes}")
	public void addPlanes(@PathParam("planes") String planes)
	{
		List<Plane> liste = new ArrayList<Plane>();
		String[] splitedFile;
		String[] splitedLine;
		try
		{
			splitedFile = planes.split(",");
			for (int i =0;i< splitedFile.length;i++)
			{
				splitedLine = splitedFile[i].split(",");
				if (splitedLine.length != 2)
				{
					System.out.println("Le fichier n'est pas dans le bon format!");
					
				}else
				{
					int id = Integer.parseInt(splitedLine[0]);
					String type = splitedLine[1];
					Plane p = new Plane(id, type);
					planeDao.addPlane(p);
				}

			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} 
	}
}
