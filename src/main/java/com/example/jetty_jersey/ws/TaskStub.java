package com.example.jetty_jersey.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.Base64;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jetty_jersey.util.TaskInfo;
import com.example.jetty_jersey.dao.*;
import com.example.jetty_jersey.dao_implementation.TaskGenericImpl;
import com.example.jetty_jersey.dao_interface.TaskGenericDao;
import com.example.jetty_jersey.db.Utility;

@Path("/task")
public class TaskStub
{
	private static Logger log = LogManager.getLogger(TaskStub.class.getName());
	private  TaskGenericDao taskGeneric= new TaskGenericImpl();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public List<TaskInfo> allTasks()
	{
		if (LoginStub.connected)
		{
			log.debug("Login connected : " + LoginStub.connected);
			return DAO.getTaskDao().getAllTasks();
		} else
			return new ArrayList<TaskInfo>();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/genericByPlane/{type}")
	public List<TaskGeneric> getGenericTasksByPlaneType(@PathParam("type") String type)
	{
		LoginStub.connected = true;
		if (LoginStub.connected)
		{
			log.debug("Login connected : " + LoginStub.connected);
			return DAO.getTaskGenericDao().getGenericTasksByPlaneType(type);

		} else
			return new ArrayList<TaskGeneric>();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/plane/{id}")
	public List<TaskInfo> allTasksByPlaneId(@PathParam("id") int id)
	{
		if (LoginStub.connected)
		{
			return DAO.getTaskDao().getTasksByPlaneId(id);
		}
		return new ArrayList<TaskInfo>();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public TaskInfo taskById(@PathParam("id") int id)
	{
		if (LoginStub.connected)
		{
			return DAO.getTaskDao().getTasksById(id);
		}
		return null;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public void addTask(Task task)
	{
		DAO.getTaskDao().addTask(task);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/test")
	public void test()
	{
		Task t = new Task(-1, 9 / 5, "2017/05/07 00:00", "2017/05/07 16:41", 5, 1, -1, -1);
		// Task tt = new Task(-1, 5, startTime, endTime, planeId, taskStatus, mroId, mccId)
		DAO.getTaskDao().addTask(t);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	// http://stackoverflow.com/questions/8194408/how-to-access-parameters-in-a-restful-post-method
	public void modifyTask(Task task)
	{
		DAO.getTaskDao().modifyTask(task);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete/{id}")
	public void deleteTaskById(@PathParam("id") int id)
	{
		DAO.getTaskDao().deleteTask(id);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addTasks/{tasks}")
	public void addTasks(@PathParam("task") String task)
	{
		String[] splitedFile;
		String[] splitedLine;
		try
		{
			splitedFile = task.split("\n");
			for (int i = 0; i < splitedFile.length; i++)
			{
				splitedLine = splitedFile[i].split(",");
				if (splitedLine.length != 10)
				{
					log.error("Le fichier n'est pas dans le bon format!");
				} else
				{
					int id = Integer.parseInt(splitedLine[0]);
					String starTime = splitedLine[1];
					String endTime = splitedLine[2];
					String description = splitedLine[3];
					String periodicity = splitedLine[4];
					String ataCategory = splitedLine[5];
					boolean needHangar = Utility.convertBoolString(splitedLine[6]);
					int planeId = Integer.parseInt(splitedLine[7]);
					int statut = Integer.parseInt(splitedLine[8]);
					int mroId = Integer.parseInt(splitedLine[9]);
					// TODO : This web service is not finished
				}

			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addGenericTasks/{tasks}")
	public void addGenericTasks(@PathParam("task") String task)
	{
		String[] splitedFile;
		String[] splitedLine;
		try
		{	byte[] decoded = Base64.getDecoder().decode(task); 
			String tasks = new String(decoded);
			splitedFile = tasks.split("\n");
			for (int i = 0; i < splitedFile.length; i++)
			{
				splitedLine = splitedFile[i].split(",");
				if (splitedLine.length != 7)
				{
					log.error("Le fichier n'est pas dans le bon format!");
				} else
				{
					int id = Integer.parseInt(splitedLine[0]);
					String description = splitedLine[1];
					String periodicity = splitedLine[2];
					String ataCategory = splitedLine[3];
					boolean needHangar = Utility.convertBoolString(splitedLine[4]);
					float duration = Integer.parseInt(splitedLine[5]);
					String typeAvion = splitedLine[6];
					TaskGeneric tg = new TaskGeneric(id,description,
							periodicity,ataCategory,needHangar,duration,
							typeAvion);
					taskGeneric.addTaskGeneric(tg);
					
				}

			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
