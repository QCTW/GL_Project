package com.example.jetty_jersey.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.jetty_jersey.util.TaskInfo;
import com.example.jetty_jersey.Dao.*;
import com.example.jetty_jersey.DaoInterface.TaskDao;
import com.example.jetty_jersey.DaoInterfaceImpl.TaskImpl;

@Path("/task")
public class TaskStub
{

	static TaskDao taskList = new TaskImpl();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public List<TaskInfo> allTasks()
	{
		return DAO.getTaskDao().getAllTasks();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all2")
	public List<TaskInfo> allTasks2()
	{
		List<TaskInfo> l = new ArrayList<TaskInfo>();
		for (int i = 0; i < 10; i++)
		{
			l.addAll(DAO.getTaskDao().getTasksByPlaneId(i));
		}
		return l;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/plane/{id}")
	public List<TaskInfo> allTasksByPlaneId(@PathParam("id") int id)
	{
		return taskList.getTasksByPlaneId(id);
		/*
		 * Plane plane = new Plane(id,"airbus");
		 * Flight flight = new Flight(1, id);
		 * 
		 * TaskInfo taskinfo = new TaskInfo(plane, flight);
		 * List<Task> tl = new ArrayList<>();
		 * Task t;
		 * for(int i =0; i<10; i++){
		 * t = new Task(i,id);
		 * taskinfo.addTask(t);
		 * }
		 * return taskinfo;
		 */
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public TaskInfo taskById(@PathParam("id") int id)
	{
		return taskList.getTasksById(id);/*
											 * Plane plane = new Plane(2,"airbus");
											 * Flight flight = new Flight(1, 2);
											 * Task t = new Task(id,2);
											 * TaskInfo taskinfo = new TaskInfo(plane, flight);
											 * taskinfo.addTask(t);
											 * return taskinfo;
											 */
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public void addTask(TaskInfo taskInfo)
	{

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/modify/{id}")
	public void modifyTaskById(@PathParam("id") int id)
	{

	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update/{id}")
	public void updateTaskById(@PathParam("id") int id)
	{

	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete/{id}")
	public void deleteTaskById(@PathParam("id") int id)
	{

	}

}
