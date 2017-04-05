package com.example.jetty_jersey.db;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utility
{
	private static Logger log = LogManager.getLogger(Utility.class.getName());
	private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * This method convert dd/MM/yyyy to date object
	 * 
	 * @param strDateInDb
	 * @return date
	 */
	public static Date convertDateString(String strDateInDb)
	{
		Date date = new Date();
		try
		{
			date = df.parse(strDateInDb);
		} catch (ParseException e)
		{
			log.error("Unrecognized date format (not dd/MM/yyyy):" + strDateInDb);
		}
		return date;
	}

	/**
	 * Reverse method of convertDateString with the same formatter
	 * 
	 * @param date
	 * @return string with format dd/MM/yyyy
	 */
	public static String convertDateToString(Date date)
	{
		return df.format(date);
	}

	/**
	 * This method convert string to boolean
	 * If string is TRUE/true/True, it return boolean true. Otherwise, it return false
	 * 
	 * @param strBoolInDb
	 * @return boolean
	 */
	public static boolean convertBoolString(String strBoolInDb)
	{
		if ("true".equals(strBoolInDb.trim().toLowerCase()))
			return true;
		else
			return false;
	}

	/**
	 * This method convert string to int
	 * Note that if string contains none-digit char, it will return 0 with an error in the log
	 * 
	 * @param strIntInDb
	 * @return integer
	 */
	public static int convertIntString(String strIntInDb)
	{
		int i = 0;
		try
		{
			i = Integer.parseInt(strIntInDb);
		} catch (NumberFormatException e)
		{
			log.error("Incorrect integer format :" + strIntInDb);
		}
		return i;
	}
}
