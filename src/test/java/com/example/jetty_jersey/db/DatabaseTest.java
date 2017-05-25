package com.example.jetty_jersey.db;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DatabaseTest
{

	@Before
	public void initialize()
	{
		DatabaseSimulator.execute();
	}

	@Test
	public void test()
	{

		fail("Not yet implemented");
	}

}
