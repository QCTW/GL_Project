package com.example.jetty_jersey.ws;

import java.security.Timestamp;
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
	
	TaskDao taskList = new TaskImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public List<Task> allTasks(){
		//taskList.getAllTasks();
		List<Task> t = new ArrayList<Task>();
		Task task = null;
		for(int i=0; i<10; i++){
			task = new Task(new Date(), new Date(), "description"+i, "periodicity"+i, "ata"+i, true);
			t.add(task);
		}
		
		return t;
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
		return new Task();
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
