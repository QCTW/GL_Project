package com.example.jetty_jersey.dao_implementation;

import java.util.List;

import com.example.jetty_jersey.dao.Flight;
import com.example.jetty_jersey.dao.Status;
import com.example.jetty_jersey.dao_interface.FlightDao;

import java.util.ArrayList;
import java.util.Map;

import com.example.jetty_jersey.db.DatabaseConnecter;
import com.example.jetty_jersey.db.Utility;

public class FlightImpl implements FlightDao
{
	public Flight getFlightbyId(String commercialId)
	{
		DatabaseConnecter dbc = new DatabaseConnecter();
		List<Map<String, String>> res = dbc.selectAllFromTableWhereFieldEqValue("flight", "commercialId", commercialId);
		dbc.close();
		Map<String, String> ligne = res.get(0);
		Flight f = new Flight(Utility.convertIntString(ligne.get("id")), ligne.get("commercialId"), ligne.get("departureAirport"), ligne.get("arrivalAirport"), ligne.get("departureTime"),
				ligne.get("arrivalTime"), Utility.convertIntString(ligne.get("planeId")));
		return f;
	}

	public List<Flight> getFlightsbyAirport(String airport)
	{
		DatabaseConnecter dbc = new DatabaseConnecter();
		List<Map<String, String>> res = dbc.selectAllFromTableWhereFieldEqValue("flight", "arrivalAirport", airport);
		dbc.close();
		List<Flight> lf = new ArrayList<Flight>();
		for (Map<String, String> ligne : res)
		{
			Flight f = new Flight(Utility.convertIntString(ligne.get("id")), ligne.get("commercialId"), ligne.get("departureAirport"), ligne.get("arrivalAirport"), ligne.get("departureTime"),
					ligne.get("arrivalTime"), Utility.convertIntString(ligne.get("planeId")));
			lf.add(f);
		}
		return lf;
	}

	public List<Flight> getFlightsbyDepartureAirport(String airport)
	{
		DatabaseConnecter dbc = new DatabaseConnecter();
		List<Map<String, String>> res = dbc.selectAllFromTableWhereFieldEqValue("flight", "departureAirport", airport);
		dbc.close();
		List<Flight> lf = new ArrayList<Flight>();
		for (Map<String, String> ligne : res)
		{
			Flight f = new Flight(Utility.convertIntString(ligne.get("id")), ligne.get("commercialId"), ligne.get("departureAirport"), ligne.get("arrivalAirport"), ligne.get("departureTime"),
					ligne.get("arrivalTime"), Utility.convertIntString(ligne.get("planeId")));
			lf.add(f);
		}
		return lf;
	}

	public List<Flight> getFlightsbyArrivalAirport(String airport)
	{
		DatabaseConnecter dbc = new DatabaseConnecter();
		List<Map<String, String>> res = dbc.selectAllFromTableWhereFieldEqValue("flight", "arrivalAirport", airport);
		dbc.close();
		List<Flight> lf = new ArrayList<Flight>();
		for (Map<String, String> ligne : res)
		{
			Flight f = new Flight(Utility.convertIntString(ligne.get("id")), ligne.get("commercialId"), ligne.get("departureAirport"), ligne.get("arrivalAirport"), ligne.get("departureTime"),
					ligne.get("arrivalTime"), Utility.convertIntString(ligne.get("planeId")));
			lf.add(f);
		}
		return lf;
	}

	public List<Flight> getFlightsbyPlaneId(int planeId)
	{
		DatabaseConnecter dbc = new DatabaseConnecter();
		List<Map<String, String>> res = dbc.selectAllFromTableWhereFieldEqValue("flight", "planeId", Integer.toString(planeId));
		dbc.close();
		List<Flight> lf = new ArrayList<Flight>();
		for (Map<String, String> m : res)
		{
			Flight f = new Flight(Utility.convertIntString(m.get("id")), m.get("commercialId"), m.get("departureAirport"), m.get("arrivalAirport"), m.get("departureTime"), m.get("arrivalTime"),
					Utility.convertIntString(m.get("planeId")));
			lf.add(f);
		}
		return lf;
	}

	public Status addFlight(Flight f)
	{
		DatabaseConnecter dbc = new DatabaseConnecter();
		Status s = dbc.insertToTableName("flight", f.toMap());
		dbc.close();
		return s;
	}
}
