package com.example.jetty_jersey.util;

import java.util.ArrayList;
import java.util.List;

public class Couple {
	public String user;
	public String pass;
	public String role;
	
	public Couple(String user, String pass, String role){
		this.user=user;
		this.pass=pass;
		this.role=role;
	}
	
	public static String inTab(List<Couple> l, Couple c){
		for(Couple e : l){
			if(e.user.equals(c.user) && e.pass.equals(c.pass))
				return e.role;
		}
		return "incorrect";
	}
	
	

}
