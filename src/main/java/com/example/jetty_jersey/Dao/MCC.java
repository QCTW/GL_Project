package com.example.jetty_jersey.Dao;

/**
 * 
 * PUT mcc/id
 * {
 * "email" : "email@exmaple"
 * }
 */
public class MCC
{
	private String mccId;
	private String email;

	
	public MCC(String email){
		this.email=email;
	}
	public String getMccId()
	{
		return mccId;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

}
