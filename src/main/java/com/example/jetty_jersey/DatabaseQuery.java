package main.java.com.example.jetty_jersey;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;

public class DatabaseQuery
{
	private final String method;
	private final String url;
	private final StringBuffer sb = new StringBuffer();

	public DatabaseQuery(String strMethod, String strURL)
	{
		method = strMethod;
		url = strURL;
	}

	public String getMethod()
	{
		return method;
	}

	public String getURL()
	{
		return url;
	}

	public HttpEntity getEntity()
	{
		return new NStringEntity("{\n" + sb.toString() + "}", ContentType.APPLICATION_JSON);
	}

	public void putData(String str)
	{
		sb.append(str + "\n");
	}

}
