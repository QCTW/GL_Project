package com.example.jetty_jersey.Dao;

import com.example.jetty_jersey.db.CustomHashMap;

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
	
	public MCC(String mccId, String email){
		this.mccId=mccId;
		this.email=email;
		
	}

	public CustomHashMap<String, String> toMap()
	{
		CustomHashMap<String, String> chm = new CustomHashMap<String, String>();
		chm.put("_id", mccId);
		chm.put("email", email);

		return chm;
	}
	
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
	
	@Override
	public String toString()
	{
		return "id=" + mccId + ";email=" + email;
	}

}
