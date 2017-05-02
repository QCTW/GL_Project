package com.example.jetty_jersey.dao_interface;

import java.util.List;

import com.example.jetty_jersey.util.TaskInfo;
import com.example.jetty_jersey.dao.*;

public interface TaskDao
{

	/**
	 * @return the list of all
	 **/
	List<TaskInfo> getAllTasks();

	/**
	 * @return the list of all
	 **/
	List<TaskInfo> getTasksInRange(int nStart, int nEnd);

	/**
	 * @return the task with the given id
	 **/
	TaskInfo getTasksById(int id);

	/**
	 * @return the task with the given plane id
	 **/
	List<TaskInfo> getTasksByPlaneId(int id);

	/**
	 * @return add a task
	 **/
	Status addTask(Task t);

	/**
	 * @return modify a task
	 **/
	Status modifyTask(Task t);

	/**
	 * @return delete a task
	 **/
	Status deleteTask(int taskId);

}