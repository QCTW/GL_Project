package com.example.jetty_jersey.DaoInterface;

import java.util.List;

import com.example.jetty_jersey.Dao.MCC;
import com.example.jetty_jersey.Dao.Status;

public interface MccDao {

	
	
	/**
	 * @return the MCC with the given id
	 **/
	 MCC getMccById(String id);

	String getEmailById(String id);
	
	List<MCC> getMccsInRange(int nStart, int nEnd);
	
	/**
	 * @return add a MCC
	 **/
	Status addMcc(MCC t);

	/**
	 * @return modify a MCC
	 **/
	Status modifyMcc(MCC t);

	/**
	 * @return delete a MCC
	 **/
	Status deleteMcc(int MccId);
}
