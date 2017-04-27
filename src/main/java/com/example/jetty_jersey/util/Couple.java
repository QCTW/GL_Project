package com.example.jetty_jersey.util;

import java.util.ArrayList;
import java.util.List;

public class Couple {
	String user;
	String pass;
	
	public Couple(String x, String y){
		this.user=x;
		this.pass=y;
	}
	
	public static boolean inTab(List<Couple> l, Couple c){
		for(Couple e : l){
			if(e.user.equals(c.user) && e.pass.equals(c.pass))
				return true;
		}
		return false;
	}
	
	

}
