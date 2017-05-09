package com.example.jetty_jersey.util;

import java.util.List;


public class LoginCouple
{
	public String user;
	public String pass;
	public String role;

	public LoginCouple(String user, String pass, String role)
	{
		this.user = user;
		this.pass = pass;
		this.role = role;
	}

	public static String inTab(List<LoginCouple> l, LoginCouple c)
	{
		for (LoginCouple e : l)
		{
			if (e.user.equals(c.user) && e.pass.equals(c.pass))
				return e.role;
		}
		return "incorrect";
	}
	
		
	

}
