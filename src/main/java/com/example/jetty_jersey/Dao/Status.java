package com.example.jetty_jersey.Dao;

/**
 * This class is used to provide the status of backend execution.
 * In case there is an error, the front end should be able to display the error by retrieving from getMessage() & getException()
 */
public class Status
{
	public enum Execution
	{
		SUCCESSFUL, FAILED,
	}

	private String msg = "";
	private Execution exe = Execution.FAILED;

	public Status(Execution e)
	{
		exe = e;
	}

	public String getMessage()
	{
		return msg;
	}

	public void setMessage(String str)
	{
		msg = str;
	}

	public Execution getException()
	{
		return exe;
	}
}
