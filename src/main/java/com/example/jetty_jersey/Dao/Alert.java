package com.example.jetty_jersey.Dao;

import com.example.jetty_jersey.DaoInterface.FlightDao;
import com.example.jetty_jersey.DaoInterface.PlaneDao;
import com.example.jetty_jersey.DaoInterface.TaskDao;
import com.example.jetty_jersey.DaoInterfaceImpl.*;

public class Alert
{
	private static long id;
	private static String msg;
	private static long userId;
	
	
	
	public Alert(String msg, long userId){
		id=0;
		this.msg=msg;
		this.userId=userId;
				
	}
	
	public long getId(){
		return id;
		
	}

	public void setId(long id){
		
		this.id=id;
	}
	public String getMsg(){
		
		return msg;
	}
	
	public void setMsg(String msg){
		
		this.msg=msg;
		
	}
	
	public long getUserId(){
		
		return userId;
	}
	
	public void setUserId(long userId){
		
		this.userId=userId;
	}
	
}
