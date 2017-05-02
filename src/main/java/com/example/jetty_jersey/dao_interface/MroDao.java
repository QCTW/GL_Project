package com.example.jetty_jersey.DaoInterface;

import java.util.List;

import com.example.jetty_jersey.Dao.*;
public interface MroDao{
	/**
	*@return the list of mro members with the given qualification
	**/
	public List<MRO> getMrobyQualification(String qualification);
	
	/**
	 * @return the list of all
	 **/
	List<MRO> getAllMros();

	/**
	 * @return add a MRO
	 **/
	Status addMro(MRO mro);

	/**
	 * @return modify a MRO
	 **/
	Status modifyMro(MRO mro);

	/**
	 * @return delete a MRO
	 **/
	Status deleteMro(int id);
}