package com.example.jetty_jersey.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.jetty_jersey.Dao.MRO;
import com.example.jetty_jersey.Dao.Plane;
import com.example.jetty_jersey.DaoInterface.MroDao;

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
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addMRO/{mro}")
	public void addMRO(@PathParam("mro") String mro)
	{
				String[] splitedFile;
		String[] splitedLine;
		try
		{
			splitedFile = mro.split(",");
			for (int i =0;i< splitedFile.length;i++)
			{
				splitedLine = splitedFile[i].split(",");
				if (splitedLine.length != 2)
				{
					System.out.println("Le fichier n'est pas dans le bon format!");
					
				}else
				{
					int id = Integer.parseInt(splitedLine[0]);
					String nom = splitedLine[1];
					MRO m = new MRO(id, nom);
					
				}

			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} 

	}
	
}
