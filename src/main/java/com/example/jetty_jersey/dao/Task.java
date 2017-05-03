package com.example.jetty_jersey.dao;

import java.util.Date;

import com.example.jetty_jersey.db.CustomHashMap;
import com.example.jetty_jersey.db.Utility;

public class Task
{
	private final int id;
	private final int idTaskGeneric;
	private Date startTime;
	private Date endTime;
	private int planeId;
	private int taskStatus; // Pas distribue = 1, En cour =2, Effactu = 3
	private int mroId;

	public Task(int id, int idTaskGeneric, Date startTime, Date endTime, int planeId, int taskStatus, int mroId)
	{
		this.id = id;
		this.idTaskGeneric = idTaskGeneric;
		this.startTime = startTime;
		this.endTime = endTime;
		this.planeId = planeId;
		this.taskStatus = taskStatus;
		this.mroId = mroId;
	}

	public CustomHashMap<String, String> toMap()
	{
		CustomHashMap<String, String> chm = new CustomHashMap<String, String>();
		chm.put("_id", String.valueOf(id));
		chm.put("startTime", Utility.convertDateToString(startTime));
		chm.put("endTime", Utility.convertDateToString(endTime));
		// chm.put("description", description);
		// chm.put("periodicity", periodicity);
		// chm.put("ataCategory", ataCategory);
		// chm.put("hangarNeed", Boolean.toString(hangarNeed));
		chm.put("planeId", String.valueOf(planeId));
		chm.put("taskStatus", String.valueOf(taskStatus));
		chm.put("mroId", String.valueOf(mroId));

		return chm;
	}

	public int getId()
	{
		return id;
	}

	public int getIdTaskGeneric()
	{
		return idTaskGeneric;
	}

	public Date getStartTime()
	{
		return startTime;
	}

	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	public Date getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	public int getPlaneId()
	{
		return planeId;
	}

	public void setPlaneId(int planeId)
	{
		this.planeId = planeId;
	}

	public int getTaskStatus()
	{
		return taskStatus;
	}

	public void setTaskStatus(int taskStatus)
	{
		this.taskStatus = taskStatus;
	}

	public int getMroId()
	{
		return mroId;
	}

	public void setMroId(int mroId)
	{
		this.mroId = mroId;
	}

	@Override
	public String toString()
	{
		return "id=" + id + ";idTaskGeneric=" + idTaskGeneric + ";start=" + Utility.convertDateToString(startTime) + ";end=" + Utility.convertDateToString(endTime) + ";planeId=" + planeId
				+ ";taskStatus=" + taskStatus + ";mroId=" + mroId;
	}

}
