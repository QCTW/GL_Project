package com.example.jetty_jersey.dao_interface;

import java.util.List;

import com.example.jetty_jersey.dao.*;
public interface AlertDao{
	
	/**
	 * Add a new Alert
	*@param alert
	**/
	public void add(Alert alert);
	/**
	 * Update an alert
	*@param alert
	**/
	public void update(Alert alert);
	/**
	 * Delete an alert by id
	*@param id
	**/
	public void delete(long id);
	/**
	 * Get an alert by id
	*@param id
	*@return An Alert
	**/
	public Alert select(long id);
	
	/**
	 * Get a list of alerts in the following interval (offset, limit)
	*@param offset
	*@param limit
	*@return the alerts list
	**/
	public List<Alert> select (int offset,int limit);

}