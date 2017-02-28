package com.example.jetty_jersey.Dao;

import com.example.jetty_jersey.DaoInterface.FlightDao;
import com.example.jetty_jersey.DaoInterfaceImpl.*;


public class DAO {

	public static FlightDao getFlightDao(){
		return new FlightImpl();
	}
	
}
