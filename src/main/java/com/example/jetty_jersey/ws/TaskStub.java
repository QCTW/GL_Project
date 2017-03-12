package com.example.jetty_jersey.ws;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.example.jetty_jersey.Dao.*;
import com.example.jetty_jersey.DaoInterface.TaskDao;
import com.example.jetty_jersey.DaoInterfaceImpl.TaskImpl;

@Path("/task")
public class TaskStub {
	
	static TaskDao taskList = new TaskImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public List<Task> allTasks(){
		//return taskList.getAllTasks();
		List<Task> tl  = new ArrayList<>();
		Task t;
		for(int i =0; i<10; i++){
			t = new Task(new Date(), new Date(), "description"+i, "periodicity"+i, "ata"+i, true);
			tl.add(t);
		}
		return tl;
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/planes//{id}")
	public List<Task> tasksByPlaneId(@PathParam("id") int id){
		//return taskList.getAllTasks();
		List<Task> tl  = new ArrayList<>();
		Task t;
		for(int i =0; i<10; i++){
			t = new Task(new Date(), new Date(), "description"+i, "periodicity"+i, "ata"+i, true);
			tl.add(t);
		}
		return tl;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/user/{id}")
	public List<Task> allTasksFromUser(@PathParam("id") int id){
		return new ArrayList<Task>();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Task taskById(@PathParam("id") int id){
		return taskList.getTasksById(id+"");
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/add")
	public void addTask(){

	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/modify/{id}")
	public void modifyTaskById(@PathParam("id") int id){
		
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update/{id}")
	public void updateTaskById(@PathParam("id") int id){
		
	}
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete/{id}")
	public void deleteTaskById(@PathParam("id") int id){
		
	}
	
}
