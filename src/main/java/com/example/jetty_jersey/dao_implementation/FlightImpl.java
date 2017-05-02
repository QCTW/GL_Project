package com.example.jetty_jersey.dao_implementation;

import java.util.List;

import com.example.jetty_jersey.dao.Flight;
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
		Flight f = new Flight(Utility.convertIntString(ligne.get("id")), ligne.get("commercialId"), ligne.get("departureAirport"), ligne.get("arrivalAirport"),
				Utility.convertDateString(ligne.get("departureTime")), Utility.convertDateString(ligne.get("arrivalTime")), Utility.convertIntString(ligne.get("planeId")));
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
			Flight f = new Flight(Utility.convertIntString(ligne.get("id")), ligne.get("commercialId"), ligne.get("departureAirport"), ligne.get("arrivalAirport"),
					Utility.convertDateString(ligne.get("departureTime")), Utility.convertDateString(ligne.get("arrivalTime")), Utility.convertIntString(ligne.get("planeId")));
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
			Flight f = new Flight(Utility.convertIntString(ligne.get("id")), ligne.get("commercialId"), ligne.get("departureAirport"), ligne.get("arrivalAirport"),
					Utility.convertDateString(ligne.get("departureTime")), Utility.convertDateString(ligne.get("arrivalTime")), Utility.convertIntString(ligne.get("planeId")));
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
			Flight f = new Flight(Utility.convertIntString(ligne.get("id")), ligne.get("commercialId"), ligne.get("departureAirport"), ligne.get("arrivalAirport"),
					Utility.convertDateString(ligne.get("departureTime")), Utility.convertDateString(ligne.get("arrivalTime")), Utility.convertIntString(ligne.get("planeId")));
			lf.add(f);
		}
		return lf;
	}

	public Flight getFlightbyPlaneId(int planeId)
	{
		DatabaseConnecter dbc = new DatabaseConnecter();
		List<Map<String, String>> res = dbc.selectAllFromTableWhereFieldEqValue("flight", "arrivalAirport", Integer.toString(planeId));
		;
		dbc.close();
		Map<String, String> ligne = res.get(0);
		Flight f = new Flight(Utility.convertIntString(ligne.get("id")), ligne.get("commercialId"), ligne.get("departureAirport"), ligne.get("arrivalAirport"),
				Utility.convertDateString(ligne.get("departureTime")), Utility.convertDateString(ligne.get("arrivalTime")), Utility.convertIntString(ligne.get("planeId")));
		return f;
	}
}
