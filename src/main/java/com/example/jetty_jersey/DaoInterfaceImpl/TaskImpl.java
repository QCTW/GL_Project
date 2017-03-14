package com.example.jetty_jersey.DaoInterfaceImpl;

import java.util.ArrayList;
import java.util.List;

import com.example.jettey_jersey.util.TaskInfo;
import com.example.jetty_jersey.Dao.Flight;
import com.example.jetty_jersey.Dao.Plane;
import com.example.jetty_jersey.Dao.Task;
import com.example.jetty_jersey.DaoInterface.PlaneDao;
import com.example.jetty_jersey.DaoInterface.TaskDao;

public class TaskImpl implements TaskDao {

	
	public TaskImpl() {
		// TODO Auto-generated constructor stub
	}

	public List<Task> getAllTasks() {
		List<Task> tl  = new ArrayList<Task>();
		Task t;
		int x;
		for(int i =0; i<100; i++){
			x = (int)(Math.random() *3) +1;
			t = new Task(i,x);
			tl.add(t);
		}
		return tl;
	}

	
	public TaskInfo getTasksById(int id) {
		PlaneDao p = new PlaneImpl();
		Task t = new Task(id,2);
		Plane plane = p.getPlanebyId(t.getPlaneId());
		Flight flight = new Flight(1, plane.getPlaneId());
		TaskInfo taskinfo = new TaskInfo(plane, flight);
		taskinfo.addTask(t);
		return taskinfo;
	}


	public TaskInfo getTasksByPlaneId(int id) {
		double x = Math.random();
		String s = (x<0.3)?"airbus":(x<0.6)?"cessna":"boeing";
		Plane plane = new Plane(id,s);
		Flight flight = new Flight(1, id);
		
		TaskInfo taskinfo = new TaskInfo(plane, flight);
		List<Task> tl  = new ArrayList<Task>();
		Task t;
		for(int i =0; i<10; i++){
			t = new Task(i,id);
			taskinfo.addTask(t);
		}
		return taskinfo;
	}
	
	public void addTask(Task t) {
		// TODO Auto-generated method stub
		
	}
	
	public void modifyTask(Task t) {
		// TODO Auto-generated method stub
		
	}

	public void addTask(int id) {
		// TODO Auto-generated method stub
		
	}

	

}
